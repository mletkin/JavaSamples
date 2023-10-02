package org.mletkin.samples.bzkw;

public interface BauerKohlUndWolfLinks_ZiegeRechts {

    KohlUndWolfLinks_ZiegeUndBauerRechts bauerAlleinNachRechts();

    WolfLinks_BauerZiegeUndKohlRechts bauerMitKohlNachRechts();

    KohlLinks_BauerZiegeUndWolfRechts bauerMitWolfNachRechts();

}
