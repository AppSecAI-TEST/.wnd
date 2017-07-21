package kr.co.windowfun.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by isyoon on 2017-07-04.
 */

public class VideoView2 extends VideoView implements _Content, _TAG {
    MediaPlayer.OnPreparedListener mOnPreparedListener;

    public VideoView2(Context context) {
        super(context);
    }

    public VideoView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VideoView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        super.setOnPreparedListener(l);
        mOnPreparedListener = l;
    }

    MediaPlayer.OnCompletionListener mOnCompletionListener;

    @Override
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener l) {
        super.setOnCompletionListener(l);
        mOnCompletionListener = l;
    }

    MediaPlayer.OnErrorListener mOnErrorListener;

    @Override
    public void setOnErrorListener(MediaPlayer.OnErrorListener l) {
        //isyoon:기본ERROR처리
        //super.setOnErrorListener(l);
        mOnErrorListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    public boolean mute = true;

    public VideoView2 mute(boolean mute) {
        this.mute = mute;
        try {
            if (this.mp != null) {
                if (this.mute) {
                    mp.setVolume(0.0f, 0.0f);
                } else {
                    mp.setVolume(1.0f, 1.0f);
                }
            }
        } catch (Exception e) {
        }
        return this;
    }

    ///**
    // * isyoon:쓰지마!!!
    // */
    //@Deprecated
    //public void setVideoURI(Uri uri) {/*super.setVideoURI(uri)*/ }
    //
    ///**
    // * isyoon:쓰지마!!!
    // */
    //@Deprecated
    //public void start() { /*super.start()*/ }

    boolean resize = true;
    //public VideoView2 resize(boolean resize) {
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

    public VideoView2 path(ArrayList<String> path) {
        this.path = path;
        this.index = 0;
        //Log.e(__CLASSNAME__, getMethodName() + ":" + this.index + ":" + this.path + ":" + path);
        return this;
    }

    MediaPlayer mp;

    @Override
    public void open(final Uri uri) {
        Log.e(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
        super.setVideoURI(uri);
        super.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri + "\t" + VideoView2.this + ":" + what + "," + extra);
                //Toast.makeText(getContext(), getMethodName() + ":" + mp + "," + what + "," + extra, Toast.LENGTH_SHORT).show();
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError(mp, what, extra);
                }
                rand();
                return true;
            }
        });
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //Log.w(__CLASSNAME__, getMethodName() + ":" + ",w:" + getWidth() + ",h:" + getHeight());
                setDimensions(getWidth(), getHeight());
                VideoView2.this.mp = mp;
                VideoView2.this.mute(VideoView2.this.mute);
            }
        });
    }

    private void open() {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + this.index + ":" + this.path);
        try {
            if (VideoView2.this.index < VideoView2.this.path.size()) {
                Uri uri = Uri.parse(path.get(index).toString());
                Log.w(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
                open(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable play = new Runnable() {
        @Override
        public void run() {
            try {
                VideoView2.super.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void play() {
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_OPEN_SHORT);
    }

    @Override
    public void stop() {
        stopPlayback();
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
                if (VideoView2.this.index < VideoView2.this.path.size()) {
                    index = r.nextInt(max - min) + min;
                    VideoView2.this.index = index;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.e(__CLASSNAME__, getMethodName() + ":" + VideoView2.this.index);
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
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index);
            open();
            play();
        }
    };

    @Override
    public void next() {
        mHandler.removeCallbacks(next);
        mHandler.postDelayed(next, TIMER_OPEN_SHORT);
    }

    int cur;

    private Runnable pause = new Runnable() {

        @Override
        public void run() {
            cur = getCurrentPosition(); //cur is an int
            VideoView2.super.pause();
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
            seekTo(cur);
            VideoView2.super.start(); //Or use resume() if it doesn't work. I'm not sure
        }
    };

    @Override
    public void resume() {
        mHandler.removeCallbacks(resume);
        mHandler.postDelayed(resume, TIMER_OPEN_SHORT);
    }
}
