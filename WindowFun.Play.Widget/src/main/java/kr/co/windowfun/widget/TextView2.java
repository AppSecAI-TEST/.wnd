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
 * Created by isyoon on 2017-07-13.
 */

class TextView2 extends TextView implements _CContent, _DEF, _ENUM {
    Context context;

    public TextView2(Context context) {
        super(context);
        this.context = context;
        //_init();
    }

    public TextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //_init();
    }

    public TextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //_init();
    }

    private void _init() {
        Log.wtf(__CLASSNAME__, getMethodName());
        text(context.getString(R.string.text_default_text), type.toString());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) _init();
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
        return Uri.parse(this.text);
    }

    @Deprecated
    @Override
    final public void open(final Uri uri) {/*안써*/}

    String text;
    text_effect type = text_effect.rainbow;

    public void text(String text, String type) {
        this.text = text;
        this.type = text_effect.valueOf(type);
        this.path = new ArrayList<>(Arrays.asList(new String[]{text}));
        try {
            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
            params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //remove
        stopMarquee();
        removeAllViews();
        //if (textView != null)  removeView(textView);
        //make
        textView = CText.valueOf(this.type.toString()).with(getContext());
        //textView = CText.valueOf(this.type.toString()).with(getContext(), null, R.style.text_view);
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + getResources().getResourceEntryName(this.getId()) + ":" + this.type + ":" + textView + ":" + uri);
        //param(width)
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(params);
        //style
        if (Build.VERSION.SDK_INT < 23) {
            textView.setTextAppearance(getContext(), R.style.text_view);
        } else {
            textView.setTextAppearance(R.style.text_view);
        }
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ContextCompat.getDimensionPixelSize(R.dimen.text_size_tiny));
        //shadow
        if (!isInEditMode()) textView.setShadowLayer(1.5f, -1, 1, Color.LTGRAY);
        //gravity
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //add
        addView(textView);
        ////blank
        //String blank = getResources().getString(R.string.text_default_text);
        ////for (int i = 0; i < _text.length(); i++) blank += "\t ";
        //setText(blank);
    }

    @Override
    protected void setText(String text) {
        super.setText(text);
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

    /**
     * <a href="http://www.androidzeitgeist.com/2012/06/creating-marqueelayout-with-android.html">Creating a MarqueeLayout with the Android Animation System</a>
     */
    private Runnable marquee = new Runnable() {
        @Override
        public void run() {
            try {
                //layout marquee
                setDuration(TIMER_ANI_NORMAL);
                startMarquee();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void marquee() {
        mHandler.removeCallbacks(marquee);
        mHandler.postDelayed(marquee, TIMER_OPEN_LONG);
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
            setText(text);
            mHandler.removeCallbacks(animate);
            mHandler.postDelayed(animate, TIMER_ANI_NORMAL);
        }
    };

    @Override
    public ViewPropertyAnimator animate() {
        mHandler.removeCallbacks(animate);
        mHandler.postDelayed(animate, 0);
        return super.animate();
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
        length = -1;
        mHandler.removeCallbacks(complete);
        mHandler.removeCallbacks(play);
        mHandler.removeCallbacks(prev);
        mHandler.removeCallbacks(next);
        mHandler.removeCallbacks(rand);

        mHandler.removeCallbacks(animate);
        mHandler.removeCallbacks(marquee);

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
        mHandler.postDelayed(next, TIMER_OPEN_SHORT);
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
