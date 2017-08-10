package kr.co.windowfun.app;

/**
 * Created by isyuun on 2017-07-17.
 */

class Application extends android.app.Application {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    protected String getMethodName() {
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]][ST]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String reg = "[^A-Za-z0-9.]";
        int i;
        StackTraceElement item = null;
        for (i = 0; i < ste.length; i++) {
            item = ste[i];
            if (item.getMethodName().equalsIgnoreCase("getMethodName")) continue;
            if (item.getMethodName().contains("access$")) continue;
            //Log.v(_CLASSNAME_.replaceAll(reg, ""), item.getClassName().replaceAll(reg, "") + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
            if (item.getClassName().replaceAll(reg, "").contains(_CLASSNAME_.replaceAll(reg, ""))) {
                //Log.i(_CLASSNAME_, item.getClassName() + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
                break;
            }
        }
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]][ED]");
        return "" + item;
    }

    public Application() {
        super();
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }
}
