package asia.ivity.android.marqueeview;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by isyuun on 8/1/2017.
 */

public class MarqueeView2 extends MarqueeView {
    public MarqueeView2(Context context) {
        super(context);
    }

    public MarqueeView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addView(View child) {
        //Log.wtf(__CLASSNAME__, getMethodName() + child);
        addView(child, 0);
        setTextView();
    }

    private void setTextView() {
        try {
            reset();

            mScrollView.removeAllViews();
            mScrollView.removeView(mTextField);

            mTextField = (TextView) getChildAt(0);
            removeView(mTextField);

            mScrollView.addView(mTextField, new ScrollView.LayoutParams(TEXTVIEW_VIRTUAL_WIDTH, LayoutParams.WRAP_CONTENT));

            mTextField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    final boolean continueAnimation = mStarted;

                    reset();
                    prepareAnimation();

                    cutTextView();

                    post(new Runnable() {
                        @Override
                        public void run() {
                            if (continueAnimation) {
                                startMarquee();
                            }
                        }
                    });
                }
            });

            mScrollView.setBackgroundColor(Color.CYAN); //test
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //if (changed) Log.wtf(__CLASSNAME__, getMethodName() + mTextField + ":" + mScrollView + ":" + changed);
        try {
            super.onLayout(changed, l, t, r, b);
        } catch (Exception e) {
            //e.printStackTrace();
            Log.wtf(__CLASSNAME__, getMethodName() + e.getStackTrace());
        }
    }

    @Override
    public void startMarquee() {
        //Log.wtf(__CLASSNAME__, getMethodName());
        super.startMarquee();
    }

    public void stopMarquee() {
        //Log.wtf(__CLASSNAME__, getMethodName());
    }

    @Override
    protected void startTextFieldAnimation() {
        super.startTextFieldAnimation();
    }

    @Override
    protected void prepareAnimation() {
        super.prepareAnimation();
    }
}
