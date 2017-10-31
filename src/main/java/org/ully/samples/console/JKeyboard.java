package org.ully.samples.console;

import static org.ully.samples.Util.closeSilently;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Text area, that acts as a Keyboard.
 * <ul>
 * <li>keys entered are dispayed in the text area
 * <li>keys entered are fed into the given input stream as they are typed
 * <li>control keys (BS, DEL, etc) are forwarded too
 * </ul>
 *
 * TODO cloese pipe on "close windows"
 *

 */
public class JKeyboard extends JTextArea {

    private PipedOutputStream outputPipe = new PipedOutputStream();

    /**
     * Create the keyboard and connect it to the input pipe.
     *
     * @param frame
     *            owner, needed for the listener
     * @param input
     */
    public JKeyboard(JFrame frame, PipedInputStream input) {
        try {
            input.connect(outputPipe);
        } catch (java.io.IOException | SecurityException e) {
            System.err.println("Couldn't connect stream to console" + e.getMessage());
        }
        addKeyListener(getKeyListener());
        frame.addWindowListener(getWindowListener());
    }

    /**
     * create a key adapter that pumps the keys into the stream
     *
     * @return
     */
    private KeyListener getKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    if (e.getKeyChar() == '\n') {
                        setText("");
                    }
                    if (e.getKeyChar() >= 32 || e.getKeyChar() == '\n') {
                        outputPipe.write(e.getKeyChar());
                    }
                } catch (IOException e1) {
                    setText("The keyboard is disconnected");
                    setEnabled(false);
                }
            }
        };
    }

    private WindowListener getWindowListener() {
        return new WindowAdapter() {
            @Override
            public synchronized void windowClosed(WindowEvent evt) {
                this.notifyAll(); // stop all threads
                closeSilently(outputPipe);
            }
        };
    }
}
