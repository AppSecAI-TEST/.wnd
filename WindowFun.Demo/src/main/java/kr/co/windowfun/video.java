package kr.co.windowfun;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-07-10.
 */

public class video extends _Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(kr.co.windowfun.R.layout.video);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final __VideoView video = (__VideoView) findViewById(kr.co.windowfun.R.id.video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);

        video.setMediaController(mediaController);
        video.requestFocus();
        video.setMediaController(null);

        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            String ext = url.substring(url.lastIndexOf("."));
            Log.w(__CLASSNAME__, getMethodName() + ":" + ext + ":" + url);
            video.open(Uri.parse(url));
            video.play();
        }

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                video.this.finish();
            }
        });

        FloatingActionButton close = (FloatingActionButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.this.finish();
            }
        });
    }
}
