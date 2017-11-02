package org.ully.samples.reactive.console;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * R/O-JTextArea that displays characters obtained from a subscription.
 */
public class DisplaySubscriber extends JTextArea implements Subscriber<Character> {

    private Subscription subscription;

    public DisplaySubscriber() {
        super(25, 20);
        this.setEditable(false);
        this.setLineWrap(true);
        ((DefaultCaret) this.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Character zeichen) {
        append(String.valueOf(zeichen));
        subscription.request(1);
    }

    @Override
    public void onComplete() {
        append(" -- Done");
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

}
