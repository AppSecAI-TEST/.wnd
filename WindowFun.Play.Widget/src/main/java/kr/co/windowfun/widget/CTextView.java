package kr.co.windowfun.widget;

import android.content.Context;
import android.util.Log;

/**
 * Created by isyuun on 7/31/2017.
 */

public abstract class CTextView implements _TAG{
    public static android.widget.TextView with(Context context, CText text) {
        //Log.wtf("CTextView", "" + context + ":" + text);
        return text.getText(context);
    }

    public static android.widget.TextView with(Context context, String type) {
        //Log.wtf("CTextView", "" + context + ":" + type);
        CText text = CText.valueOf(type);
        return text.getText(context);
    }
}