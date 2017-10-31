package org.ully.samples.threads.legacy;

import java.io.IOException;

/**
 * Sample app to visualize thread control.
 * <p>
 * uses the deprecated java suspend/resume mechanism
 */
public class SuspendResumeSampleApp {

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

