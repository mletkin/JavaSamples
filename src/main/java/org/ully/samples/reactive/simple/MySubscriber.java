package org.ully.samples.reactive.simple;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscriber<T> implements Subscriber<T> {
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
      this.subscription = subscription;
      subscription.request(Long.MAX_VALUE); // # of accepted Objects, effectively unbounded
    }

    @Override
    public void onNext(T item) {
      System.out.println("Got : " + item);
      subscription.request(Long.MAX_VALUE); // # of accepted Objects, effectively unbounded
    }

    @Override
    public void onError(Throwable t) {
      t.printStackTrace();
    }

    @Override
    public void onComplete() {
      System.out.println("Done");
    }
  }
