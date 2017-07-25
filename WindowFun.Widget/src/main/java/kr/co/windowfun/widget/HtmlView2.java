package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by isyoon on 2017-07-19.
 */

public class HtmlView2 extends HtmlView implements _Content, _TAG {
    public HtmlView2(Context context) {
        super(context);
    }

    public HtmlView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HtmlView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    private int index;

    private ArrayList<String> path = new ArrayList<>();

    public HtmlView2 path(ArrayList<String> path) {
        this.path = path;
        this.index = 0;
        return this;
    }

    @Override
    public void open(Uri uri) {
        loadUrl(uri.toString());
    }

    private void open() {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + this.index + ":" + this.path);
        try {
            if (HtmlView2.this.index < HtmlView2.this.path.size()) {
                Uri uri = Uri.parse(path.get(index));
                Log.i(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
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
            //int r = HtmlView2.this.r.nextInt(TIMER_JPG_LONG - TIMER_JPG_SHORT + 1) + TIMER_JPG_SHORT;
            //if (HtmlView2.this.index < HtmlView2.this.path.size()) {
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
        length = -1;
        mHandler.removeCallbacks(call);
        mHandler.removeCallbacks(play);
        mHandler.removeCallbacks(prev);
        mHandler.removeCallbacks(next);
        mHandler.removeCallbacks(rand);
    }

    Random r = new Random();

    private Runnable rand = new Runnable() {
        @Override
        public void run() {
            int min = 0;
            int max = path.size() - 1;
            int index = -1;
            try {
                if (HtmlView2.this.index < HtmlView2.this.path.size()) {
                    index = r.nextInt(max - min) + min;
                    HtmlView2.this.index = index;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index);
            open();
            play();
        }
    };

    @Override
    public void rand() {
        mHandler.removeCallbacks(rand);
        mHandler.postDelayed(rand, TIMER_OPEN_SHORT);
    }

    private Runnable prev = new Runnable() {
        @Override
        public void run() {
            index--;
            if (index < 0) {
                index = path.size() - 1;
            }
            //Log.i(__CLASSNAME__, getMethodName() + ":" + index);
            open();
            play();
        }
    };

    @Override
    public void prev() {
        mHandler.removeCallbacks(prev);
        mHandler.postDelayed(prev, TIMER_OPEN_SHORT);
    }

    private Runnable next = new Runnable() {
        @Override
        public void run() {
            index++;
            if (index > path.size() - 1) {
                index = 0;
            }
            //Log.i(__CLASSNAME__, getMethodName() + ":" + index);
            open();
            play();
        }
    };

    @Override
    public void next() {
        mHandler.removeCallbacks(next);
        mHandler.postDelayed(next, TIMER_OPEN_SHORT);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
