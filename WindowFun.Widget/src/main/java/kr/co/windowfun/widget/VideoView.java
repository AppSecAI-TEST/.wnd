package kr.co.windowfun.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by isyoon on 2017-07-19.
 */

class VideoView extends android.widget.VideoView implements _Listener {
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

    public VideoView(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    ContentListener mContentListener;
    @Override
    public void set(ContentListener l) {
        mContentListener = l;
    }
}
