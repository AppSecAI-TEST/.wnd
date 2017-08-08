package asia.ivity.android.marqueeview;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by isyuun on 8/1/2017.
 */
@Deprecated
final public class MarqueeView2 extends MarqueeView {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

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

    public MarqueeView2(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public MarqueeView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public MarqueeView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    //@Override
    //public void addView(View child) {
    //    //Log.wtf(__CLASSNAME__, getMethodName() + child);
    //    addView(child, 0);
    //    setTextView();
    //}
    //
    //private void setTextView() {
    //    try {
    //        reset();
    //
    //        mScrollView.removeAllViews();
    //        mScrollView.removeView(mTextField);
    //
    //        mTextField = (TextView) getChildAt(0);
    //        removeView(mTextField);
    //
    //        mScrollView.addView(mTextField, new ScrollView.LayoutParams(TEXT_VIRTUAL_WIDTH, LayoutParams.WRAP_CONTENT));
    //
    //        mTextField.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    //
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable editable) {
    //                final boolean continueAnimation = mStarted;
    //
    //                reset();
    //                prepareAnimation();
    //
    //                cutTextView();
    //
    //                post(new Runnable() {
    //                    @Override
    //                    public void run() {
    //                        if (continueAnimation) {
    //                            startMarquee();
    //                        }
    //                    }
    //                });
    //            }
    //        });
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}
    //
    //@Override
    //protected void onLayout(boolean changed, int l, int t, int r, int b) {
    //    //if (changed) Log.wtf(__CLASSNAME__, getMethodName() + mTextField + ":" + mScrollView + ":" + changed);
    //    try {
    //        super.onLayout(changed, l, t, r, b);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}
    //
    //@Override
    //public void startMarquee() {
    //    //Log.wtf(__CLASSNAME__, getMethodName());
    //    super.startMarquee();
    //}
    //
    //public void stopMarquee() {
    //    //Log.wtf(__CLASSNAME__, getMethodName());
    //}
}
