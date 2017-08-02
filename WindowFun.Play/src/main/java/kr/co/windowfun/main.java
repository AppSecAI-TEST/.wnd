package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.__CLayout;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyoon on 2017-07-12.
 */

public class main extends __main {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.w(__CLASSNAME__, getMethodName());
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        //Log.w(__CLASSNAME__, getMethodName());
        ((__VideoView)findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        super.init();
        ((__CLayout)findViewById(R.id.c1)).setContents(getApp().result_c1);
        ((__CLayout)findViewById(R.id.c3)).setContents(getApp().result_c3);
        ((__CLayout)findViewById(R.id.c4)).setContents(getApp().result_c4);
        ((__CLayout)findViewById(R.id.c5)).setContents(getApp().result_c5);
    }
}
