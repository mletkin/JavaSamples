package org.ully.samples.reactive.console;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
 * Interface for a character processing machine with one in- and one out stream.
 */
public interface Machine {

    /**
     * Sets a publisher as input stream.
     *
     * @param input
     *            the publisher to read from.
     */
    void setInput(Publisher<Character> input);

    /**
     * Sets a subscriber as output stream.
     *
     * @param input
     *            the publisher to read from.
     */
    void setOutput(Subscriber<Character> output);

    /**
     * Connects input and output with the machine.
     */
    void connect();

}
