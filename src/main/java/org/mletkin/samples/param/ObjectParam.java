package org.mletkin.samples.param;

public class ObjectParam {

    static class Param {
        String ort;
        String plz;

        Param ort(String ort) {
            this.ort = ort;
            return this;
        }

        Param plz(String plz) {
            this.plz = plz;
            return this;
        }

    }

    void find(Param param) {
        var ort = param.ort;
        var plz = param.plz;
    }

    void callFind() {
        find(new Param() //
                .ort("Ort") //
                .plz("plz"));
    }

}
