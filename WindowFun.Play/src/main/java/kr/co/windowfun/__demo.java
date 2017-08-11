package kr.co.windowfun;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import kr.co.windowfun.widget._CContentListener;
import kr.co.windowfun.widget.__CContent;
import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-08-04.
 */

class __demo extends __main implements _CContentListener, View.OnTouchListener {

    @Override
    protected void init() {
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        super.init();
        rand(); //test
    }

    private void rand() {  //test
        Log.wtf(__CLASSNAME__, getMethodName());
        for (final __ImageView i : images) {
            i.rand(); //test
        }
        for (final __VideoView v : videos) {
            if (!v.isPlaying()) v.rand(); //test
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v + ":" + event);
        rand(); //test
        return false;
    }

    @Override
    public void onPrepared(__CContent c, View v) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
    }

    @Override
    public void onError(__CContent c, View v) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        //rand(); //test
    }

    @Override
    public void onCompletion(__CContent c, View v) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        rand(); //test
    }
}
