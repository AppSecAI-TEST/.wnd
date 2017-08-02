package kr.co.windowfun;

import android.view.Gravity;

import kr.co.windowfun.widget.CText;
import kr.co.windowfun.widget.R;

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

    enum text_size {
        //text_size: "micro|tiny|small|normal|large|xlarge"
        micro(R.dimen.text_size_micro),
        tiny(R.dimen.text_size_tiny),
        small(R.dimen.text_size_small),
        normal(R.dimen.text_size_normal),
        large(R.dimen.text_size_large),
        xlarge(R.dimen.text_size_xlarge);

        private final int value;

        text_size(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum text_valign {
        //text_valign: "top|center|bottom"
        top(Gravity.TOP),
        center(Gravity.CENTER_VERTICAL),
        bottom(Gravity.BOTTOM);

        private final int value;

        text_valign(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
