package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by isyuun on 2017-07-12.
 */

public class main extends __main5 {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.w(__CLASSNAME__, getMethodName());
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
