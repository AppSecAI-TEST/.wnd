package kr.co.windowfun.widget;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Random;

/**
 * 컨텐츠레이아웃<br>
 * Created by isyoon on 2017-07-19.
 */

public class CLayout extends RelativeLayout implements _Content, _TAG, CListener, View.OnTouchListener {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;
    private JSONArray contents;

    protected String getMethodName() {
        ////Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int idx = 0;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement item = ste[i];
            ////Log.i(_CLASSNAME_, "" + item.getClassName());
            if (item.getClassName().contains(_CLASSNAME_)) {
                idx = i;
                //Log.v(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
    }

    public CLayout(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public CLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public CLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    private void showVideo() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((VideoView2) findViewById(R.id.video)).setVisibility(View.VISIBLE);
        ((ImageView2) findViewById(R.id.image)).setVisibility(View.INVISIBLE);
        ((TextView2) findViewById(R.id.text)).setVisibility(View.INVISIBLE);
        ((HtmlView2) findViewById(R.id.html)).setVisibility(View.INVISIBLE);
    }

    private void showImage() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((VideoView2) findViewById(R.id.video)).setVisibility(View.INVISIBLE);
        ((ImageView2) findViewById(R.id.image)).setVisibility(View.VISIBLE);
        ((TextView2) findViewById(R.id.text)).setVisibility(View.INVISIBLE);
        ((HtmlView2) findViewById(R.id.html)).setVisibility(View.INVISIBLE);
    }

