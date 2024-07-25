package org.mletkin.samples.param;

import java.util.EnumMap;

public class EnumParam2 {

    enum Param {
        ORT, PLZ;

        static class Obj extends EnumMap<Param, String> {

            Obj() {
                super(Param.class);
            }

            Obj with(Param key, String value) {
                put(key, value);
                return this;
            }
        }
    }

    void find(Param.Obj param) {
        var ort = param.get(Param.ORT);
        var plz = param.get(Param.PLZ);
    }

    void callFind() {
        find(new Param.Obj() //
                .with(Param.PLZ, "plz")//
                .with(Param.ORT, "ort"));
    }
}
