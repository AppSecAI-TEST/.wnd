package kr.co.windowfun;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import kr.co.windowfun.widget._CContentListener;
import kr.co.windowfun.widget.__CContent;
import kr.co.windowfun.widget.__VideoView;

/**
 * 컨텐츠오류처리
 * Created by isyuun on 2017-08-08.
 */

class __main2 extends __main implements _CContentListener {
    @Override
    protected void init() {
        super.init();
        _contents();
        start();
    }


    protected __CContent[] contents;

    protected void _contents() {
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);

        ((__CContent) findViewById(R.id.c1)).setContents(getApp().result_c1);
        ((__CContent) findViewById(R.id.c3)).setContents(getApp().result_c3);
        ((__CContent) findViewById(R.id.c4)).setContents(getApp().result_c4);
        ((__CContent) findViewById(R.id.c5)).setContents(getApp().result_c5);

        contents = new __CContent[]{
                (__CContent) findViewById(R.id.c1),
                (__CContent) findViewById(R.id.c3),
                (__CContent) findViewById(R.id.c4),
                (__CContent) findViewById(R.id.c5)
        };

        for (__CContent c : contents) {
            c.setCListener(this);
            c.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    setShowMenu();
                    return false;
                }
            });
        }
    }

    private Map<Integer, Boolean> cc = new HashMap<>();

    private void start() {
        for (__CContent c : contents) {
            if (c.getVisibility() == View.VISIBLE) c.start();
        }
        cc.clear();
    }

    private void restart() {
        for (__CContent cc : contents) {
            cc.stop();
        }
        for (__CContent cc : contents) {
            cc.start();
        }
    }

    private Runnable show = new Runnable() {
        @Override
        public void run() {
            for (Map.Entry<Integer, Boolean> e : cc.entrySet()) {
                //System.out.println("Item : " + e.getKey() + " Count : " + e.getValue());
                if (e.getValue()) ((__CContent) findViewById(e.getKey())).show();
            }
        }
    };

    private void show(int id) {
        cc.put(id, true);
        mHandler.removeCallbacks(show);
        mHandler.postDelayed(show, TIMER_OPEN_NORMAL);
    }

    private void hide(int id) {
        cc.put(id, false);
        mHandler.removeCallbacks(show);
    }

    @Override
    public void onPrepared(__CContent c, View v) {
        Log.e(__CLASSNAME__, "onPrepared()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        //show(c.getId());
    }

    @Override
    public void onError(__CContent c, View v) {
        Log.e(__CLASSNAME__, "onError()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        int id = c.getId();
        if (id == R.id.c1) {
            Log.wtf(__CLASSNAME__, "onError()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
            restart();
        } else {
            //c.gone();
            //c.next();
        }
        //hide(c.getId());
    }

    @Override
    public void onCompletion(__CContent c, View v) {
        Log.e(__CLASSNAME__, "onCompletion()" + ":" + getResourceEntryName(c.getId()) + ":" + getResourceEntryName(v.getId()));
        ((__CContent) findViewById(c.getId())).next();
    }
}
