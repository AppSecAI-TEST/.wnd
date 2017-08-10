package kr.co.windowfun.widget;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kr.co.windowfun._DEF;
import kr.co.windowfun._ENUM;
import kr.co.windowfun._JSON;
import kr.co.windowfun.api.JSONObject2;
import kr.co.windowfun.util.TextUtil;

/**
 * 컨텐츠레이아웃<br>
 * Created by isyoon on 2017-07-19.
 */

class CContent2 extends CContent implements _CContent, _DEF, _ENUM, _JSON {
    public CContent2(Context context) {
        super(context);
    }

    public CContent2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CContent2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CContent2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void showVideo() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).show();
        ((__ImageView) findViewById(R.id.image)).hide();
        ((__TextView) findViewById(R.id.text)).hide();
        ((__HtmlView) findViewById(R.id.html)).hide();
    }

    private void showImage() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).hide();
        ((__ImageView) findViewById(R.id.image)).show();
        ((__TextView) findViewById(R.id.text)).hide();
        ((__HtmlView) findViewById(R.id.html)).hide();
    }

    private void showText() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).hide();
        ((__ImageView) findViewById(R.id.image)).hide();
        ((__TextView) findViewById(R.id.text)).show();
        ((__HtmlView) findViewById(R.id.html)).hide();
    }

    private void showHtml() {
        //Log.w(__CLASSNAME__,getMethodName());
        ((__VideoView) findViewById(R.id.video)).hide();
        ((__ImageView) findViewById(R.id.image)).hide();
        ((__TextView) findViewById(R.id.text)).hide();
        ((__HtmlView) findViewById(R.id.html)).show();
    }

    private JSONArray contents;

    public void setContents(JSONArray contents) {
        //Log.wtf(__CLASSNAME__, getMethodName() + "\n[contents]\n" + toString(contents, 2));
        this.contents = contents;
        index = 0;
        //start();
        //rand(); //test
    }

    private int index;

    private ArrayList<String> path = new ArrayList<>();

    public CContent2 path(ArrayList<String> path) {
        this.path = path;
        this.index = 0;
        return this;
    }

    @Override
    public void path(String path) {
        this.path = new ArrayList<>(Arrays.asList(new String[]{path}));
    }

    Uri uri;

    @Override
    public Uri uri() {
        return this.uri;
    }

    @Deprecated
    @Override
    final public void open(final Uri uri) {
    }

    @Deprecated
    @Override
    final public void play() {
    }

    @Deprecated
    @Override
    final public void play(int length) {
    }

    private void showTexting() {
        mHandler.removeCallbacks(showTexting);
        mHandler.postDelayed(showTexting, TIMER_OPEN_NORMAL);
    }

    private Runnable showTexting = new Runnable() {
        @Override
        public void run() {
            _showTexting();
        }
    };

    JSONObject2 item = null;
    String _type = null;
    String _text = null;
    //text...
    /**
     * plane|line|fade|typer|rainbow|scale|evaporate|fall
     */
    String _text_effect = text_effect.rainbow.toString(); //default
    String _text_font = "font1"; //default
    String _text_line = "false"; //default
    /**
     * xxxlarge|xxlarge|xlarge|large|normal|small|xsmall|xxsmall|xxxsmall
     */
    String _text_size = text_size.xxlarge.toString(); //default
    /**
     * top|center|bottom
     */
    String _text_valign = text_valign.center.toString(); //default
    //String _text_color = "#ffffffff"; //default
    //String _text_backcolor = "#55ff00ff"; //default
    int _text_color = Color.parseColor("#ffffffff");  //default:R.color.text_default_color;
    int _text_backcolor = Color.parseColor("#55ff00ff");  //default:R.color.test_default_backcolor);
    //play...
    String _play_effect = Techniques.FlipInX.toString();
    String _play_length = null;
    //file...
    String file_name = "";

    /**
     * DON'T CALL DIRECT CALL [[USE]] {@link #showTexting()}
     */
    protected void _showTexting() {
        if (result_c_type.text == result_c_type.valueOf(_type)) return;
        try {
            if (!TextUtils.isEmpty(_text)) {
                //Log.wtf(__CLASSNAME__, getMethodName() + ":" + _type + ":" + _text + ":" + _text_effect + ":" + _play_effect);
                text();
                //start
                ((__TextView) findViewById(R.id.text)).show();
                ((__TextView) findViewById(R.id.text)).play(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DON'T CALL DIRECT CALL [[USE]] {@link #start()}
     */
    private void _open() {
        try {
            Log.i(__CLASSNAME__, getMethodName() + ":" + CContent2.this.index + "\n" + ((JSONObject) CContent2.this.contents.get(index)).toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!(index < (contents != null ? contents.length() : 0))) return;

        //reset
        item = null;
        _type = null;
        _text = null;

        try {
            item = new JSONObject2(contents.getJSONObject(index).toString());
            _type = !item.isNullString(result_c.type) ? item.getString(result_c.type) : _type;
            _text = !item.isNullString(result_c.text) ? item.getString(result_c.text) : _text;
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        try {
            //text_...default
            _text_effect = text_effect.rainbow.toString(); //default
            _text_font = "font1"; //default
            _text_line = "true"; //default
            _text_size = text_size.xxlarge.toString(); //default
            _text_valign = text_valign.bottom.toString(); //default
            _text_color = Color.parseColor("#ffffffff");  //default:R.color.text_default_color;
            _text_backcolor = Color.parseColor("#55ff00ff");  //default:R.color.test_default_backcolor);
            String __text_color = String.format("#%08x", /*0xFFFFFFFF & */_text_color);
            String __text_backcolor = String.format("#%08x", /*0xFFFFFFFF & */_text_backcolor);
            //text_...value
            _text_effect = !item.isNullString(result_c.text_effect) ? item.getString(result_c.text_effect) : _text_effect;
            _text_font = !item.isNullString(result_c.text_font) ? item.getString(result_c.text_font) : _text_font;
            _text_line = !item.isNullString(result_c.text_line) ? item.getString(result_c.text_line) : _text_line;
            _text_size = !item.isNullString(result_c.text_size) ? item.getString(result_c.text_size) : _text_size;
            _text_valign = !item.isNullString(result_c.text_valign) ? item.getString(result_c.text_valign) : _text_valign;
            _text_color = Color.parseColor(!item.isNullString(result_c.text_color) ? item.getString(result_c.text_color) : __text_color);
            _text_backcolor = Color.parseColor(!item.isNullString(result_c.text_backcolor) ? item.getString(result_c.text_backcolor) : __text_backcolor);
            if (!TextUtils.isEmpty(_text)) {
                String msg = "";
                msg += "\n[type]" + _type;
                msg += "\n[text]" + _text;
                msg += "\n[text_effect]" + _text_effect;
                msg += "\n[text_font]" + _text_font;
                msg += "\n[text_line]" + _text_line;
                msg += "\n[text_size]" + _text_size;
                msg += "\n[text_valign]" + _text_valign;
                msg += "\n[text_color]" + _text_color + ":" + __text_color;
                msg += "\n[text_backcolor]" + _text_backcolor + ":" + __text_backcolor;
                Log.w(__CLASSNAME__, getMethodName() + msg);
            }
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        try {
            //play_...
            _play_effect = !item.isNullString(result_c.play_effect) ? item.getString(result_c.play_effect) : _play_effect;
            _play_length = !item.isNullString(result_c.play_length) ? item.getString(result_c.play_length) : _play_length;
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        try {
            //file_name
            file_name = !item.isNullString(result_c.file_name) ? item.getString(result_c.file_name) : file_name;
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        //Log.w(__CLASSNAME__, getMethodName() + ":" + index + "->" + _type + ":" + _text + ":" + file_name + ":" + uri);
        _open(file_name);
    }

    private void _open(String uri) {
        try {
            this.uri = Uri.parse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String url = TextUtil.getFileUrl(uri) != null ? TextUtil.getFileUrl(uri) : uri;
        File file = new File(TextUtil.getFilePath(url));
        final String path = file.exists() && file.isFile() ? TextUtil.getFilePath(url) : url;

        stop();

        //label
        String label = _type + ":" + _text_size + ":" + _text + ":" + _text_effect + ":" + _play_effect;
        if (result_c_type.text == result_c_type.valueOf(_type)) {
            label += ":" + _text;
            this.uri = Uri.parse(this._text);
        } else {
            label += ":" + Uri.decode(path);
            this.uri = Uri.parse(path);
        }
        ((android.widget.TextView) findViewById(R.id.label)).setText(label); //test

        Log.wtf(__CLASSNAME__, getMethodName() + ":" + label); //test

        View v = this;
        switch (result_c_type.valueOf(_type)) {
            case text:
                showText();
                text();
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
        //texting
        //_text = getResources().getString(R.string.text_test_text); //test
        //_text_effect = text_effect.rainbow.toString(); //test
        //_text_font = "font1";  //test
        //_text_line = "false"; //test
        //_text_size = text_size.xxxlarge.toString(); //test
        //_text_valign = text_valign.top.toString(); //test
        if (result_c_type.text != result_c_type.valueOf(_type) && !TextUtils.isEmpty(_text)) showTexting();
        //_play_effect
        YoYo.with(Techniques.valueOf(_play_effect))
                .duration(1000)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        //Log.wtf(__CLASSNAME__, "YoYo.onEnd()" + ":" + _type + ":" + _text_effect + ":" + _play_effect + ":" + uri + ":" + path);
                    }
                })
                .playOn(v);
    }

    private void text() {
        try {
            ((__TextView) findViewById(R.id.text)).text(_text, _text_effect);
            ((__TextView) findViewById(R.id.text)).textFont(_text_font);
            ((__TextView) findViewById(R.id.text)).textLine(Boolean.parseBoolean(_text_line));
            ((__TextView) findViewById(R.id.text)).textSize(text_size.valueOf(_text_size));
            ((__TextView) findViewById(R.id.text)).textVAlign(text_valign.valueOf(_text_valign));
            ((__TextView) findViewById(R.id.text)).textColor(_text_color);
            ((__TextView) findViewById(R.id.text)).textBackColor(_text_backcolor);
        } catch (IllegalArgumentException e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
        }
    }

    /**
     * DON'T CALL DIRECT CALL [[USE]] {@link #start()}
     */
    private void _play() {
        try {
            int length = Integer.parseInt(_play_length) * 1000;
            //Log.w(__CLASSNAME__,getMethodName() + ":" + _type);
            switch (result_c_type.valueOf(_type)) {
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

    public void start() {
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
            int idx;
            try {
                idx = r.nextInt(max - min) + min;
                if (idx > -1 && idx < contents.length()) {
                    index = idx;
                    Log.wtf(__CLASSNAME__, getMethodName() + ":" + idx + ":" + contents.get(idx));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Log.w(__CLASSNAME__, getMethodName() + ":" + index);
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

    @Override
    public void show() {
        setVisibility(View.VISIBLE);
        ((__TextView) findViewById(R.id.text)).show();
        ((__ImageView) findViewById(R.id.image)).show();
        ((__VideoView) findViewById(R.id.video)).show();
        ((__HtmlView) findViewById(R.id.html)).show();
    }

    @Override
    public void hide() {
        setVisibility(View.INVISIBLE);
        ((__TextView) findViewById(R.id.text)).hide();
        ((__ImageView) findViewById(R.id.image)).hide();
        ((__VideoView) findViewById(R.id.video)).hide();
        ((__HtmlView) findViewById(R.id.html)).hide();
    }

    @Override
    public void gone() {
        setVisibility(View.GONE);
        ((__TextView) findViewById(R.id.text)).hide();
        ((__ImageView) findViewById(R.id.image)).hide();
        ((__VideoView) findViewById(R.id.video)).hide();
        ((__HtmlView) findViewById(R.id.html)).hide();
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

}
