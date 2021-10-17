package org.ully.samples.encoding;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Write to a File with ISO-8859 encoding.
 */
public class IsoWriter {

    private static String TEXT = "Umlaute: äöüÄÖÜß";

    public static void main(String[] args) {

        String file = "d:\\data\\test.txt";
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "ISO8859_1"))) {
            writer.write(TEXT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
