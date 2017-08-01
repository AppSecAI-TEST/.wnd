package kr.co.windowfun.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by isyoon on 2017-07-19.
 */

class TextView extends asia.ivity.android.marqueeview.MarqueeView implements _Listener {
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

    //protected Animation animation;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //this.animation = new TranslateAnimation(
        //        Animation.RELATIVE_TO_SELF, 0f,
        //        Animation.RELATIVE_TO_SELF, -1f,
        //        Animation.RELATIVE_TO_SELF, 0f,
        //        Animation.RELATIVE_TO_SELF, 0f
        //);
        //this.animation.setRepeatCount(Animation.INFINITE);
    }

    int durationMillis;

    public void setDuration(int durationMillis) {
        this.durationMillis = durationMillis;
        //this.animation.setDuration(durationMillis);
    }

    public void startAnimation() {
        //startAnimation(this.animation);
    }

    public void stopAnimation() {
        clearAnimation();
    }

    protected android.widget.TextView getTextView() {
        return null;
    }
}
