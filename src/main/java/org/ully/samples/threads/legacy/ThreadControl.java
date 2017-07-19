package org.ully.samples.threads.legacy;

import org.ully.samples.console.Machine;
import org.ully.samples.console.Machine.MachineCallback;

/**
 * Controls the thread for the engine execution
 *
 * @author Ullrich
 */
public class ThreadControl {

    private final MachineCallback WAIT = e -> this.suspendExecution();
    private final MachineCallback RUN = e -> {};
    private final MachineCallback STOP = e -> this.kill();

    private MachineThread thread;
    private Machine engine;

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
        if (!threadAlive()) {
            startThread(WAIT);
            return;
        }
        resumeExecution(WAIT);
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

    private void suspendExecution() {
        thread.suspend();
    }

    private void resumeExecution(MachineCallback callback) {
        engine.callback = callback;
        thread.resume();
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
