package kr.co.windowfun.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by isyuun on 2017-08-04.
 */

class CContent2 extends CContent implements _CContentListener {
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

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //CListener
        ((__VideoView) findViewById(R.id.video)).setConListener(this);
        ((__ImageView) findViewById(R.id.image)).setConListener(this);
        ((__TextView) findViewById(R.id.text)).setConListener(this);
        ((__HtmlView) findViewById(R.id.html)).setConListener(this);
    }

    _CContentListener mCOnListener;

    public void setCListener(_CContentListener l) {
        this.mCOnListener = l;
    }

    @Override
    public void onPrepared(__CContent c, View v) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + v);
        if (mCOnListener != null) mCOnListener.onPrepared(c, v);
    }

    @Override
    public void onError(__CContent c, View v) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + v);
        if (mCOnListener != null) mCOnListener.onError(c, v);
    }

    @Override
    public void onCompletion(__CContent c, View v) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + v);
        if (mCOnListener != null) mCOnListener.onCompletion(c, v);
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) v;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }
}
