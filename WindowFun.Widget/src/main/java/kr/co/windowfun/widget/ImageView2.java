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

public class ImageView2 extends ImageView implements _Content, _TAG {
    public ImageView2(Context context) {
        super(context);
    }

    public ImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    boolean resize = false;
    //public ImageView2 resize(boolean resize) {
    //    this.resize = resize;
    //    return this;
    //}

    private int h = 0;
    private int w = 0;

    private void setDimensions(int w, int h) {
        this.h = h;
        this.w = w;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ////Log.wtf(__CLASSNAME__, getMethodName() + ":" + widthMeasureSpec + ":" + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (resize && w > 0 && h > 0) {
            setMeasuredDimension(w, h);
        }
    }

    private int index;

    private ArrayList<String> path = new ArrayList<>();

    public ImageView2 path(ArrayList<String> path) {
        this.path = path;
        this.index = 0;
        return this;
    }

    @Override
    public void open(Uri uri) {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + index + "(" + "w:" + getMeasuredWidth() + ", h:" + getMeasuredHeight() + ")" + uri);
        try {
            //setDimensions(getWidth(), getHeight());
            //if (uri.toString().contains((".gif"))) {
            //    //bumptech/glide
            //    GlideApp.with(getContext())
            //            .load(uri)
            //            .override(getMeasuredWidth(), getMeasuredHeight())
            //            .into(this);
            //} else {
            //    //square/picasso
            //    Picasso.with(getContext())
            //            .load(uri)
            //            .resize(getMeasuredWidth(), getMeasuredHeight())
            //            .into(this);
            //}
            //bumptech/glide
            GlideApp.with(getContext())
                    .load(uri)
                    .override(getMeasuredWidth(), getMeasuredHeight())
                    .into(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
    }

    private Runnable play = new Runnable() {
        @Override
        public void run() {
            Uri uri = Uri.parse(path.get(index).toString());
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
            open(uri);
            mHandler.removeCallbacks(rand);
            int r = ImageView2.this.r.nextInt(5000 - 3000 + 1) + 3000;
            if (uri.toString().contains((".gif"))) {
                Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
                r = 10000;
            }
            mHandler.postDelayed(rand, r);
        }
    };


    @Override
    public void play() {
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_OPEN_SHORT);
    }

    @Override
    public void stop() {
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
            do {
                index = r.nextInt(max - min) + min;
            } while (index == ImageView2.this.index);
            ImageView2.this.index = index;
            ////Log.e(__CLASSNAME__, getMethodName() + ":" + index);
            play();
        }
    };

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
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index);
            play();
        }
    };

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
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index);
            play();
        }
    };

    public void next() {
        mHandler.removeCallbacks(next);
        mHandler.postDelayed(next, TIMER_OPEN_SHORT);
    }

    private Runnable pause = new Runnable() {

        @Override
        public void run() {
        }
    };

    @Override
    public void pause() {
        mHandler.removeCallbacks(pause);
        mHandler.postDelayed(pause, TIMER_OPEN_SHORT);
    }

    private Runnable resume = new Runnable() {

        @Override
        public void run() {
        }
    };

    @Override
    public void resume() {
        mHandler.removeCallbacks(resume);
        mHandler.postDelayed(resume, TIMER_OPEN_SHORT);
    }
}
