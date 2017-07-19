package org.ully.samples.threads.condition;

import org.ully.samples.console.Machine;
import org.ully.samples.console.Machine.MachineCallback;

/**
 * Controls the thread for the engine execution
 *
 * @author Ullrich
 */
public class ThreadControl {

    private MachineThread thread;
    private Machine engine;

    private final MachineCallback WAIT = e -> thread.pauseExecution();
    private final MachineCallback RUN = e -> {};
    private final MachineCallback STOP = e -> this.kill();

    public ThreadControl(Machine engine) {
        this.engine = engine;
    }

    public void startExecution() {
        if (threadAlive()) {
            resumeExecution(RUN);
            return;
        }
        startThread(RUN);
    }

    public void stepExecution() {
        if (threadAlive()) {
            resumeExecution(WAIT);
            return;
        }
        startThread(WAIT);
    }

    public void stopExecution() {
        resumeExecution(STOP);
    }

    public void pauseExecution() {
        engine.callback = WAIT;
    }

    private void startThread(MachineCallback callback) {
        thread = new MachineThread(engine, callback, e -> e.print("\nkilled"));
        thread.start();
    }

    private void resumeExecution(MachineCallback callback) {
        engine.callback = callback;
        thread.continueExecution();
    }

    private boolean threadAlive() {
        return thread != null && thread.isAlive();
    }

    public void kill() {
        if (threadAlive()) {
            thread.stop();
        }
    }
}
