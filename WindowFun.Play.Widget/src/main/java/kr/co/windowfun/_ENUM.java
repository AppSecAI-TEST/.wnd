package kr.co.windowfun;

import kr.co.windowfun.widget.CText;

/**
 * Created by isyuun on 8/2/2017.
 */

public interface _ENUM {
    //"line|fade|typer|rainbow|scale|evaporate|fall"
    enum effect_text {
        line(CText.line),
        fade(CText.fade),
        typer(CText.typer),
        rainbow(CText.rainbow),
        scale(CText.scale),
        evaporate(CText.evaporate),
        fall(CText.fall),;

        CText value;
        effect_text(CText value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + this.value;
        }
    }

    //"text|image|video|html"
    enum m_type {
        text("text"),
        image("image"),
        video("video"),
        html("html"),;

        private String value;

        m_type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    //"text|image|video|html"
    enum c_type {
        text("text"),
        image("image"),
        video("video"),
        html("html"),;

        private String value;

        c_type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
