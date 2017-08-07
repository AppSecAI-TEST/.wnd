package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyoon on 2017-07-12.
 */

public class demo2 extends __demo {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        videos.add(((__VideoView)findViewById(R.id.c1).findViewById(R.id.video))/*.path(mp4).mute(false)*/);
        videos.add(((__VideoView)findViewById(R.id.c3).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView)findViewById(R.id.c4).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView)findViewById(R.id.c5).findViewById(R.id.video))/*.path(mp4)*/);
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).path(mp4);
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        ((__VideoView)findViewById(R.id.c3).findViewById(R.id.video)).path(mp4);
        ((__VideoView)findViewById(R.id.c4).findViewById(R.id.video)).path(mp4);
        ((__VideoView)findViewById(R.id.c5).findViewById(R.id.video)).path(mp4);
        super.init();
        rand(); //test
    }
}
