package kr.co.windowfun.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import kr.co.windowfun._DEF;

/**
 * Created by isyoon on 2017-07-04.
 */

class VideoView2 extends VideoView implements _CContent, _DEF {
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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        //super.setOnPreparedListener(l);
        mOnPreparedListener = l;
    }

    MediaPlayer.OnCompletionListener mOnCompletionListener;

    @Override
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener l) {
        //super.setOnCompletionListener(l);
        mOnCompletionListener = l;
    }

    MediaPlayer.OnErrorListener mOnErrorListener;

    @Override
    public void setOnErrorListener(MediaPlayer.OnErrorListener l) {
        //super.setOnErrorListener(l);
        mOnErrorListener = l;
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

    private void setDimension(int w, int h) {
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
        //Log.w(__CLASSNAME__, getMethodName() + ":" + this.index + "->" + this.path + ":" + path);
        return this;
    }

    MediaPlayer mp;

    Uri uri;

    @Override
    public Uri uri() {
        return this.uri;
    }

    @Override
    public void open(final Uri uri) {
        this.uri = uri;
        //Log.w(__CLASSNAME__, getMethodName() + ":" + index + "->" + Uri.decode(uri.toString()));
        super.setVideoURI(uri);
        super.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(__CLASSNAME__, "MediaPlayer.OnError" + "(" + what + ", " + extra + ")" + ":" + index + "->" + Uri.decode(uri.toString()));
                ////Toast.makeText(getContext(), getMethodName() + "(" + what + "," + extra + ")" + extra, Toast.LENGTH_SHORT).show();
                //if (mOnErrorListener != null) {
                //    mOnErrorListener.onError(mp, what, extra);
                //}
                if (mCOnListener != null) mCOnListener.onError((__CContent) getParent(), VideoView2.this);
                return true;
            }
        });
        super.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e(__CLASSNAME__, "MediaPlayer.onPrepared()" + "(" + "w:" + getWidth() + ",h:" + getHeight() + ")" + ":" + index + "->" + Uri.decode(uri.toString()));
                setDimension(getWidth(), getHeight());
                VideoView2.this.mp = mp;
                mute(mute);
                //if (mOnPreparedListener != null) {
                //    mOnPreparedListener.onPrepared(mp);
                //}
                if (mCOnListener != null) mCOnListener.onPrepared((__CContent) getParent(), VideoView2.this);
            }
        });
        super.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e(__CLASSNAME__, "MediaPlayer.onCompletion()" + "(" + "w:" + getWidth() + ",h:" + getHeight() + ")" + ":" + index + "->" + Uri.decode(uri.toString()));
                //if (mOnCompletionListener != null) {
                //    mOnCompletionListener.onCompletion(mp);
                //}
                if (mCOnListener != null) mCOnListener.onCompletion((__CContent) getParent(), VideoView2.this);
            }
        });
    }

    private  Runnable prepare = new Runnable() {
        @Override
        public void run() {
            setDimension(getWidth(), getHeight());
            mute(mute);
        }
    };

    private void prepare() {
        mHandler.removeCallbacks(prepare);
        mHandler.postDelayed(prepare, TIMER_OPEN_SHORT);
    }

    private void open() {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + this.index + "->" + this.path);
        try {
            if (index > -1 && index < path.size()) {
                Uri uri = Uri.parse(path.get(index));
                Log.i(__CLASSNAME__, getMethodName() + ":" + index + "->" + uri);
                open(uri);
            } else {
                setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        super.start();
    }

    int length = -1;
    private Runnable play = new Runnable() {
        @Override
        public void run() {
            try {
                VideoView2.this.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable call = new Runnable() {
        @Override
        public void run() {
            if (mCOnListener != null) mCOnListener.onCompletion((__CContent) getParent(), VideoView2.this);
        }
    };

    @Override
    public void play() {
        mHandler.removeCallbacks(play);
        mHandler.postDelayed(play, TIMER_OPEN_SHORT);
    }

    @Override
    public void play(int length) {

    }

    @Override
    public void stop() {
        stopPlayback();
        length = -1;
        mHandler.removeCallbacks(call);
        mHandler.removeCallbacks(play);
        mHandler.removeCallbacks(prev);
        mHandler.removeCallbacks(next);
        mHandler.removeCallbacks(rand);
        //video
        mHandler.removeCallbacks(prepare);
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
    public boolean paused() {
        return !isPlaying();
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
