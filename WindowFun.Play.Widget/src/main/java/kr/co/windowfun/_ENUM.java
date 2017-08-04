package kr.co.windowfun;

import android.view.Gravity;

import kr.co.windowfun.widget.CText;
import kr.co.windowfun.widget.R;

/**
 * Created by isyuun on 8/2/2017.
 */

public interface _ENUM {
    //"plane|line|fade|typer|rainbow|scale|evaporate|fall"
    enum text_effect {
        plane(CText.plane),
        line(CText.line),
        fade(CText.fade),
        typer(CText.typer),
        rainbow(CText.rainbow),
        scale(CText.scale),
        evaporate(CText.evaporate),
        fall(CText.fall),;

        private CText value;

        text_effect(CText value) {
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
        //text_size: "xxxlarge|xxlarge|xlarge|large|normal|small|xsmall|xxsmall|xxxsmall"
        xxxlarge(R.dimen.text_size_xxxlarge),
        xxlarge(R.dimen.text_size_xxlarge),
        xlarge(R.dimen.text_size_xlarge),
        large(R.dimen.text_size_large),
        normal(R.dimen.text_size_normal),
        small(R.dimen.text_size_small),
        xsmall(R.dimen.text_size_xsmall),
        xxsmall(R.dimen.text_size_xxsmall),
        xxxsmall(R.dimen.text_size_xxxsmall),
        ;
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
