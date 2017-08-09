package kr.co.windowfun;

import android.util.Log;
import android.view.View;

import kr.co.windowfun.widget.__CContent;
import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-08-04.
 */

class __demo extends __main2 {
    @Override
    protected void init() {
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        super.init();
        ((__CContent) findViewById(R.id.c1)).setContents(null);
        ((__CContent) findViewById(R.id.c3)).setContents(null);
        ((__CContent) findViewById(R.id.c4)).setContents(null);
        ((__CContent) findViewById(R.id.c5)).setContents(null);
        rand(); //test
    }

    protected void rand() {  //test
        Log.wtf(__CLASSNAME__, getMethodName());
        for (final __ImageView i : images) {
            i.rand(); //test
        }
        for (final __VideoView v : videos) {
            if (!v.isPlaying()) v.rand(); //test
        }
    }

    @Override
    public void onPrepared(__CContent c, View v) {
        Log.wtf(__CLASSNAME__, "onPrepared()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
    }

    @Override
    public void onError(__CContent c, View v) {
        Log.wtf(__CLASSNAME__, "onError()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        super.onError(c, v);
        //rand(); //test
    }

    @Override
    public void onCompletion(__CContent c, View v) {
        Log.wtf(__CLASSNAME__, "onCompletion()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        rand(); //test
    }
}
