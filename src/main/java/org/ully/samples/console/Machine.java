package org.ully.samples.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Dummy implementation for pipe experiments
 *
 * @author Ullrich
 */
public class Machine {

    InputStream inlet;
    OutputStream outlet;
    private Thread inputReader;
    private boolean quit = false;

    public int ip;
    public MachineCallback callback = e -> {};

    @FunctionalInterface
    public
    interface MachineCallback {
        public void call(Machine e);
    }

    public void connectInlet(InputStream inStream) {
        inlet = inStream;

        // start reading from incoming stream
        inputReader = new Thread(this::run);
        inputReader.setDaemon(true);
        inputReader.start();
    }

    public void connectOutlet(OutputStream outStream) {
        outlet = outStream;
    }

    /**
     * precess incoming data and pour it into the outlet
     *
     * @param data
     */
    void process(String data) {
        print("[" + data + "]");
    }

    /**
     * precess incoming data and pour it into the outlet
     *
     * @param data
     */
    public void print(String data) {
        try {
            outlet.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void run() {
        try {
            while (Thread.currentThread() == inputReader || quit) {
                try {
                    wait(100);
                } catch (InterruptedException ie) {}
                if (inlet.available() != 0) {
                    process(readLine());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while reading from stream: " + e);
        }
    }

    /**
     * read a line from the inlet
     *
     * @return
     * @throws IOException
     */
    private synchronized String readLine() throws IOException {
        String input = "";
        do {
            int available = inlet.available();
            if (available == 0)
                break;
            byte b[] = new byte[available];
            inlet.read(b);
            input = input + new String(b, 0, b.length);
        } while (!input.endsWith("\n") && !quit);
        return input;
    }

    /**
     * go on an infinte loop
     */
    public void process() {
        for (;;) {
            process("" + ip++);
            callback.call(this);
        }
    }
}
