package kr.co.windowfun;

import android.util.Log;

import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-08-04.
 */

class _demo extends __main {
    protected void rand() {  //test
        Log.wtf(__CLASSNAME__, getMethodName());
        for (final __ImageView i : images) {
            i.rand(); //test
        }
        for (final __VideoView v : videos) {
            v.rand(); //test
        }
    }
}
