package kr.co.windowfun;

import android.view.View;

import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-07-12.
 */

public class demo extends __demo {
    @Override
    protected void init() {
        videos.add(((__VideoView) findViewById(R.id.c1).findViewById(R.id.video))/*.path(mp4).mute(false)*/);
        videos.add(((__VideoView) findViewById(R.id.c3).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView) findViewById(R.id.c4).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView) findViewById(R.id.c5).findViewById(R.id.video))/*.path(mp4)*/);
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).path(mp4);
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        findViewById(R.id.cs).setVisibility(View.GONE);
        super.init();
    }
}
