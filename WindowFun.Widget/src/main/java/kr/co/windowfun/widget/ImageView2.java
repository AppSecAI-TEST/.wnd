package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.request.RequestOptions;

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

    OnTouchListener mOnTouchListener;

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
        mOnTouchListener = l;
    }

    @Override
    public void open(Uri uri) {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + index + "(" + "w:" + getMeasuredWidth() + ", h:" + getMeasuredHeight() + ")" + uri);
        try {
            int w = getMeasuredWidth();
            int h = getMeasuredHeight();
            //square/picasso
            //Picasso.with(getContext())
            //        .load(uri)
            //        .resize(getMeasuredWidth(), getMeasuredHeight())
            //        .into(this);
            //Glide.get(getContext()).clearDiskCache(); //test
            //option
            RequestOptions options = new RequestOptions()
                    //.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .override(getMeasuredWidth(), getMeasuredHeight())
                    .lock();
            //if (uri.toString().contains((".gif"))) {
            //    Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + "(" + "w:" + w + ", h:" + h + ")" + uri);
            //    uri = Uri.parse(uri.toString() + "?w=" + w + "&h=" + h);
            //    options.downsample(DownsampleStrategy.FIT_CENTER);
            //}
            //bumptech/glide
            GlideApp.with(getContext())
                    .applyDefaultRequestOptions(options)
                    .load(uri)
                    .into(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.e(__CLASSNAME__, getMethodName() + ":" + v + "," + event);
                float w = v.getWidth();
                //float h = v.getHeight();
                float x = event.getX();
                //float y = event.getY();
                if (x < w / 2) {
                    /*((VideoView2) v).*/
                    prev();
                } else if (x > w / 2) {
                    /*((VideoView2) v).*/
                    next();
                }
                if (mOnTouchListener != null) {
                    mOnTouchListener.onTouch(v, event);
                }
                return false;
            }
        });
    }

    private void open() {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + this.index + ":" + this.path);
        try {
            if (ImageView2.this.index < ImageView2.this.path.size()) {
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
            int r = ImageView2.this.r.nextInt(TIMER_JPG_LONG - TIMER_JPG_SHORT + 1) + TIMER_JPG_SHORT;
            if (ImageView2.this.index < ImageView2.this.path.size()) {
                Uri uri = Uri.parse(path.get(index));
                if (uri.toString().contains((".gif"))) {
                    //Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
                    r = TIMER_GIF_SHORT;
                    r = TIMER_GIF_LONG;
                }
            }
            if (length < 0) {
                length = r;
            }
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
                if (ImageView2.this.index < ImageView2.this.path.size()) {
                    index = r.nextInt(max - min) + min;
                    ImageView2.this.index = index;
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

    private Runnable prev = new Runnable() {
        @Override
        public void run() {
            index--;
            if (index < 0) {
                index = path.size() - 1;
            }
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index);
            open();
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
            open();
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
