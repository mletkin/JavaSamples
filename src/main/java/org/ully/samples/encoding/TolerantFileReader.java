package org.ully.samples.encoding;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Tries to read a file as UTF-8 and falls back to ISO-8859-1.
 *
 * treats sequences of <CR><LF> or <LF><CR> as single line.
 */
public class TolerantFileReader implements Closeable {

    private byte[] buffer = new byte[1024];
    private int cnt = 0;
    private Reader reader = null;

    public TolerantFileReader(File file) throws FileNotFoundException {
        this.reader = new FileReader(file);
    }

    /**
     * read a line and decode it as UTF-8 or ISO-8859-1
     *
     * @return
     * @throws IOException
     */
    public String readLine() throws IOException {
        cnt = 0;
        int c = -1;
        while (cnt < 1024) {
            c = reader.read();
            if (c == '\n' || c == '\r' || c == -1) {
                break;
            }
            buffer[cnt++] = (byte) c;
        }
        if (c == -1 && cnt == 0) {
            return null;
        } else {
            String result = decode();
            int a = reader.read();
            if ((a == '\n' || a == '\r') && a != c) {
                cnt = 0;
            } else {
                cnt = 1;
                buffer[1] = (byte) a;
            }
            return result;
        }
    }

    /**
     * decode the current buffer with UTF-8 and ISO-8859-1
     *
     * @return
     */
    private String decode() {
        try {
            return decodeAsUtf8();
        } catch (Exception e) {
            return new String(buffer, 0, cnt, StandardCharsets.ISO_8859_1);
        }
    }

    /**
     * decode the crrent buffer with UTF-8
     *
     * @return
     * @throws CharacterCodingException
     */
    public String decodeAsUtf8() throws CharacterCodingException {
        CharsetDecoder cd = Charset.availableCharsets().get("UTF-8").newDecoder();
        return cd.decode(ByteBuffer.wrap(buffer, 0, cnt)).toString();
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
