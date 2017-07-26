package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.VideoView2;

/**
 * Created by isyoon on 2017-07-12.
 */

public class demo2 extends _main {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        videos.add(((VideoView2)findViewById(R.id.c1).findViewById(R.id.video)).path(mp4).mute(false));
        videos.add(((VideoView2)findViewById(R.id.c2).findViewById(R.id.video)).path(mp4));
        videos.add(((VideoView2)findViewById(R.id.c3).findViewById(R.id.video)).path(mp4));
        videos.add(((VideoView2)findViewById(R.id.c4).findViewById(R.id.video)).path(mp4));
        super.init();
        rand();
    }
}