    private void showText() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((VideoView2) findViewById(R.id.video)).setVisibility(View.INVISIBLE);
        ((ImageView2) findViewById(R.id.image)).setVisibility(View.INVISIBLE);
        ((TextView2) findViewById(R.id.text)).setVisibility(View.VISIBLE);
        ((HtmlView2) findViewById(R.id.html)).setVisibility(View.INVISIBLE);
    }

    private void showHtml() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((VideoView2) findViewById(R.id.video)).setVisibility(View.INVISIBLE);
        ((ImageView2) findViewById(R.id.image)).setVisibility(View.INVISIBLE);
        ((TextView2) findViewById(R.id.text)).setVisibility(View.INVISIBLE);
        ((HtmlView2) findViewById(R.id.html)).setVisibility(View.VISIBLE);
    }

    private void open() {
        try {
            Log.i(__CLASSNAME__, getMethodName() + ":" + this.index + "\n" + ((JSONObject) this.contents.get(index)).toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (CLayout.this.index < CLayout.this.contents.length()) {
                String type = ((JSONObject) contents.get(index)).getString(result_c.type);
                String title = ((JSONObject) contents.get(index)).getString(result_c.title);
                String text = ((JSONObject) contents.get(index)).getString(result_c.text);
                String filename = ((JSONObject) contents.get(index)).getString(result_c.file_name);
                String play_length = ((JSONObject) contents.get(index)).getString(result_c.play_length);
                Uri uri = Uri.parse(!TextUtils.isEmpty(filename) ? filename : text);
                //Log.w(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri + "<-" + type + ":" + title + ":" + file_name + ":" + play_length);
                open(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void open(final Uri uri) {
        final String url = _TextUtil.getFileUrl(uri.toString()) != null ? _TextUtil.getFileUrl(uri.toString()) : uri.toString();
        final String path = new File(_TextUtil.getFilePath(url)) != null ? _TextUtil.getFilePath(url) : url;

        stop();

        try {
            final String type = ((JSONObject) contents.get(index)).getString(result_c.type);
            final String effect_text = ((JSONObject) contents.get(index)).getString(result_c.effect_text);
            final String effect_play = ((JSONObject) contents.get(index)).getString(result_c.effect_play);
            if (c_type.text.equalsIgnoreCase(type)) {
                ((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + uri.toString());
            } else {
                ((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + Uri.decode(path));
            }
            //Log.i(__CLASSNAME__,getMethodName() + ":" + type + ":" + url);
            View v = this;
            switch (type) {
                case c_type.text:
                    showText();
                    ((TextView2) findViewById(R.id.text)).type(effect_text);
                    ((TextView2) findViewById(R.id.text)).open(uri);
                    //v = findViewById(R.id.text); //test
                    break;
                case c_type.image:
                    showImage();
                    //((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + Uri.decode(path));
                    ((ImageView2) findViewById(R.id.image)).open(Uri.parse(path));
                    //v = findViewById(R.id.image); //test
                    break;
                case c_type.video:
                    showVideo();
                    //((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + Uri.decode(path));
                    ((VideoView2) findViewById(R.id.video)).open(Uri.parse(path));
                    //v = findViewById(R.id.video); //test
                    break;
                case c_type.html:
                    showHtml();
                    //((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + Uri.decode(path));
                    ((HtmlView2) findViewById(R.id.html)).open(Uri.parse(path));
                    //v = findViewById(R.id.html); //test
                    break;
                default:
                    break;
            }
            YoYo.with(Techniques.valueOf(effect_play))
                    .duration(1000)
                    .onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            //Log.wtf(__CLASSNAME__, "YoYo.onEnd()" + ":" + type + ":" + effect_text + ":" + effect_play + ":" + uri + ":" + path);
                        }
                    })
                    .playOn(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable play = new Runnable() {
        @Override
        public void run() {
            try {
                String type = ((JSONObject) contents.get(index)).getString(result_c.type);
                String play_length = ((JSONObject) contents.get(index)).getString(result_c.play_length);
                int length = Integer.parseInt(play_length) * 1000;
                //length /= 10; //test
                //Log.w(__CLASSNAME__,getMethodName() + ":" + type);
                switch (type) {
                    case c_type.text:
                        showText();
                        ((TextView2) findViewById(R.id.text)).play(length);
                        break;
                    case c_type.image:
                        showImage();
                        ((ImageView2) findViewById(R.id.image)).play(length);
                        break;
                    case c_type.video:
                        showVideo();
                        ((VideoView2) findViewById(R.id.video)).play();
                        break;
                    case c_type.html:
                        showHtml();
                        ((HtmlView2) findViewById(R.id.html)).play();
                        break;
                    default:
                        break;
                }
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
    public void play(int length) {
    }

    @Override
    public void stop() {
        Log.w(__CLASSNAME__, getMethodName());
        mHandler.removeCallbacks(play);
        mHandler.removeCallbacks(prev);
        mHandler.removeCallbacks(next);
        mHandler.removeCallbacks(rand);
        ((TextView2) findViewById(R.id.text)).stop();
        ((ImageView2) findViewById(R.id.image)).stop();
        ((VideoView2) findViewById(R.id.video)).stop();
        ((HtmlView2) findViewById(R.id.html)).stop();
    }

    Random r = new Random();

    private Runnable rand = new Runnable() {

        @Override
        public void run() {
            int min = 0;
            int max = contents.length() - 1;
            int index = -1;
            try {
                if (CLayout.this.index < CLayout.this.contents.length()) {
                    index = r.nextInt(max - min) + min;
                    CLayout.this.index = index;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.w(__CLASSNAME__, getMethodName() + ":" + VideoView2.this.index);
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
                index = contents.length() - 1;
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
            if (index > contents.length() - 1) {
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

    protected String toString(JSONObject response, int indentSpaces) {
        try {
            return response.toString(indentSpaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String toString(JSONArray response, int indentSpaces) {
        try {
            return response.toString(indentSpaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    int index = -1;

    public void setContents(JSONArray contents) {
        //Log.i(__CLASSNAME__, getMethodName() + "\n[contents]\n" + toString(contents, 2));
        this.contents = contents;
        index = 0;
        //CListener
        ((VideoView2) findViewById(R.id.video)).set(this);
        ((ImageView2) findViewById(R.id.image)).set(this);
        ((TextView2) findViewById(R.id.text)).set(this);
        ((HtmlView2) findViewById(R.id.html)).set(this);
        //OnTouchListener
        ((VideoView2) findViewById(R.id.video)).setOnTouchListener(this);
        ((ImageView2) findViewById(R.id.image)).setOnTouchListener(this);
        ((TextView2) findViewById(R.id.text)).setOnTouchListener(this);
        ((HtmlView2) findViewById(R.id.html)).setOnTouchListener(this);
        open();
        play();
        //rand(); //test
    }


    @Override
    public void onPrepared(View v) {
        Log.w(__CLASSNAME__, getMethodName());
    }

    @Override
    public void onError(View v) {
        Log.w(__CLASSNAME__, getMethodName());
        next();
        //rand(); //test
    }

    @Override
    public void onCompletion(View v) {
        Log.w(__CLASSNAME__, getMethodName());
        next();
        //rand(); //test
    }

    OnTouchListener mOnTouchListener;

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
        mOnTouchListener = l;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + v + "," + event);
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
}
