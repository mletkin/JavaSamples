package org.ully.samples;

import java.io.Closeable;
import java.io.IOException;
import java.io.PipedInputStream;

public class Util {

    /**
     * Check the string fpr emptyness.
     * <p>
     * TODO: highly unefficient
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static void closeSilently(Closeable door) {
        try {
            if (door != null) {
                door.close();
            }
        } catch (Exception e) {}
    }

    @FunctionalInterface
    public interface Quit {
        boolean yes();
    }

    public static synchronized String readLine(PipedInputStream in, Quit quit) throws IOException {
        String input = "";
        do {
            int available = in.available();
            if (available == 0)
                break;
            byte b[] = new byte[available];
            in.read(b);
            input = input + new String(b, 0, b.length);
        } while (!input.endsWith("\n") && !quit.yes());
        return input;
    }

}
