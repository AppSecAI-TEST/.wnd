package kr.co.windowfun;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__TextView;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-07-03.
 */

class __main extends _Activity implements View.OnTouchListener {
    protected ArrayList<__TextView> texts = new ArrayList<>();
    protected ArrayList<String> txt = new ArrayList<>();
    protected ArrayList<String> jpg = new ArrayList<>();
    protected ArrayList<String> mp4 = new ArrayList<>();

    private void path() {
        String root_mp4 = root + "/_mp4";
        Log.wtf(__CLASSNAME__, getMethodName() + "List: " + root_mp4);
        File directory = new File(root_mp4);
        File[] files = directory.listFiles();
        Log.w(__CLASSNAME__, getMethodName() + "List: " + files);
        if (files != null) {
            ////Log.d(__CLASSNAME__, "Size: "+ files.length);
            for (int i = 0; i < files.length; i++) {
                //Log.d(__CLASSNAME__, getMethodName() + "PathName:" + files[i].getPath());
                if (files[i].isFile()) mp4.add(files[i].getPath());
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    private void pause() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    private void resume() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mHandler.postDelayed(init, TIMER_MSEC_1H);
    }

    private Runnable init = new Runnable() {
        @Override
        public void run() {
            init();
        }
    };

    protected void init() {
        if (!requestForPermission()) {
            return;
        }
        path();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
