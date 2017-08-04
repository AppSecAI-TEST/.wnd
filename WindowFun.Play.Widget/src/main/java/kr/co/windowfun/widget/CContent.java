package kr.co.windowfun.widget;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
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

class CContent extends RelativeLayout implements _Content, _DEF, _ENUM, _JSON {
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

    public CContent(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public CContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public CContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CContent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    @Deprecated
    @Override
    public void open(final Uri uri) {
    }

    @Deprecated
    @Override
    public void play() {
    }

    @Deprecated
    @Override
    public void play(int length) {
    }

    private void showBanner() {
        mHandler.removeCallbacks(showBanner);
        mHandler.postDelayed(showBanner, TIMER_OPEN_NORMAL);
    }

    private Runnable showBanner = new Runnable() {
        @Override
        public void run() {
            _showBanner();
        }
    };

    JSONObject item = null;
    String type = null;
    String text = null;
    String text_effect = "rainbow"; //default
    String text_font = "font:TBD"; //default
    String text_line = "false"; //default
    String text_size = "xxlarge"; //default
    String text_valign = "center"; //default
    String text_color = "#ffffffff"; //default
    String text_backcolor = "#55ff00ff"; //default
    String play_effect = null;
    String play_length = null;
    String file_name = null;

    /**
     * DON'T CALL DIRECT CALL [[USE]] {@link #start()}
     */
    private void _open() {
        try {
            Log.i(__CLASSNAME__, getMethodName() + ":" + CContent.this.index + "\n" + ((JSONObject) CContent.this.contents.get(index)).toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (index < (contents != null ? contents.length() : 0)) {
                item = contents.getJSONObject(index);
                type = !item.isNull(result_c.type) ? item.getString(result_c.type) : type;
                text = !item.isNull(result_c.text) ? item.getString(result_c.text) : text;
                //text_...
                text_effect = !item.isNull(result_c.text_effect) ? item.getString(result_c.text_effect) : text_effect;
                text_font = !item.isNull(result_c.text_font) ? item.getString(result_c.text_font) : text_font;
                text_line = !item.isNull(result_c.text_line) ? item.getString(result_c.text_line) : text_line;
                text_size = !item.isNull(result_c.text_size) ? item.getString(result_c.text_size) : text_size;
                text_valign = !item.isNull(result_c.text_valign) ? item.getString(result_c.text_valign) : text_valign;
                text_color = !item.isNull(result_c.text_color) ? item.getString(result_c.text_color) : text_color;
                text_backcolor = !item.isNull(result_c.text_color) ? item.getString(result_c.text_color) : text_color;
                //play_...
                play_effect = !item.isNull(result_c.play_effect) ? item.getString(result_c.play_effect) : play_effect;
                play_length = !item.isNull(result_c.play_length) ? item.getString(result_c.play_length) : play_length;
                //file_name
                file_name = !item.isNull(result_c.file_name) ? item.getString(result_c.file_name) : file_name;
                //uri
                Uri uri = Uri.parse(file_name);
                if (c_type.text == c_type.valueOf(type)) {
                    uri = Uri.parse(text);
                }
                //Log.w(__CLASSNAME__, getMethodName() + ":" + index + "->" + type + ":" + text + ":" + file_name + ":" + uri);
                _open(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.wtf(__CLASSNAME__, getMethodName() + e.getStackTrace());
        }
    }

    protected void _open(final Uri uri) {
        final String url = TextUtil.getFileUrl(uri.toString()) != null ? TextUtil.getFileUrl(uri.toString()) : uri.toString();
        final String path = new File(TextUtil.getFilePath(url)) != null ? TextUtil.getFilePath(url) : url;

        stop();

        //label
        if (c_type.text == c_type.valueOf(type)) {
            ((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + text_effect + ":" + play_effect + ":" + uri.toString()); //test
        } else {
            ((android.widget.TextView) findViewById(R.id.label)).setText(type + ":" + text_effect + ":" + play_effect + ":" + Uri.decode(path)); //test
        }

        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + type + ":" + text + ":" + text_effect + ":" + play_effect + ":" + uri.toString());

        View v = this;
        switch (c_type.valueOf(type)) {
            case text:
                showText();
                ((__TextView) findViewById(R.id.text)).open(uri, text_effect);
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
        text = getResources().getString(R.string.text_test_text); //test
        if (/*c_type.text != c_type.valueOf(type) && */!TextUtils.isEmpty(text)) showBanner();
        //play_effect
        YoYo.with(Techniques.valueOf(play_effect))
                .duration(1000)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        //Log.wtf(__CLASSNAME__, "YoYo.onEnd()" + ":" + type + ":" + text_effect + ":" + play_effect + ":" + uri + ":" + path);
                    }
                })
                .playOn(v);
    }

    /**
     * DON'T CALL DIRECT CALL [[USE]] {@link #showBanner()}
     */
    protected void _showBanner() {
        if (c_type.text == c_type.valueOf(type)) return;

        try {
            text = getResources().getString(R.string.text_test_text); //test
            text_effect = "rainbow"; //test
            text_font = "font:TBD";  //test
            text_line = "false"; //test
            text_size = "xxlarge"; //test
            text_valign = "bottom"; //test

            if (!TextUtils.isEmpty(text)) {
                //Log.wtf(__CLASSNAME__, getMethodName() + ":" + type + ":" + text + ":" + text_effect + ":" + play_effect);
                ((__TextView) findViewById(R.id.text)).open(Uri.parse(text), text_effect);
                ((__TextView) findViewById(R.id.text)).textFont(text_font);
                ((__TextView) findViewById(R.id.text)).textLine(Boolean.parseBoolean(text_line));
                ((__TextView) findViewById(R.id.text)).textSize(_ENUM.text_size.valueOf(text_size));
                ((__TextView) findViewById(R.id.text)).textVAlign(_ENUM.text_valign.valueOf(text_valign));
                //start
                ((__TextView) findViewById(R.id.text)).setVisibility(View.VISIBLE);
                ((__TextView) findViewById(R.id.text)).play(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DON'T CALL DIRECT CALL [[USE]] {@link #start()}
     */
    private void _play() {
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
                if (CContent.this.index < (contents != null ? contents.length() : 0)) {
                    index = r.nextInt(max - min) + min;
                    CContent.this.index = index;
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
    public boolean paused() {
        return false;
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
        //Log.wtf(__CLASSNAME__, getMethodName() + "\n[contents]\n" + toString(contents, 2));
        this.contents = contents;
        index = 0;
        start();
        //rand(); //test
    }
}
