package kr.co.windowfun;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-07-12.
 */

public class demo extends _demo {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        videos.add(((__VideoView)findViewById(R.id.c1).findViewById(R.id.video))/*.path(mp4).mute(false)*/);
        videos.add(((__VideoView)findViewById(R.id.c2).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView)findViewById(R.id.c3).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView)findViewById(R.id.c4).findViewById(R.id.video))/*.path(mp4)*/);

        ((__VideoView)findViewById(R.id.c1).findViewById(R.id.video)).path(mp4);
        ((__VideoView)findViewById(R.id.c1).findViewById(R.id.video)).mute(false);

        findViewById(R.id.cs).setVisibility(View.GONE);
        super.init();
    }
}
