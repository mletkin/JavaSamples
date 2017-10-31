package org.ully.samples.console;

import java.awt.Dimension;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * Simple keyboard/display emulator.
 * <ul>
 * <li>The {@link Machine}-Object acts as processing instance.
 * <li>The "keyboard" is connected to the ingoing pipe of the machine
 * <li>The "display" is connected to the outgoing pipe of the machine
 * </ul>
 */
public class SampleConsoleWindow {

    private JFrame frame = new JFrame("Example Console");
    private PipedConsole display;
    private JKeyboard keyboard;

    /**
     * create the console window
     */
    public SampleConsoleWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        mkConsole(new Machine());
        frame.getContentPane().add(//
                new JSplitPane(JSplitPane.VERTICAL_SPLIT, //
                        General.mkVerticalScrollPane(display), //
                        General.mkVerticalScrollPane(keyboard)//
                )//
        );

        frame.pack();
        frame.setVisible(true);
    }

    private void mkConsole(Machine maschine) {

        // connect to keyboard
        PipedInputStream keyboardOut = new PipedInputStream();
        keyboard = new JKeyboard(frame, keyboardOut);
        maschine.connectInlet(keyboardOut);

        // connect to display
        PipedOutputStream displayIn = new PipedOutputStream();
        maschine.connectOutlet(displayIn);
        display = new PipedConsole(frame, displayIn);
    }

}
