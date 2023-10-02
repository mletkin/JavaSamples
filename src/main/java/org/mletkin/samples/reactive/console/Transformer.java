package org.mletkin.samples.reactive.console;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 * Processor that transforms data.
 *
 * @param <T>
 *            Data type of the data provided by the publisher
 * @param <R>
 *            Data type of the data provided to the subscribers
 */
public abstract class Transformer<T, R> extends SubmissionPublisher<R> implements Processor<T, R> {

    private Subscription subscription;

    /**
     * Pumps data from the publisher to the subscriber.
     * <p>
     * Must call {@link SubmissionPublisher#submit()}
     *
     * @param item
     */
    abstract protected void pump(T item);

    /**
     * Adds a subscriber, suitable for method chaining.
     *
     * @param subscriber
     * @return the Transformer instance for method chaining
     */
    public Transformer<T, R> subscribeTo(Subscriber<? super R> subscriber) {
        super.subscribe(subscriber);
        return this;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        pump(item);
        subscription.request(1);
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
