package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by isyoon on 2017-07-19.
 */

public class WebView2 extends WebView implements _Content {
    public WebView2(Context context) {
        super(context);
    }

    public WebView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WebView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
