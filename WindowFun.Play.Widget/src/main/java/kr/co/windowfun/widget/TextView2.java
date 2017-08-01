package kr.co.windowfun.widget;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.RelativeLayout;

import com.hanks.htextview.base.HTextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by isyoon on 2017-07-13.
 */

public class TextView2 extends TextView implements _Content, _TAG {
    String[] sentences = {
            //"What is design?",
            //"Design is not just",
            //"what it looks like and feels like.",
            "Design is how it works. - Steve Jobs",
            //"Older people",
            //"sit down and ask,",
            //"'What is it?'",
            //"but the boy asks,",
            "'What can I do with it?'. - Steve Jobs",
            //"Swift",
            //"Objective-C",
            //"iPhone",
            //"iPad",
            //"Mac Mini",
            //"MacBook Pro",
            //"Mac Pro",
            //"爱老婆",
            //"老婆和女儿",
            "만세만세만세만세만세 윈도펀 만세만세만세만세만세",
            "세만세만세만세만세만 이종천 세만세만세만세만세만",
    };

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

    String text;

    @Override
    public void open(Uri uri) {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + uri);
        this.text = uri.toString();
        //remove
        if (textView != null) removeView(textView);
        removeAllViews();
        //make
        textView = CTextView.with(getContext(), this.type);
        //style
        if (Build.VERSION.SDK_INT < 23) {
            textView.setTextAppearance(getContext(), R.style.text_view);
        } else {
            textView.setTextAppearance(R.style.text_view);
        }
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_view_size_large));
        //textView.setShadowLayer(float radius, float dx, float dy, int shadowColor)
        textView.setShadowLayer(1.5f, -1, 1, Color.LTGRAY);
        //line
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        //param
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //blank
        String blank = "";
        for (int i = 0; i < text.length(); i++) blank += "\t ";
        setText(blank);
        addView(textView);
    }

    /**
     * <a href="http://www.androidzeitgeist.com/2012/06/creating-marqueelayout-with-android.html">Creating a MarqueeLayout with the Android Animation System</a>
     */
    private Runnable marquee = new Runnable() {
        @Override
        public void run() {
            //textview marquee
            //textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            //textView.setMarqueeRepeatLimit(-1);
            textView.setSelected(true);
            textView.setSingleLine(true);
            //layout marquee
            setDuration(TIMER_ANI_NORMAL);
            startAnimation();
        }
    };

    private void marquee() {
        mHandler.removeCallbacks(marquee);
        mHandler.postDelayed(marquee, TIMER_ANI_SHORT);
    }

    String type;

    public void type(String type) {
        this.type = type;
    }

    android.widget.TextView textView;

    private void setText(String text) {
        try {
            if (textView == null) return;
            if (textView instanceof HTextView) {
                ((HTextView) textView).animateText(text);
            }
            textView.setText(text);
            textView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int w = textView.getMeasuredWidth();
            int h = textView.getMeasuredHeight();
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            //Log.wtf(__CLASSNAME__, getMethodName() + "w:" + params.width + ", h:" + params.height + "->" + "w:" + w + ", h:" + h);
            params.width = w;
            params.height = h;
            textView.setLayoutParams(params);
            //setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected android.widget.TextView getTextView() {
        return textView;
    }

    private void open() {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + this.index + ":" + this.path);
        try {
            if (TextView2.this.index < TextView2.this.path.size()) {
                Uri uri = Uri.parse(path.get(index));
                Log.i(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
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
            mHandler.postDelayed(complete, length);
            animate();
            marquee();
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

    private Runnable complete = new Runnable() {
        @Override
        public void run() {
            if (mCListener != null) mCListener.onCompletion(TextView2.this);
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
        //stopAnimation();
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
