package kr.co.windowfun.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by isyoon on 2017-07-19.
 */

class ImageView extends android.support.v7.widget.AppCompatImageView implements _CListener {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    protected String getMethodName() {
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int idx = 0;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement item = ste[i];
            Log.i(_CLASSNAME_, "" + item.getClassName());
            if (item.getClassName().contains(_CLASSNAME_)) {
                idx = i;
                Log.w(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
    }

    public ImageView(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    _CContentListener mCOnListener;

    @Override
    public void setConListener(_CContentListener l) {
        mCOnListener = l;
    }
}
