package org.mletkin.samples.reactive.console;

/**
 * Runs different sample console windiws with reactive key propagation.
 */
public class SampleConsole {

    /**
     * Runs sample I/O-Consoles with different data processing machines.
     *
     * @param args
     *            command line parameter
     */
    public static void main(String[] args) {
        new SampleConsoleWindow("Collector", new TransformMachine(new CollectTransformer()));
        new SampleConsoleWindow("Delay", new TransformMachine(new DelayTransformer<Character>()));
        new SampleConsoleWindow("Enrich", new EnrichMachine());
    }

}
