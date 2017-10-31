package org.ully.samples.reactive.console;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
 * Machine, that transforms data using a {@ link Processor}.
 * <p>
 * connects a publisher to a subscriber and transforms the data sent through.
 */
public class TransformMachine implements Machine {

    private Processor<Character, Character> processor;
    private Publisher<Character> input;
    private Subscriber<Character> output;

    /**
     * Constructs a Machine using the given processor.
     *
     * @param processor
     *            Prcessor used to transform the data
     */
    public TransformMachine(Processor<Character, Character> processor) {
        this.processor = processor;
    }

    @Override
    public void setInput(Publisher<Character> input) {
        this.input = input;
    }

    @Override
    public void setOutput(Subscriber<Character> output) {
        this.output = output;
    }

    @Override
    public void connect() {
        processor.subscribe(output);
        input.subscribe(processor);
    }

}
