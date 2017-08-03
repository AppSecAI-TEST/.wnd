package kr.co.windowfun.widget;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hanks.htextview.base.HTextView;

import java.util.ArrayList;
import java.util.Random;

import kr.co.windowfun._DEF;
import kr.co.windowfun._ENUM;

/**
 * Created by isyoon on 2017-07-13.
 */

class TextView2 extends TextView implements _Content, _DEF,_ENUM {
    public TextView2(Context context) {
        super(context);
    }

    public TextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        //this.path = (ArrayList<String>) Arrays.asList(sentences); //test
        return this;
    }

    effect_text type = effect_text.rainbow;

    public void open(Uri uri, String type) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + effect_text.valueOf(type) + ":" + textView + ":" + uri);
        this.type = effect_text.valueOf(type);
        open(uri);
    }

    String text;

    @Override
    public void open(Uri uri) {
        this.text = uri.toString();
        //scroll don't touch
        try {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
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
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + this.type + ":" + textView + ":" + uri);
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
        textView.setShadowLayer(1.5f, -1, 1, Color.LTGRAY);
        //gravity
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //add
        addView(textView);
        //blank
        String blank = getResources().getString(R.string.text_default_text);
        //for (int i = 0; i < text.length(); i++) blank += "\t ";
        setText(blank);
    }

    @Override
    protected void setText(String text) {
        super.setText(text);
        try {
            if (textView == null) return;
            //param(width)
            textView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int w = textView.getMeasuredWidth();
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + textView + ":" + params + ":" + "w:" + params.width + ", h:" + params.height + "->" + "w:" + w + ", h:" + h);
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
            if (TextView2.this.index < TextView2.this.path.size()) {
                Uri uri = Uri.parse(path.get(index));
                Log.i(__CLASSNAME__, getMethodName() + ":" + index + "->" + uri);
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
            mHandler.removeCallbacks(complete);
            if (length > 0) {
                mHandler.postDelayed(complete, length);
            }
            animate();
            marquee();
        }
    };

    private Runnable complete = new Runnable() {
        @Override
        public void run() {
            if (mCListener != null) mCListener.onCompletion(TextView2.this);
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
            int index = -1;
            try {
                if (TextView2.this.index < TextView2.this.path.size()) {
                    index = r.nextInt(max - min) + min;
                    TextView2.this.index = index;
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

}
