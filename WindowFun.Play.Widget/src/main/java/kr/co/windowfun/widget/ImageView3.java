package kr.co.windowfun.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by isyuun on 2017-08-04.
 */

class ImageView3 extends ImageView2 implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {
    public ImageView3(Context context) {
        super(context);
    }

    public ImageView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        super.setOnTouchListener(this);
        super.setOnClickListener(this);
        super.setOnLongClickListener(this);
    }

    OnTouchListener mOnTouchListener;
    MotionEvent event;

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(this);
        mOnTouchListener = l;
    }

    OnClickListener mOnClickListener;

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(this);
        this.mOnClickListener = l;
    }

    OnLongClickListener mOnLongClickListener;

    @Override
    public void setOnLongClickListener(@Nullable OnLongClickListener l) {
        super.setOnLongClickListener(this);
        this.mOnLongClickListener = l;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.event = event;
        float w = v.getWidth();
        //float h = v.getHeight();
        float x = event.getX();
        //float y = event.getY();
        //Log.wtf(__CLASSNAME__, getMethodName()/* + ":" + v + "\t"*/ + ":w:" + v.getWidth() + "\t" + "x:" + (int) x + "\t" + "[" + (int) (w * 1.0f / 3.0f) + " - " + (int) (w * 2.0f / 3.0f) + "]" + "\t" + event);
        if (x < (w * 1.0f / 3.0f)) {
            prev();
        } else if (x > (w * 2.0f / 3.0f)) {
            next();
        } else {
            if (paused()) {
                pause();
            } else {
                resume();
            }
        }
        if (mOnTouchListener != null) {
            mOnTouchListener.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        float w = v.getWidth();
        //float h = v.getHeight();
        float x = event != null ? event.getX() : w / 2.0f;
        //float y = event.getY();
        //Log.wtf(__CLASSNAME__, getMethodName()/* + ":" + v + "\t"*/ + ":w:" + v.getWidth() + "\t" + "x:" + (int) x + "\t" + "[" + (int) (w * 1.0f / 3.0f) + " - " + (int) (w * 2.0f / 3.0f) + "]" + "\t" + event);
        if (x < (w * 1.0f / 3.0f)) {
            prev();
        } else if (x > (w * 2.0f / 3.0f)) {
            next();
        }
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        float w = v.getWidth();
        //float h = v.getHeight();
        float x = event != null ? event.getX() : w / 2.0f;
        //float y = event.getY();
        //Log.wtf(__CLASSNAME__, getMethodName()/* + ":" + v + "\t"*/ + ":w:" + v.getWidth() + "\t" + "x:" + (int) x + "\t" + "[" + (int) (w * 1.0f / 3.0f) + " - " + (int) (w * 2.0f / 3.0f) + "]" + "\t" + event);
        if (mOnLongClickListener != null) {
            mOnLongClickListener.onLongClick(v);
        }
        return false;
    }
}
