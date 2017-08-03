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
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Random;

import kr.co.windowfun._DEF;
import kr.co.windowfun._ENUM;
import kr.co.windowfun._JSON;
import kr.co.windowfun.util.TextUtil;

/**
 * 컨텐츠레이아웃<br>
 * Created by isyoon on 2017-07-19.
 */

class CLayout extends RelativeLayout implements _Content, _DEF,_ENUM, _JSON, CListener, View.OnTouchListener {
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
        ((__VideoView) findViewById(R.id.video)).setVisibility(View.VISIBLE);
        ((__ImageView) findViewById(R.id.image)).setVisibility(View.INVISIBLE);
        ((__TextView) findViewById(R.id.text)).setVisibility(View.INVISIBLE);
        ((__HtmlView) findViewById(R.id.html)).setVisibility(View.INVISIBLE);
    }

    private void showImage() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).setVisibility(View.INVISIBLE);
        ((__ImageView) findViewById(R.id.image)).setVisibility(View.VISIBLE);
        ((__TextView) findViewById(R.id.text)).setVisibility(View.INVISIBLE);
        ((__HtmlView) findViewById(R.id.html)).setVisibility(View.INVISIBLE);
    }

    private void showText() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).setVisibility(View.INVISIBLE);
        ((__ImageView) findViewById(R.id.image)).setVisibility(View.INVISIBLE);
        ((__TextView) findViewById(R.id.text)).setVisibility(View.VISIBLE);
        ((__HtmlView) findViewById(R.id.html)).setVisibility(View.INVISIBLE);
    }

    private void showHtml() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).setVisibility(View.INVISIBLE);
        ((__ImageView) findViewById(R.id.image)).setVisibility(View.INVISIBLE);
        ((__TextView) findViewById(R.id.text)).setVisibility(View.INVISIBLE);
        ((__HtmlView) findViewById(R.id.html)).setVisibility(View.VISIBLE);
    }


    Runnable showBanner = new Runnable() {
        @Override
        public void run() {
            String type = null;
            String text = null;
            String effect_text = null;
            String effect_play = null;
            String text_font;
            String text_line;
            String text_size;
            String text_valign;

            try {
                type = ((JSONObject) contents.get(index)).getString(result_c.type);
                if (c_type.text == c_type.valueOf(type)) return;
                text = ((JSONObject) contents.get(index)).getString(result_c.text);
                effect_text = ((JSONObject) contents.get(index)).getString(result_c.effect_text);
                effect_play = ((JSONObject) contents.get(index)).getString(result_c.effect_play);
                //text_...
                text_font = ((JSONObject) contents.get(index)).getString(result_c.text_font);
                text_line = ((JSONObject) contents.get(index)).getString(result_c.text_line);
                text_size = ((JSONObject) contents.get(index)).getString(result_c.text_size);
                text_valign = ((JSONObject) contents.get(index)).getString(result_c.text_valign);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                text = "[TEST]" + getResources().getString(R.string.text_default_text) + "[TEST]"; //test
                effect_text = "rainbow"; //test
                text_font = "font:TBD";  //test
                text_line = "false"; //test
                text_size = "xxlarge"; //test
                text_valign = "bottom"; //test

                if (!TextUtils.isEmpty(text)) {
                    //Log.wtf(__CLASSNAME__, getMethodName() + ":" + type + ":" + text + ":" + effect_text + ":" + effect_play);
                    ((__TextView) findViewById(R.id.text)).open(Uri.parse(text), effect_text);
                    ((__TextView) findViewById(R.id.text)).textFont(text_font);
                    ((__TextView) findViewById(R.id.text)).textLine(Boolean.parseBoolean(text_line));
                    ((__TextView) findViewById(R.id.text)).textSize(_ENUM.text_size.valueOf(text_size));
                    ((__TextView) findViewById(R.id.text)).textVAlign(_ENUM.text_valign.valueOf(text_valign));
                    //start
                    ((__TextView) findViewById(R.id.text)).setVisibility(View.VISIBLE);
                    ((__TextView) findViewById(R.id.text)).play(-1);
                    //scroll
                    ((__TextView) findViewById(R.id.text)).setOnTouchListener(null); //don't touch
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void showBanner() {
        mHandler.removeCallbacks(showBanner);
        mHandler.postDelayed(showBanner, TIMER_OPEN_NORMAL);
    }

   @Override
    public void open(final Uri uri) {
        final String url = TextUtil.getFileUrl(uri.toString()) != null ? TextUtil.getFileUrl(uri.toString()) : uri.toString();
        final String path = new File(TextUtil.getFilePath(url)) != null ? TextUtil.getFilePath(url) : url;

        stop();

        String type = null;
        String text = null;
        String effect_text = null;
        String effect_play = null;
        String text_font = null;
        String text_line = null;
        String text_size = null;
        String text_valign = null;
        try {
            type = ((JSONObject) contents.get(index)).getString(result_c.type);
            text = ((JSONObject) contents.get(index)).getString(result_c.text);
            effect_text = ((JSONObject) contents.get(index)).getString(result_c.effect_text);
            effect_play = ((JSONObject) contents.get(index)).getString(result_c.effect_play);
            //text_...
            text_font = ((JSONObject) contents.get(index)).getString(result_c.text_font);
            text_line = ((JSONObject) contents.get(index)).getString(result_c.text_line);
            text_size = ((JSONObject) contents.get(index)).getString(result_c.text_size);
            text_valign = ((JSONObject) contents.get(index)).getString(result_c.text_valign);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //label
        if (c_type.text == c_type.valueOf(type)) {
            ((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + uri.toString()); //test
        } else {
            ((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + effect_text + ":" + effect_play + ":" + Uri.decode(path)); //test
        }

        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + type + ":" + text + ":" + effect_text + ":" + effect_play + ":" + uri.toString());

        View v = this;
        switch (c_type.valueOf(type)) {
            case text:
                showText();
                ((__TextView) findViewById(R.id.text)).open(uri, effect_text);
                ((__TextView) findViewById(R.id.text)).textFont(text_font);
                ((__TextView) findViewById(R.id.text)).textLine(Boolean.parseBoolean(text_line));
                ((__TextView) findViewById(R.id.text)).textSize(_ENUM.text_size.valueOf(text_size));
                ((__TextView) findViewById(R.id.text)).textVAlign(_ENUM.text_valign.valueOf(text_valign));
                v = findViewById(R.id.text);
                break;
            case image:
                showImage();
                ((__ImageView) findViewById(R.id.image)).open(Uri.parse(path));
                v = findViewById(R.id.image);
                break;
            case video:
                showVideo();
                ((__VideoView) findViewById(R.id.video)).open(Uri.parse(path));
                v = findViewById(R.id.video);
                break;
            case html:
                showHtml();
                ((__HtmlView) findViewById(R.id.html)).open(Uri.parse(path));
                v = findViewById(R.id.html);
                break;
            default:
                break;
        }
        //banner
        if (c_type.text != c_type.valueOf(type)) showBanner();
        //effect_play
        YoYo.with(Techniques.valueOf(effect_play))
                .duration(1000)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        //Log.wtf(__CLASSNAME__, "YoYo.onEnd()" + ":" + type + ":" + effect_text + ":" + effect_play + ":" + uri + ":" + path);
                    }
                })
                .playOn(v);
    }

    @Override
    public void play() {
    }

    @Override
    public void play(int length) {
    }

    /**
     * OPEN N PLAY
     */
    private void _open() {
        try {
            Log.i(__CLASSNAME__, getMethodName() + ":" + CLayout.this.index + "\n" + ((JSONObject) CLayout.this.contents.get(index)).toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (CLayout.this.index < (contents != null ? contents.length() : 0)) {
                String type = ((JSONObject) contents.get(index)).getString(result_c.type);
                String text = ((JSONObject) contents.get(index)).getString(result_c.text);
                String filename = ((JSONObject) contents.get(index)).getString(result_c.file_name);
                Uri uri = Uri.parse(filename);
                if (c_type.text == c_type.valueOf(type)) {
                    uri = Uri.parse(text);
                }
                //Log.w(__CLASSNAME__, getMethodName() + ":" + index + "->" + type + ":" + text + ":" + filename + ":" + uri);
                open(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * OPEN N PLAY
     */
    private void _play() {
        String type = null;
        String play_length = null;
        try {
            type = ((JSONObject) contents.get(index)).getString(result_c.type);
            play_length = ((JSONObject) contents.get(index)).getString(result_c.play_length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int length = Integer.parseInt(play_length) * 1000;
            //Log.w(__CLASSNAME__,getMethodName() + ":" + type);
            switch (c_type.valueOf(type)) {
                case text:
                    showText();
                    ((__TextView) findViewById(R.id.text)).play(length);
                    break;
                case image:
                    showImage();
                    ((__ImageView) findViewById(R.id.image)).play(length);
                    break;
                case video:
                    showVideo();
                    ((__VideoView) findViewById(R.id.video)).play();
                    break;
                case html:
                    showHtml();
                    ((__HtmlView) findViewById(R.id.html)).play();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
        _open();
        _play();
    }

    @Override
    public void stop() {
        Log.w(__CLASSNAME__, getMethodName());
        //calbacks
        mHandler.removeCallbacks(prev);
        mHandler.removeCallbacks(next);
        mHandler.removeCallbacks(rand);
        //content
        ((__TextView) findViewById(R.id.text)).stop();
        ((__ImageView) findViewById(R.id.image)).stop();
        ((__VideoView) findViewById(R.id.video)).stop();
        ((__HtmlView) findViewById(R.id.html)).stop();
    }

    Random r = new Random();

    private Runnable rand = new Runnable() {

        @Override
        public void run() {
            int min = 0;
            int max = (contents != null ? contents.length() : 0) - 1;
            int index = -1;
            try {
                if (CLayout.this.index < (contents != null ? contents.length() : 0)) {
                    index = r.nextInt(max - min) + min;
                    CLayout.this.index = index;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.w(__CLASSNAME__, getMethodName() + ":" + __VideoView.this.index);
            start();
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
                index = (contents != null ? contents.length() : 0) - 1;
            }
            //Log.i(__CLASSNAME__, getMethodName() + ":" + index);
            start();
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
            if (index > (contents != null ? contents.length() : 0) - 1) {
                index = 0;
            }
            //Log.i(__CLASSNAME__, getMethodName() + ":" + index);
            start();
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
        ((__VideoView) findViewById(R.id.video)).set(this);
        ((__ImageView) findViewById(R.id.image)).set(this);
        ((__TextView) findViewById(R.id.text)).set(this);
        ((__HtmlView) findViewById(R.id.html)).set(this);
        //OnTouchListener
        ((__VideoView) findViewById(R.id.video)).setOnTouchListener(this);
        ((__ImageView) findViewById(R.id.image)).setOnTouchListener(this);
        ((__TextView) findViewById(R.id.text)).setOnTouchListener(this); //don't touch
        ((__HtmlView) findViewById(R.id.html)).setOnTouchListener(this);
        start();
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
                    /*((__VideoView) v).*/
            prev();
        } else if (x > w / 2) {
                    /*((__VideoView) v).*/
            next();
        }
        if (mOnTouchListener != null) {
            mOnTouchListener.onTouch(v, event);
        }
        return false;
    }
}
