package kr.co.windowfun.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.hanks.htextview.EvaporateTextView2;
import com.hanks.htextview.FadeTextView2;
import com.hanks.htextview.FallTextView2;
import com.hanks.htextview.LineTextView2;
import com.hanks.htextview.RainbowTextView2;
import com.hanks.htextview.ScaleTextView2;
import com.hanks.htextview.TyperTextView2;

/**
 * Created by isyuun on 7/31/2017.
 */

public enum CText {
    //"line|fade|typer|rainbow|scale|evaporate|fall"
    plane(android.widget.TextView.class),
    //line(android.widget.TextView.class),
    line(LineTextView2.class),
    fade(FadeTextView2.class),
    typer(TyperTextView2.class),
    rainbow(RainbowTextView2.class),
    scale(ScaleTextView2.class),
    evaporate(EvaporateTextView2.class),
    fall(FallTextView2.class);

    private Class textClass;

    private CText(Class clazz) {
        this.textClass = clazz;
    }

    public android.widget.TextView getText(Context context) {
        try {
            Class[] parameterTypes = {Context.class};
            return (android.widget.TextView) this.textClass.getConstructor(parameterTypes).newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public android.widget.TextView getText(Context context, AttributeSet attrs){
        try {
            Class[] parameterTypes = {Context.class, AttributeSet.class};
            return (android.widget.TextView) this.textClass.getConstructor(parameterTypes).newInstance(context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public android.widget.TextView getText(Context context, AttributeSet attrs, int defStyleAttr) {
        try {
            Class[] parameterTypes = {Context.class, AttributeSet.class, int.class};
            return (android.widget.TextView) this.textClass.getConstructor(parameterTypes).newInstance(context, attrs, defStyleAttr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
