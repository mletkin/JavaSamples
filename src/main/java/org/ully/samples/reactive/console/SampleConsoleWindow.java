package org.ully.samples.reactive.console;

import java.awt.Dimension;
import java.util.concurrent.SubmissionPublisher;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * Simple keyboard/display emulator.
 * <ul>
 * <li>The {@link Machine}-Object acts as data processing instance.
 * <li>The "keyboard" is connected to the machine as input channel
 * <li>The "display1" is connected directly to the machine as output channel
 * <li>The "display2" is connected to the machine as output channel
 * </ul>
 */
public class SampleConsoleWindow {

    private JFrame frame = new JFrame("Example Console");
    private DisplaySubscriber display1 = new DisplaySubscriber();
    private DisplaySubscriber display2 = new DisplaySubscriber();
    private KeyboardPublisher keyboard = new KeyboardPublisher(frame);

    /**
     * Creates the console window.
     */
    public SampleConsoleWindow() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        frame.getContentPane().add(//
                new JSplitPane(JSplitPane.VERTICAL_SPLIT, //
                        new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, //
                                mkVerticalScrollPane(display1), //
                                mkVerticalScrollPane(display2)), //
                        mkVerticalScrollPane(keyboard)//
                )//
        );

        frame.pack();
        frame.setVisible(true);

        connect(new Machine());
    }

    /**
     * Connect keyboard to machine to displays.
     *
     * @param machine
     */
    private void connect(Machine machine) {
        SubmissionPublisher<Character> publisher = keyboard.getPublisher();
        machine.setInput(publisher);
        machine.setOutput(display1);
        machine.connect();
        publisher.subscribe(display2);
    }

    /**
     * Adds a vertical scrollbar to the component.
     *
     * @param component
     * @return
     */
    public JScrollPane mkVerticalScrollPane(JComponent component) {
        return new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

}
