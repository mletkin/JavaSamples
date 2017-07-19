package org.ully.samples.threads.legacy;

import org.ully.samples.console.Machine;
import org.ully.samples.console.Machine.MachineCallback;

/**
 * Run the machine in a separate thread
 *
 * @author Ullrich
 */
final class MachineThread extends Thread {

    private Machine.MachineCallback onDebug;
    private EngineThreadCallback onTermination;
    private Machine machine;

    @FunctionalInterface
    interface EngineThreadCallback {
        void call(Machine machine);
    }

    public MachineThread(Machine machine, MachineCallback debugCallback,
            EngineThreadCallback onTermination) {
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

}
