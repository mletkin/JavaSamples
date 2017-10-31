package org.ully.samples.threads.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.ully.samples.console.Machine;
import org.ully.samples.console.Machine.MachineCallback;

/**
 * Run the machine in a separate thread
 */
final class MachineThread extends Thread {

    private final Lock lock = new ReentrantLock();
    private final Condition suspended = lock.newCondition();

    private Machine.MachineCallback onDebug;
    private EngineThreadCallback onTermination;
    private Machine machine;

    @FunctionalInterface
    interface EngineThreadCallback {
        void call(Machine machine);
    }

    public MachineThread(Machine machine, MachineCallback debugCallback, EngineThreadCallback onTermination) {
        this.machine = machine;
        this.onDebug = debugCallback;
        this.onTermination = onTermination;
    }

    @Override
    public void run() {
        try {
            machine.callback = onDebug;
            machine.process();
        } finally {
            onTermination.call(machine);
        }
    }

    public void pauseExecution() {
        lock.lock();
        try {
            suspended.await();
        } catch (InterruptedException e) {
            System.err.println("Thread ijterruptedExecution");
        } finally {
            lock.unlock();
        }
    }

    public void continueExecution() {
        lock.lock();
        try {
            suspended.signal();
        } finally {
            lock.unlock();
        }
    }

}
