package kr.co.windowfun.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by isyuun on 2017-08-04.
 */

class CContent2 extends CContent implements CListener {
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
        ((__VideoView) findViewById(R.id.video)).setCListener(this);
        ((__ImageView) findViewById(R.id.image)).setCListener(this);
        ((__TextView) findViewById(R.id.text)).setCListener(this);
        ((__HtmlView) findViewById(R.id.html)).setCListener(this);
    }

    @Override
    public void onPrepared(View v) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v);
        //
    }

    @Override
    public void onError(View v) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v);
        next();
        //rand(); //test
    }

    @Override
    public void onCompletion(View v) {
        Log.wtf(__CLASSNAME__, getMethodName() + ":" + v);
        next();
        //rand(); //test
    }
}
