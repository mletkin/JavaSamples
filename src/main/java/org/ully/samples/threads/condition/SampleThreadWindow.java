package org.ully.samples.threads.condition;

import static org.ully.samples.console.General.mkButton;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.PipedOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.ully.samples.console.Machine;
import org.ully.samples.console.PipedConsole;

/**
 * sample for thread debugger.
 *
 * @author Ullrich
 */
public class SampleThreadWindow {

    private JFrame frame = new JFrame("Thread Example with await/signal");
    private Machine machine = new Machine();
    private ThreadControl ctrl = new ThreadControl(machine);
    private PipedConsole console;

    public SampleThreadWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        frame.getContentPane().add(mkButtonPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(console = mkConsole(machine), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private Component mkButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(mkButton("go", e -> ctrl.startExecution()));
        panel.add(mkButton("suspend", e -> ctrl.pauseExecution()));
        panel.add(mkButton("resume", e -> ctrl.startExecution()));
        panel.add(mkButton("step", e -> ctrl.stepExecution()));
        panel.add(mkButton("stop", e -> ctrl.stopExecution()));
        panel.add(mkButton("kill", e -> ctrl.kill()));

        panel.add(mkButton("clear", e -> this.console.clear()));
        return panel;
    }

    private PipedConsole mkConsole(Machine maschine) {
        // connect to display
        PipedOutputStream displayIn = new PipedOutputStream();
        maschine.connectOutlet(displayIn);
        return new PipedConsole(frame, displayIn);
    }

}
