package kr.co.windowfun.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;

/**
 * text_font: "TBD",<br>
 * text_line: "true|false",<br>
 * text_size: "text|tiny|small|normal|large|xlarge",<br>
 * text_valign: "top|center|bottom",<br>
 * Created by isyuun on 8/2/2017.
 */

class TextView3 extends TextView2 {
    public TextView3(Context context) {
        super(context);
    }

    public TextView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * TODO:text_font: "TBD"
     */
    public void textFont(String font) {
        //Log.wtf(__CLASSNAME__, getMethodName() + font);
    }

    /**
     * text_line: "true|false"
     */
    public void textLine(boolean line) {
        try {
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + line);
            textView.setSingleLine(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * text_size: "text|tiny|small|normal|large|xlarge"
     */
    public void textSize(text_size size) {
        try {
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + size + ":" + size.value());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(size.value()));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * text_valign: "top|center|bottom"
     */
    public void textVAlign(text_valign valign) {
        try {
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + valign + ":" + valign.value() + ":" + textView.getLayoutParams());
            textView.setGravity(valign.value());
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) textView.getLayoutParams();
            params.gravity = valign.value();
            textView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
