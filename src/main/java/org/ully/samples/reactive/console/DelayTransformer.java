package org.ully.samples.reactive.console;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 * Processor that simulates delayed processing of data.
 *
 * @param <T>
 *            Data type of the data provided by the publisher
 */
public class DelayTransformer<T> extends SubmissionPublisher<T> implements Processor<T, T> {

    private static final int MAX_DELAY_IN_MSEC = 1000;
    private Subscription subscription;

    /**
     * Adds a subscriber, suitable for method chaining.
     *
     * @param subscriber
     * @return the Transformer instance for method chaining
     */
    public DelayTransformer<T> subscribeTo(Subscriber<? super T> subscriber) {
        super.subscribe(subscriber);
        return this;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(10);
    }

    @Override
    public void onNext(T item) {
        submit(item);
        delay();
        subscription.request(10);
    }

    private void delay() {
        try {
            long msec = (long) (Math.random() * MAX_DELAY_IN_MSEC);
            Thread.sleep(msec);
        } catch (InterruptedException ex) {
            System.out.println("interrupted");
            Thread.currentThread().interrupt();
        }
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
