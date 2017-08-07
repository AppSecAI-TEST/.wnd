package kr.co.windowfun.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by isyuun on 7/31/2017.
 */
@Deprecated
public abstract class CTextView {
    public static android.widget.TextView with(CText text, Context context) {
        //Log.wtf("[[kr.co.windowfun.widget.CTextView]]", _text + ":" + context);
        return text.with(context);
    }

    public static android.widget.TextView with(String type, Context context) {
        CText text = CText.valueOf(type);
        //Log.wtf("[[kr.co.windowfun.widget.CTextView]]", _text + ":" + context);
        return text.with(context);
    }

    public static android.widget.TextView with(CText text, Context context, AttributeSet attrs) {
        //Log.wtf("[[kr.co.windowfun.widget.CTextView]]", _text + ":" + context + ", " + context + ", " + attrs);
        return text.with(context, attrs);
    }

    public static android.widget.TextView with(String type, Context context, AttributeSet attrs) {
        CText text = CText.valueOf(type);
        //Log.wtf("[[kr.co.windowfun.widget.CTextView]]", _text + ":" + context + ", " + context + ", " + attrs);
        return text.with(context, attrs);
    }

    public static android.widget.TextView with(CText text, Context context, AttributeSet attrs, int defStyleAttr) {
        //Log.wtf("[[kr.co.windowfun.widget.CTextView]]", _text + ":" + context + ", " + context + ", " + attrs + ", " + defStyleAttr);
        return text.with(context, attrs, defStyleAttr);
    }

    public static android.widget.TextView with(String type, Context context, AttributeSet attrs, int defStyleAttr) {
        CText text = CText.valueOf(type);
        //Log.wtf("[[kr.co.windowfun.widget.CTextView]]", _text + ":" + context + ", " + context + ", " + attrs + ", " + defStyleAttr);
        return text.with(context, attrs, defStyleAttr);
    }
}
