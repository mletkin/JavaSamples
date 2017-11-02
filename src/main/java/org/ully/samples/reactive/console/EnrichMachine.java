package org.ully.samples.reactive.console;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
 * Machine, that adds text to each character.
 * <p>
 * Shows how to define a sequence of two transformers.<br>
 * The method getPump creates combined transformer but is not used here.
 */
public class EnrichMachine implements Machine {

    Publisher<Character> input;
    Subscriber<Character> output;

    public int ip = 0;

    /*
     * (non-Javadoc)
     *
     * @see org.ully.samples.reactive.console.Machine#setInput(java.util.concurrent.Flow.Publisher)
     */
    @Override
    public void setInput(Publisher<Character> input) {
        this.input = input;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.ully.samples.reactive.console.Machine#setOutput(java.util.concurrent.Flow.Subscriber)
     */
    @Override
    public void setOutput(Subscriber<Character> output) {
        this.output = output;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.ully.samples.reactive.console.Machine#connect()
     */
    @Override
    public void connect() {

        // sequenz.subscribe(output);
        // enrich.subscribe(sequenz);
        // input.subscribe(enrich);

        input.subscribe(enricher().subscribeTo(sequencer().subscribeTo(output)));
    }

    /**
     * Creates a Transformer that adds information to the character.
     *
     * @return
     */
    private Transformer<Character, String> enricher() {
        return new Transformer<Character, String>() {
            @Override
            protected void pump(Character item) {
                submit("[" + ip++ + ":" + item + "]");
            }
        };
    }

    /**
     * Creates a Transformer that expands a string to a sequence of characters.
     *
     * @return
     */
    private Transformer<String, Character> sequencer() {
        return new Transformer<String, Character>() {

            @Override
            protected void pump(String item) {
                for (char c : item.toCharArray()) {
                    submit(c);
                }
            }
        };
    }

    /**
     * Creates a Transformer that combines enricher and sequencer.
     *
     * @return
     */
    private Transformer<Character, Character> getPump() {
        return new Transformer<Character, Character>() {
            @Override
            protected void pump(Character item) {
                submit('[');
                submit(item);
                submit(']');
            }
        };
    }

}
