package org.mletkin.samples.console;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import org.mletkin.samples.Util;


/**
 * R/O JTextArea attached to a PipedOutputStream
 */
public class PipedConsole extends JTextArea  {

    private JFrame frame;
    // signals the thread that it should exit
    private boolean quit = false;
    private Thread inputReader;
    private PipedInputStream inputPipe = new PipedInputStream();

    /**
     * Get a Console Component attached to the given {@link JFrame}
     *
     * @param frame
     * @param outStream
     */
    public PipedConsole(JFrame frame, PipedOutputStream outStream) {
        super(25, 20);

        this.setEditable(false);
        this.setLineWrap(true);
        ((DefaultCaret) this.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        this.frame = frame;
        frame.addWindowListener(getWindowListener());

        try {
            outStream.connect(inputPipe);
        } catch (java.io.IOException | SecurityException e) {
            append("Couldn't redirect outputStream to the console\n" + e.getMessage());
        }

        // start reading drom incoming stream to console
        inputReader = new Thread(this::run);
        inputReader.setDaemon(true);
        inputReader.start();
    }

    /**
     * copy incoming data to inputPipe
     */
    public synchronized void run() {
        try {
            while (Thread.currentThread() == inputReader || quit) {
                try {
                    this.wait(100);
                } catch (InterruptedException e) {}
                if (inputPipe.available() > 0) {
                    append(Util.readLine(inputPipe, ()-> quit));
                }
            }
        } catch (Exception e) {
            append("Exception while reading from stream: " + e);
        }
    }

    public synchronized void clear() {
        setText("");
    }

    WindowListener getWindowListener() {
        return new WindowAdapter() {
            @Override
            public synchronized void windowClosed(WindowEvent evt) {
                quit = true;
                this.notifyAll(); // stop all threads
                try {
                    inputReader.join(1000);
                    Util.closeSilently(inputPipe);
                } catch (Exception e) {}
                System.exit(0);
            }

            @Override
            public synchronized void windowClosing(WindowEvent evt) {
                frame.setVisible(false);
                frame.dispose();
            }
        };
    }
}
