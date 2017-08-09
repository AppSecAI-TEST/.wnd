package kr.co.windowfun.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * 기본액티비티
 * Created by isyoon on 2017-07-06.
 */

class Activity extends android.app.Activity {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    public Activity() {
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

    protected boolean isACTIONMAIN() {
        return Intent.ACTION_MAIN.equalsIgnoreCase(getIntent().getAction());
    }

    public String getResourceEntryName(int resid) {
        try {
            return getResources().getResourceEntryName(resid);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
