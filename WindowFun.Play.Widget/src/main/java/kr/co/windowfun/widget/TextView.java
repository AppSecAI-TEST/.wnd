package kr.co.windowfun.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;

/**
 * Created by isyoon on 2017-07-19.
 */

class TextView extends HorizontalScrollView implements _CListener {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    protected String getMethodName() {
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String reg = "[^A-Za-z0-9.]";
        int i;
        StackTraceElement item = null;
        for (i = 0; i < ste.length; i++) {
            item = ste[i];
            if (item.getMethodName().equalsIgnoreCase("getMethodName")) continue;
            //Log.v(_CLASSNAME_.replaceAll(reg, ""), item.getClassName().replaceAll(reg, "") + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
            if (item.getClassName().replaceAll(reg, "").contains(_CLASSNAME_.replaceAll(reg, ""))) {
                //Log.i(_CLASSNAME_, item.getClassName() + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
                break;
            }
        }
        return item.toString();
    }

    public TextView(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public TextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    _CContentListener mCOnListener;

    @Override
    public void setConListener(_CContentListener l) {
        mCOnListener = l;
    }

    protected android.widget.TextView textView;

    Animation animation;

    protected void setText(String text) {
        //animation
        this.animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        this.animation.setRepeatCount(Animation.INFINITE);
        this.animation.setRepeatMode(Animation.RESTART);
    }

    int durationMillis;

    public void setDuration(int durationMillis) {
        this.durationMillis = durationMillis;
        this.animation.setDuration(durationMillis);
    }

    public void startMarquee() {
        //Log.wtf(__CLASSNAME__, getMethodName());
        try {
            if (textView != null) textView.startAnimation(animation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMarquee() {
        //Log.wtf(__CLASSNAME__, getMethodName());
        try {
            if (textView != null) textView.clearAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
