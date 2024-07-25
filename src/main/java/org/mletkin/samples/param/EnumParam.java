package org.mletkin.samples.param;

import java.util.EnumMap;

public class EnumParam {

    enum What {
        ORT, PLZ;
    }

    static class Param extends EnumMap<What, String> {

        Param() {
            super(What.class);
        }

        Param with(What key, String value) {
            put(key, value);
            return this;
        }
    }

    void find(Param param) {
        var ort = param.get(What.ORT);
        var plz = param.get(What.PLZ);
    }

    void callFind() {
        find(new Param() //
                .with(What.PLZ, "plz")//
                .with(What.ORT, "ort"));
    }
}
