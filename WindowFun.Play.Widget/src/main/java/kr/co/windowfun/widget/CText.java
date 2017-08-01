package kr.co.windowfun.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.hanks.htextview.evaporate.EvaporateTextView;
import com.hanks.htextview.fade.FadeTextView;
import com.hanks.htextview.fall.FallTextView;
import com.hanks.htextview.line.LineTextView;
import com.hanks.htextview.rainbow.RainbowTextView;
import com.hanks.htextview.scale.ScaleTextView;
import com.hanks.htextview.typer.TyperTextView;

/**
 * Created by isyuun on 7/31/2017.
 */

public enum CText {
    //"line|fade|typer|rainbow|scale|evaporate|fall"
    plane(android.widget.TextView.class),
    line(LineTextView.class),
    fade(FadeTextView.class),
    typer(TyperTextView.class),
    rainbow(RainbowTextView.class),
    scale(ScaleTextView.class),
    evaporate(EvaporateTextView.class),
    fall(FallTextView.class);

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
