package org.ully.samples.threads.condition;

import java.io.IOException;

/**
 * Sample app to visualize thread control.<br>
 * uses the condition mechanism
 *
 * @author Ullrich
 */
public class AwaitSignalSampleApp {

    /**
     * run a sample I/O-Console with data processing "machine"
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new SampleThreadWindow();
    }

}
