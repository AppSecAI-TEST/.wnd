package kr.co.windowfun;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.ContentLayout;

/**
 * Created by isyoon on 2017-07-12.
 */

public class main4 extends _main {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.e(__CLASSNAME__, getMethodName());
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        //Log.e(__CLASSNAME__, getMethodName());
        super.init();
        ((ContentLayout)findViewById(R.id.c1)).setContents(getApp().result_c1);
        ((ContentLayout)findViewById(R.id.c2)).setContents(getApp().result_c2);
        ((ContentLayout)findViewById(R.id.c3)).setContents(getApp().result_c3);
        ((ContentLayout)findViewById(R.id.c4)).setContents(getApp().result_c4);
    }
}
