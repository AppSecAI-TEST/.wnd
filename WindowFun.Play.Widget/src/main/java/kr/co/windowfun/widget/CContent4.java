package kr.co.windowfun.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by isyuun on 2017-08-04.
 */

class CContent4 extends CContent3 implements View.OnTouchListener, View.OnClickListener, View.OnLongClickListener {
    public CContent4(Context context) {
        super(context);
    }

    public CContent4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CContent4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CContent4(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //OnTouchListener
        ((__VideoView) findViewById(R.id.video)).setOnTouchListener(this);
        ((__ImageView) findViewById(R.id.image)).setOnTouchListener(this);
        ((__HtmlView) findViewById(R.id.html)).setOnTouchListener(this);
        ((__View) findViewById(R.id.view)).setOnTouchListener(this);
        //OnClickListener
        ((__VideoView) findViewById(R.id.video)).setOnClickListener(this);
        ((__ImageView) findViewById(R.id.image)).setOnClickListener(this);
        ((__HtmlView) findViewById(R.id.html)).setOnClickListener(this);
        ((__View) findViewById(R.id.view)).setOnClickListener(this);
        //OnClickListener
        ((__VideoView) findViewById(R.id.video)).setOnLongClickListener(this);
        ((__ImageView) findViewById(R.id.image)).setOnLongClickListener(this);
        ((__HtmlView) findViewById(R.id.html)).setOnLongClickListener(this);
        ((__View) findViewById(R.id.view)).setOnLongClickListener(this);
    }

    @Override
    protected void _showTexting() {
        super._showTexting();
    }

    OnTouchListener mOnTouchListener;
    MotionEvent event;

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        //super.setOnTouchListener(l);
        mOnTouchListener = l;
    }

    OnClickListener mOnClickListener;

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        //super.setOnClickListener(l);
        this.mOnClickListener = l;
    }

    OnLongClickListener mOnLongClickListener;

    @Override
    public void setOnLongClickListener(@Nullable OnLongClickListener l) {
        //super.setOnLongClickListener(l);
        this.mOnLongClickListener = l;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.event = event;
        float w = v.getWidth();
        //float h = v.getHeight();
        float x = event.getX();
        //float y = event.getY();
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v + "\t" + ":w:" + v.getWidth() + "\t" + "x:" + (int) x + "\t" + "[" + (int) (w * 1.0f / 3.0f) + " - " + (int) (w * 2.0f / 3.0f) + "]" + "\t" + event);
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
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v + "\t" + ":w:" + v.getWidth() + "\t" + "x:" + (int) x + "\t" + "[" + (int) (w * 1.0f / 3.0f) + " - " + (int) (w * 2.0f / 3.0f) + "]" + "\t" + event);
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
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v + "," + event);
        if (mOnLongClickListener != null) {
            mOnLongClickListener.onLongClick(v);
        }
        return false;
    }
}
