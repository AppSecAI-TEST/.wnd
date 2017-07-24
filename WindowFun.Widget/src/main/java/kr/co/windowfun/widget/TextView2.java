package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by isyoon on 2017-07-13.
 */

public class TextView2 extends TextView implements _Content, _TAG {

    public TextView2(Context context) {
        super(context);
    }

    public TextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    private int index;

    private ArrayList<String> path = new ArrayList<>();

    public TextView2 path(ArrayList<String> path) {
        this.path = path;
        this.index = 0;
        return this;
    }

    @Override
    public void open(Uri uri) {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + uri);
        setText(uri.toString());
    }

    private void setText(String text) {

    }

    private void open() {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + this.index + ":" + this.path);
        try {
            if (TextView2.this.index < TextView2.this.path.size()) {
                Uri uri = Uri.parse(path.get(index));
                Log.w(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
                open(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int length = -1;
    private Runnable play = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(call);
            int r = TextView2.this.r.nextInt(TIMER_JPG_LONG - TIMER_JPG_SHORT + 1) + TIMER_JPG_SHORT;
            //if (ImageView2.this.index < ImageView2.this.path.size()) {
            //    Uri uri = Uri.parse(path.get(index));
            //    if (uri.toString().contains((".gif"))) {
            //        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
            //        r = TIMER_GIF_SHORT;
            //        r = TIMER_GIF_LONG;
            //    }
            //}
            mHandler.postDelayed(call, length);
        }
    };


    private Runnable call = new Runnable() {
        @Override
        public void run() {
            if (mContentListener != null) mContentListener.onCompletion();
        }
    };

    @Override
    public void play() {
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_OPEN_SHORT);
    }

    @Override
    public void play(int length) {
        this.length = length;
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_OPEN_SHORT);
    }

    @Override
    public void stop() {
        //mHandler.removeCallbacks(play);
        //mHandler.removeCallbacks(prev);
        //mHandler.removeCallbacks(next);
        //mHandler.removeCallbacks(rand);
    }

    Random r = new Random();

    private Runnable rand = new Runnable() {
        @Override
        public void run() {
            int min = 0;
            int max = path.size() - 1;
            int index = -1;
            try {
                if (TextView2.this.index < TextView2.this.path.size()) {
                    index = r.nextInt(max - min) + min;
                    TextView2.this.index = index;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.e(__CLASSNAME__, getMethodName() + ":" + index);
            open();
            play();
        }
    };

    @Override
    public void rand() {
        mHandler.removeCallbacks(rand);
        mHandler.postDelayed(rand, TIMER_OPEN_SHORT);
    }

    @Override
    public void next() {

    }

    @Override
    public void prev() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
