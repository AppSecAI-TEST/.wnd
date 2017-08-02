package kr.co.windowfun.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;

/**
 * Created by isyoon on 2017-07-19.
 */

class TextView extends HorizontalScrollView implements _Listener {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    protected String getMethodName() {
        ////Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int idx = 0;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement item = ste[i];
            ////Log.i(_CLASSNAME_, "" + item.getClassName());
            if (item.getClassName().contains(_CLASSNAME_)) {
                idx = i;
                //Log.v(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
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

    CListener mCListener;

    @Override
    public void set(CListener l) {
        mCListener = l;
    }

    protected android.widget.TextView textView;

    protected android.widget.TextView getTextView() {
        return textView;
    }

    Animation animation;

    protected void setText(String text) {
        //param(width)
        if (getTextView() != null) {
            getTextView().measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int w = getTextView().getMeasuredWidth();
            ViewGroup.LayoutParams params = getTextView().getLayoutParams();
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + getTextView() + ":" + params + ":" + "w:" + params.width + ", h:" + params.height + "->" + "w:" + w + ", h:" + h);
            params.width = w;
            getTextView().setLayoutParams(params);
        }
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
            if (getTextView() != null) getTextView().startAnimation(animation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMarquee() {
        //Log.wtf(__CLASSNAME__, getMethodName());
        try {
            if (getTextView() != null) getTextView().clearAnimation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
