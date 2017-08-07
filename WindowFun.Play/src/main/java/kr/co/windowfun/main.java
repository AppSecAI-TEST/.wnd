package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by isyoon on 2017-07-12.
 */

public class main extends __main2 {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.w(__CLASSNAME__, getMethodName());
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
