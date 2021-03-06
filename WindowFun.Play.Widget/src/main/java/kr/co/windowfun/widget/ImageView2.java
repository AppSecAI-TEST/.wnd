package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kr.co.windowfun._DEF;

/**
 * Created by isyuun on 2017-07-13.
 */

class ImageView2 extends ImageView implements _CContent, _DEF {
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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
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
    public void path(String path) {
        this.path = new ArrayList<>(Arrays.asList(new String[]{path}));
        open();
    }

    Uri uri;

    @Override
    public Uri uri() {
        return this.uri;
    }

    @Override
    public void open(final Uri uri) {
        //if (!ViewCompat.isAttachedToWindow(this)) return;
        this.uri = uri;
        //Log.w(__CLASSNAME__, getMethodName() + ":" + index + "[" + "w:" + getMeasuredWidth() + ", h:" + getMeasuredHeight() + "]" + uri);
        try {
            int w = getMeasuredWidth();
            int h = getMeasuredHeight();
            //square/picasso
            //Picasso.with(getContext())
            //        .load(uri)
            //        .resize(w, h)
            //        .into(this);
            //bumptech/glide:clearDiskCache
            //Glide.get(getContext()).clearDiskCache(); //test
            //bumptech/glide:option
            RequestOptions options = new RequestOptions()
                    .override(w, h)
                    .lock();
            //bumptech/glide:GlideApp
            Object model;
            if (new File(uri.getPath()).exists()) {
                model = new File(uri.getPath());
            } else {
                model = uri;
            }
            Log.v(__CLASSNAME__, getMethodName() + ":" + index + "[" + uri.getScheme() + "]" + "[" + "w:" + w + ", h:" + h + "]" + uri);
            GlideApp.with(getContext())
                    .applyDefaultRequestOptions(options)
                    .load(model)
                    .into(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void open() {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + this.index + "->" + this.path);
        try {
            if (index > -1 && index < path.size()) {
                Uri uri = Uri.parse(path.get(index));
                //Log.i(__CLASSNAME__, getMethodName() + ":" + index + "->" + uri);
                open(uri);
            } else {
                setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int length = -1;
    private Runnable play = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(complete);
            int r = ImageView2.this.r.nextInt(TIMER_MSEC_5T - TIMER_MSEC_1T + 1) + TIMER_MSEC_1T;
            if (index > -1 && index < path.size()) {
                Uri uri = Uri.parse(path.get(index));
                if (uri.toString().contains((".gif"))) {
                    //Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + "->" + uri);
                    r = TIMER_MSEC_10T;
                    r = TIMER_MSEC_30T;
                }
            }
            if (length < 0) {
                length = r;
            }
            mHandler.postDelayed(complete, length);
        }
    };


    private Runnable complete = new Runnable() {
        @Override
        public void run() {
            if (mCOnListener != null)
                mCOnListener.onCompletion((__CContent) getParent(), ImageView2.this);
        }
    };

    @Override
    public void play() {
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_MSEC_1H);
    }

    @Override
    public void play(int length) {
        this.length = length;
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_MSEC_1H);
    }

    @Override
    public void stop() {
        length = -1;
        mHandler.removeCallbacks(complete);
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
            int idx;
            try {
                idx = r.nextInt(max - min) + min;
                if (idx > -1 && idx < path.size()) {
                    index = idx;
                    Log.wtf(__CLASSNAME__, getMethodName() + ":" + idx + ":" + path.get(idx));
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
        mHandler.postDelayed(rand, TIMER_MSEC_1H);
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

    public void prev() {
        mHandler.removeCallbacks(prev);
        mHandler.postDelayed(prev, TIMER_MSEC_1H);
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

    public void next() {
        mHandler.removeCallbacks(next);
        mHandler.postDelayed(next, TIMER_MSEC_1H);
    }

    private Runnable pause = new Runnable() {

        @Override
        public void run() {
        }
    };

    @Override
    public boolean paused() {
        return false;
    }

    @Override
    public void pause() {
        mHandler.removeCallbacks(pause);
        mHandler.postDelayed(pause, TIMER_MSEC_1H);
    }

    private Runnable resume = new Runnable() {

        @Override
        public void run() {
        }
    };

    @Override
    public void resume() {
        mHandler.removeCallbacks(resume);
        mHandler.postDelayed(resume, TIMER_MSEC_1H);
    }

    @Override
    public void show() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        setVisibility(View.INVISIBLE);
    }

    @Override
    public void gone() {
        setVisibility(View.GONE);
    }
}
