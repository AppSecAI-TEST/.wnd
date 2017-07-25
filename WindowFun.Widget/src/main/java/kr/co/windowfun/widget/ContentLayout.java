package kr.co.windowfun.widget;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Random;

/**
 * Created by isyoon on 2017-07-19.
 */

public class ContentLayout extends RelativeLayout implements _Content, _TAG, ContentListener, View.OnTouchListener {
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

    public ContentLayout(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public ContentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
            if (ContentLayout.this.index < ContentLayout.this.contents.length()) {
                String division = ((JSONObject) contents.get(index)).getString(result_c.division);
                String title = ((JSONObject) contents.get(index)).getString(result_c.title);
                String filename = ((JSONObject) contents.get(index)).getString(result_c.filename);
                String contents_order = ((JSONObject) contents.get(index)).getString(result_c.contents_order);
                Uri uri = Uri.parse(!TextUtils.isEmpty(filename) ? filename : title);
                //Log.w(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri + "<-" + division + ":" + title + ":" + filename + ":" + contents_order);
                open(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void open(Uri uri) {
        String url = uri.toString();
        ((android.widget.TextView)findViewById(R.id.label)).setText(url);
        try {
            //Log.wtf(__CLASSNAME__,getMethodName() + "[BF]" + url);
            url = _TextUtil.getFileUrl(url);
            //Log.wtf(__CLASSNAME__, getMethodName() + "[AF]" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = _TextUtil.getFilePath(url);
        if (new File(path) == null) {
            path = url;
        }

        stop();

        try {
            String division = ((JSONObject) contents.get(index)).getString(result_c.division);
            //Log.i(__CLASSNAME__,getMethodName() + ":" + division + ":" + url);
            switch (division) {
                case "T":
                    showText();
                    ((TextView2) findViewById(R.id.text)).open(uri);
                    break;
                case "I":
                    showImage();
                    ((android.widget.TextView)findViewById(R.id.label)).setText(Uri.decode(path));
                    ((ImageView2) findViewById(R.id.image)).open(Uri.parse(path));
                    break;
                case "M":
                    showVideo();
                    ((android.widget.TextView)findViewById(R.id.label)).setText(Uri.decode(path));
                    ((VideoView2) findViewById(R.id.video)).open(Uri.parse(path));
                    break;
                case "H":
                    showHtml();
                    ((HtmlView2) findViewById(R.id.html)).open(Uri.parse(url));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable play = new Runnable() {
        @Override
        public void run() {
            try {
                String division = ((JSONObject) contents.get(index)).getString(result_c.division);
                String contents_order = ((JSONObject) contents.get(index)).getString(result_c.contents_order);
                int length = Integer.parseInt(contents_order) * 1000;
                //length /= 10; //test
                //Log.w(__CLASSNAME__,getMethodName() + ":" + division);
                switch (division) {
                    case "T":
                        showText();
                        ((TextView2) findViewById(R.id.text)).play(length);
                        break;
                    case "I":
                        showImage();
                        ((ImageView2) findViewById(R.id.image)).play(length);
                        break;
                    case "M":
                        showVideo();
                        ((VideoView2) findViewById(R.id.video)).play();
                        break;
                    case "H":
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
        Log.w(__CLASSNAME__,getMethodName());
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
                if (ContentLayout.this.index < ContentLayout.this.contents.length()) {
                    index = r.nextInt(max - min) + min;
                    ContentLayout.this.index = index;
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
        //ContentListener
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
    public void onPrepared() {
        Log.w(__CLASSNAME__, getMethodName());
    }

    @Override
    public void onError() {
        Log.w(__CLASSNAME__, getMethodName());
        next();
        //rand(); //test
    }

    @Override
    public void onCompletion() {
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
