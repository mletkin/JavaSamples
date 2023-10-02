package org.mletkin.samples.encoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.Test;

/**
 * Try to read different encoded files.
 */
public class Utf8 {

    /**
     * Reading a UTF-8 file as UTF-8 results in a UTF-16 string.
     *
     * @throws IOException
     */
    @Test
    public void readUtf8WithStream() throws IOException {
        System.out.println(">read UTF-8 as UTF-8 with a stream.");
        File file = getFile("utf8.txt");
        Files.lines(file.toPath()).forEach(this::print);
    }

    /**
     * Reading an ISO 8859-1 file as UTF-8 results in an exception.
     *
     * @throws IOException
     */
    @Test(expected = UncheckedIOException.class)
    public void readIsoWithAStream() throws IOException {
        System.out.println(">read ISO with a stream.");
        File file = getFile("iso.txt");
        Files.lines(file.toPath()).forEach(this::print);
    }

    /**
     * Reading an ISO 8859-1 file as UTF-8 with a buffered reader results in an
     * exception.
     *
     * @throws IOException
     */
    @Test(expected = UncheckedIOException.class)
    public void readIsoBufferedWithStream() throws IOException {
        System.out.println(">read ISO with buffered reader and stream.");
        try (BufferedReader br = Files.newBufferedReader(getFile("iso.txt").toPath())) {
            br.lines().forEach(this::print);
        }
    }

    /**
     * Reading an ISO 8859-1 file with a buffered reader results in an exception.
     *
     * @throws IOException
     */
    @Test(expected = MalformedInputException.class)
    public void readUtf8BufferedAsascii() throws IOException {
        System.out.println(">read UTF-8 with buffered reader as ISO.");
        try (BufferedReader br = Files.newBufferedReader(getFile("iso.txt").toPath(), StandardCharsets.US_ASCII)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                print(line);
            }
        }
    }

    /**
     * Reading a ISO 8859-1 file as ISO 8859-1 with buffered reader results in a UTF-16
     * string.
     *
     * @throws IOException
     */
    @Test
    public void readIsoBufferedAsIso() throws IOException {
        System.out.println(">read ISO with buffered reader as ISO.");
        try (BufferedReader br = Files.newBufferedReader(getFile("iso.txt").toPath(), StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                print(line);
            }
        }
    }

    /**
     * Reading an UTF-8 file as ISO 8859-1 leads to a UTF-16 string with garbled
     * non-ASCII-Characters.
     *
     * @throws IOException
     */
    @Test
    public void readUtf8BufferedAsIso() throws IOException {
        System.out.println(">read UTF-8 with buffered reader as ISO.");
        try (BufferedReader br = Files.newBufferedReader(getFile("utf8.txt").toPath(), StandardCharsets.ISO_8859_1)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                print(line);
            }
        }
    }

    /**
     * Reading a UTF-8 file with {@link TolerantFileReader} leads to a UTF-16 string.
     *
     * @throws IOException
     */
    @Test
    public void myReadUtf8() throws IOException {
        System.out.println(">read UTF-8 file with TolerantFileReader.");
        try (TolerantFileReader reader = new TolerantFileReader(getFile("utf8.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                print(line);
            }
        }
    }

    /**
     * Reading an ISO 8859-1 file with {@link TolerantFileReader} leads to a UTF-16 string.
     *
     * @throws IOException
     */
    @Test
    public void myReadIso() throws IOException {
        System.out.println(">read ISO file with TolerantFileReader.");
        try (TolerantFileReader reader = new TolerantFileReader(getFile("iso.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                print(line);
            }
        }
    }

    private File getFile(String name) {
        return new File(getClass().getClassLoader().getResource("encoding/" + name).getFile());
    }

    private void print(String line) {
        System.out.println(line);
    }
}
