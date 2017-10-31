    package org.ully.samples.reactive.console;

import java.util.Stack;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 * Processor that collect a line of characters and publishes them en block.
 */
public class CollectTransformer extends SubmissionPublisher<Character> implements Processor<Character, Character> {

    private Subscription subscription;
    private Stack<Character> buffer = new Stack<Character>();

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Character item) {
        buffer.push(item);
        if (item.equals('\n')) {
            flush();
        }
        subscription.request(1);
    }

    private void flush() {
        buffer.stream().forEach(this::submit);
        buffer.clear();
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}
