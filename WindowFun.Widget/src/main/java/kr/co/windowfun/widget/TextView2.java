package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

/**
 * Created by isyoon on 2017-07-13.
 */

public class TextView2 extends TextView implements _Content, _TAG {

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

    @Override
    public void open(Uri uri) {

    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {
        //mHandler.removeCallbacks(play);
        //mHandler.removeCallbacks(prev);
        //mHandler.removeCallbacks(next);
        //mHandler.removeCallbacks(rand);
    }

    @Override
    public void rand() {

    }

    @Override
    public void next() {

    }

    @Override
    public void prev() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
