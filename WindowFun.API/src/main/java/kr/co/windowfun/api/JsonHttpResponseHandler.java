package kr.co.windowfun.api;

import android.util.Log;

/**
 * Created by isyoon on 2017-07-18.
 */

public class JsonHttpResponseHandler extends com.loopj.android.http.JsonHttpResponseHandler {
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
                Log.v(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
    }


    /**
     * Creates new JsonHttpResponseHandler, with JSON String encoding UTF-8
     */
    public JsonHttpResponseHandler() {
        super();
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

}
