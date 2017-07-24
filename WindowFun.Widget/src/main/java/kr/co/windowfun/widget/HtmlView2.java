package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by isyoon on 2017-07-19.
 */

public class HtmlView2 extends HtmlView implements _Content, _TAG {
    public HtmlView2(Context context) {
        super(context);
    }

    public HtmlView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HtmlView2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public void open(Uri uri) {
        loadUrl(uri.toString());
    }

    int length = -1;
    private Runnable play = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(call);
            //int r = ImageView2.this.r.nextInt(TIMER_JPG_LONG - TIMER_JPG_SHORT + 1) + TIMER_JPG_SHORT;
            //if (ImageView2.this.index < ImageView2.this.path.size()) {
            //    Uri uri = Uri.parse(path.get(index));
            //    if (uri.toString().contains((".gif"))) {
            //        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + ":" + uri);
            //        r = TIMER_GIF_SHORT;
            //        r = TIMER_GIF_LONG;
            //    }
            //}
            mHandler.postDelayed(call, length);
        }
    };


    private Runnable call = new Runnable() {
        @Override
        public void run() {
            if (mContentListener != null) mContentListener.onCompletion();
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
