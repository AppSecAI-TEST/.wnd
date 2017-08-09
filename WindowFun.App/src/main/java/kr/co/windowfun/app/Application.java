package kr.co.windowfun.app;

import android.util.Log;

/**
 * Created by isyoon on 2017-07-17.
 */

class Application extends android.app.Application {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    public Application() {
        super();
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    protected String getMethodName() {
        ////Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int idx = 0;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement item = ste[i];
            ////Log.v(_CLASSNAME_, "" + item);
            if (item.getClassName().contains(_CLASSNAME_)) {
                idx = i;
                Log.v(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
    }

}
