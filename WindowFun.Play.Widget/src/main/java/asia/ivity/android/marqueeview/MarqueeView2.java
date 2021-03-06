package asia.ivity.android.marqueeview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by isyuun on 8/1/2017.
 */
@Deprecated
final public class MarqueeView2 extends MarqueeView {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public MarqueeView2(Context context) {
        super(context);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p>
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #MarqueeView2(Context, AttributeSet, int)
     */
    public MarqueeView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute. This constructor of MarqueeView2 allows subclasses to use their
     * own base style when they are inflating. For example, a Button class's
     * constructor would call this version of the super class constructor and
     * supply <code>R.attr.buttonStyle</code> for <var>defStyleAttr</var>; this
     * allows the theme's button style to modify all of the base view attributes
     * (in particular its background) as well as the Button class's attributes.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @see #MarqueeView2(Context, AttributeSet)
     */
    public MarqueeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute or style resource. This constructor of MarqueeView2 allows
     * subclasses to use their own base style when they are inflating.
     * <p>
     * When determining the final value of a particular attribute, there are
     * four inputs that come into play:
     * <ol>
     * <li>Any attribute values in the given AttributeSet.
     * <li>The style resource specified in the AttributeSet (named "style").
     * <li>The default style specified by <var>defStyleAttr</var>.
     * <li>The default style specified by <var>defStyleRes</var>.
     * <li>The base values in this theme.
     * </ol>
     * <p>
     * Each of these inputs is considered in-order, with the first listed taking
     * precedence over the following ones. In other words, if in the
     * AttributeSet you have supplied <code>&lt;Button * textColor="#ff000000"&gt;</code>
     * , then the button's text will <em>always</em> be black, regardless of
     * what is specified in any of the styles.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @param defStyleRes  A resource identifier of a style resource that
     *                     supplies default values for the view, used only if
     *                     defStyleAttr is 0 or can not be found in the theme. Can be 0
     *                     to not look for defaults.
     * @see #MarqueeView2(Context, AttributeSet, int)
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarqueeView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr/*, defStyleRes*/);
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    protected String getMethodName() {
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]][ST]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String reg = "[^A-Za-z0-9.]";
        int i;
        StackTraceElement item = null;
        for (i = 0; i < ste.length; i++) {
            item = ste[i];
            if (item.getMethodName().equalsIgnoreCase("getMethodName")) continue;
            if (item.getMethodName().contains("access$")) continue;
            //Log.v(_CLASSNAME_.replaceAll(reg, ""), item.getClassName().replaceAll(reg, "") + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
            if (item.getClassName().replaceAll(reg, "").contains(_CLASSNAME_.replaceAll(reg, ""))) {
                //Log.i(_CLASSNAME_, item.getClassName() + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
                break;
            }
        }
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]][ED]");
        return "" + item;
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
