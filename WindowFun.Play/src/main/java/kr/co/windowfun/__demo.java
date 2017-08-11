package kr.co.windowfun;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import kr.co.windowfun.widget._CContentListener;
import kr.co.windowfun.widget.__CContent;
import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__VideoView;

/**
 * 데모
 * Created by isyuun on 2017-08-04.
 */

class __demo extends __main2 implements _CContentListener, View.OnTouchListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        super.init();
        images();   //test
        videos();   //test
        rand(); //test
    }

    protected ArrayList<__ImageView> images = new ArrayList<>();
    protected ArrayList<__VideoView> videos = new ArrayList<>();

    private void images() { //test
        for (final __ImageView i : images) {
            i.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View i, MotionEvent event) {
                    //////Log.w(__CLASSNAME__, getMethodName() + ":" + i + "," + event);
                    float w = i.getWidth();
                    //float h = i.getHeight();
                    float x = event.getX();
                    //float y = event.getY();
                    if (x < w / 2) {
                        ((__ImageView) i).prev();
                    } else if (x > w / 2) {
                        ((__ImageView) i).next();
                    }
                    return false;
                }
            });
        }
    }

    private void videos() { //test
        for (final __VideoView v : videos) {
            //리스너 등록
            v.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    v.rand(); //test
                }
            });
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.wtf(__CLASSNAME__, getMethodName() + ":" + v + "," + event);
                    float w = v.getWidth();
                    //float h = v.getHeight();
                    float x = event.getX();
                    //float y = event.getY();
                    if (x < w / 2) {
                        ((__VideoView) v).prev();
                    } else if (x > w / 2) {
                        ((__VideoView) v).next();
                    }
                    for (__VideoView video : videos) {
                        if (v == video) {
                            video.mute(false);
                        } else {
                            video.mute(true);
                        }
                    }
                    return false;
                }
            });
        }
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
