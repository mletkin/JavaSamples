package org.mletkin.samples.reactive.console;

import java.awt.Dimension;
import java.util.concurrent.SubmissionPublisher;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * Simple swing window as keyboard/display emulator.
 * <ul>
 * <li>The {@link EnrichMachine}-Object acts as data processing instance.
 * <li>The "keyboard" is connected to the machine as input channel
 * <li>The "display1" is connected to the machine as output channel
 * <li>The "display2" shows the "raw" input data
 * </ul>
 */
public class SampleConsoleWindow {

    private JFrame frame = new JFrame("Example Console");
    private DisplaySubscriber display1 = new DisplaySubscriber();
    private DisplaySubscriber display2 = new DisplaySubscriber();
    private KeyboardPublisher keyboard = new KeyboardPublisher(frame);

    /**
     * Creates the console window.
     *
     * @param title
     *            totle to display in the the window's title bar
     * @param machine
     *            machine to beconnected to in- and output
     */
    public SampleConsoleWindow(String title, Machine machine) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setTitle(title);

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

        connect(machine);
    }

    /**
     * Connects keyboard and display to the machine.
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
     *            component to embed into the scroll pane
     * @return ScrollPane with the embedded component
     */
    public JScrollPane mkVerticalScrollPane(JComponent component) {
        return new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

}
