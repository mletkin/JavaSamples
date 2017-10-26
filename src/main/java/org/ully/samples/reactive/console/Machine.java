package org.ully.samples.reactive.console;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
 * Machine as Character Transformer.
 * <p>
 * connects a publisher to a subscriber and transforms the data sent through.
 */
public class Machine {

    Publisher<Character> input;
    Subscriber<Character> output;

    public int ip = 0;

    public void setInput(Publisher<Character> input) {
        this.input = input;
    }

    public void setOutput(Subscriber<Character> output) {
        this.output = output;
    }

    // Connects input to combined transformer to output.
    // public void connect() {
    // Transformer<Character, Character> processor = getPump();
    // input.subscribe(processor);
    // processor.subscribe(output);
    // }

    /**
     * Connects input to transformer chain to output.
     */
    public void connect() {

        // input.subscribe(enrich);
        // enrich.subscribe(sequenz);
        // sequenz.subscribe(output);

        input.subscribe(enricher().subscribeTo(sequencer().subscribeTo(output)));
    }

    /**
     * Creates a Transformer that adds info to the character.
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
