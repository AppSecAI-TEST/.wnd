package kr.co.windowfun.widget;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hanks.htextview.base.HTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kr.co.windowfun._DEF;
import kr.co.windowfun._ENUM;

/**
 * Created by isyuun on 2017-07-13.
 */

class TextView2 extends TextView implements _CContent, _DEF, _ENUM {
    Context context;

    public TextView2(Context context) {
        super(context);
        this.context = context;
        _init();
    }

    public TextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        _init();
    }

    public TextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        _init();
    }

    private void _init() {
        Log.wtf(__CLASSNAME__, getMethodName());
        text(context.getString(R.string.text_default_text), type.toString());
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

    private int index;

    private ArrayList<String> path = new ArrayList<>();

    public TextView2 path(ArrayList<String> path) {
        this.path = path;
        this.index = 0;
        return this;
    }

    @Override
    public void path(String path) {
        this.path = new ArrayList<>(Arrays.asList(new String[]{path}));
        open();
    }

    @Override
    public Uri uri() {
        return Uri.parse(this._text);
    }

    @Deprecated
    @Override
    final public void open(final Uri uri) {/*안써*/}

    String _text = getResources().getString(R.string.text_default_text);
    text_effect type = text_effect.rainbow;

    private void params() {
        try {
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;
            textView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void text(String text, String type) {
        stopMarquee();
        //if (this.type != text_effect.valueOf(type))
        {
            removeAllViews();
            textView = CText.valueOf(type).with(getContext());
            //textView = CText.valueOf(this.type.toString()).with(getContext(), null, R.style.text_view);
            addView(textView);
            this._text = getResources().getString(R.string.text_default_text);
            _text(this._text);
        }
        this._text = text;
        this.path = new ArrayList<>(Arrays.asList(new String[]{text}));
        this.type = text_effect.valueOf(type);
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + getResources().getResourceEntryName(this.getId()) + ":" + this.type + ":" + textView + ":" + uri);
        //params
        params();
        //style
        if (!isInEditMode()) {
            if (Build.VERSION.SDK_INT < 23) {
                textView.setTextAppearance(getContext(), R.style.text_view);
            } else {
                textView.setTextAppearance(R.style.text_view);
            }
        }
        //shadow
        if (!isInEditMode()) textView.setShadowLayer(1.5f, -1, 1, Color.LTGRAY);
        //gravity
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //text
        text();
    }

    private void _text(String text) {
        try {
            if (textView == null) return;
            //param(width)
            textView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int w = textView.getMeasuredWidth();
            int h = textView.getMeasuredHeight();
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            Log.i(__CLASSNAME__, getMethodName() + ":" + getResources().getResourceEntryName(this.getId()) + ":" + this.type + ":" + textView + ":" + text + ":" + "w:" + params.width + ", h:" + params.height + "->" + "w:" + w + ", h:" + h);
            params.width = w;
            textView.setLayoutParams(params);
            if (textView instanceof HTextView) {
                ((HTextView) textView).animateText(text);
            }
            textView.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable text = new Runnable() {
        @Override
        public void run() {
            _text(_text);
        }
    };

    private void text() {
        if (isInEditMode()) return;
        mHandler.removeCallbacks(text);
        mHandler.postDelayed(text, TIMER_MSEC_1H);
    }

    /**
     * <a href="http://www.androidzeitgeist.com/2012/06/creating-marqueelayout-with-android.html">Creating a MarqueeLayout with the Android Animation System</a>
     */
    private Runnable marquee = new Runnable() {
        @Override
        public void run() {
            try {
                //layout marquee
                setDuration(TIMER_MSEC_10T);
                startMarquee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void marquee() {
        mHandler.removeCallbacks(marquee);
        mHandler.postDelayed(marquee, TIMER_MSEC_1T);
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

    private Runnable complete = new Runnable() {
        @Override
        public void run() {
            if (mCOnListener != null)
                mCOnListener.onCompletion((__CContent) getParent(), TextView2.this);
        }
    };

    Runnable animate = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(animate);
            mHandler.postDelayed(animate, TIMER_MSEC_5H);
        }
    };

    public ViewPropertyAnimator animate() {
        super.animate();
        mHandler.removeCallbacks(animate);
        mHandler.postDelayed(animate, 0);
        return null;
    }

    int length = -1;

    private Runnable play = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(complete);
            if (length > 0) {
                mHandler.postDelayed(complete, length);
            }
            animate();
            marquee();
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

        mHandler.removeCallbacks(animate);
        mHandler.removeCallbacks(marquee);
        mHandler.removeCallbacks(text);

        stopMarquee();
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

    @Override
    public void prev() {
        mHandler.removeCallbacks(prev);
        mHandler.postDelayed(prev, TIMER_MSEC_1H);
    }

    @Override
    public boolean paused() {
        return false;
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
        mHandler.postDelayed(next, TIMER_MSEC_1H);
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
