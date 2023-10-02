package org.mletkin.samples.bzkw;

public interface AlleLinks {

    static AlleLinks start() {
        return null;
    }

    GefahrLinkesUfer bauerAlleinNachRechts();

    KohlUndWolfLinks_ZiegeUndBauerRechts bauerMitZiegeNachRechts();

    GefahrLinkesUfer bauerMitKohlNachRechts();

    GefahrLinkesUfer bauerMitWolfNachRechts();

}
