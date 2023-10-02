package org.mletkin.samples.threads.condition;

import java.io.IOException;

/**
 * Sample app to visualize thread control.
 * <p>
 * uses the condition mechanism
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
