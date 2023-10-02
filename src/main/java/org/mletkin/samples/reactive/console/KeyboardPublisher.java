package org.mletkin.samples.reactive.console;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.SubmissionPublisher;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * JTextArea, that acts as a Keyboard and publishes the input.
 * <ul>
 * <li>keys entered are dispayed in the text area
 * <li>keys entered are published as they are typed
 * <li>control keys (BS, DEL, etc) are published unchanged
 * </ul>
 */
public class KeyboardPublisher extends JTextArea {

    private SubmissionPublisher<Character> publisher = new SubmissionPublisher<>();

    /**
     * Creates the keyboard and connects it to the publisher.
     *
     * @param frame
     *            owner, needed for the listener
     */
    public KeyboardPublisher(JFrame frame) {
        addKeyListener(getKeyListener());
        frame.addWindowListener(getWindowListener());
    }

    /**
     * Gets the publisher attached to the keyboard.
     *
     * @return
     */
    public SubmissionPublisher<Character> getPublisher() {
        return publisher;
    }

    /**
     * Creates a Listener that publishes the keys entered.
     * <ul>
     * <li>enter clears the text area
     * <li>characters >= 32 are published
     * <li>escape closes the publisher
     * </ul>
     *
     * @return
     */
    private KeyListener getKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 27) {
                    publisher.close();
                }
                if (e.getKeyChar() == '\n') {
                    setText("");
                }
                if (e.getKeyChar() >= 32 || e.getKeyChar() == '\n') {
                    publish(e);
                }
            }

            private void publish(KeyEvent e) {
                if (!publisher.isClosed()) {
                    publisher.submit(e.getKeyChar());
                }
            }
        };
    }

    /**
     * Creates a Listener that closes the publisher when the windows closes.
     *
     * @return
     */
    private WindowListener getWindowListener() {
        return new WindowAdapter() {
            @Override
            public synchronized void windowClosing(WindowEvent evt) {
                this.notifyAll(); // stop all threads
                publisher.close();
            }
        };
    }
}
