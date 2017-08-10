package kr.co.windowfun.widget;

import android.net.Uri;

/**
 * Created by isyuun on 2017-07-19.
 */

interface _CContent {
    void path(String path);

    Uri uri();

    void open(Uri uri);

    void play();

    void play(int length);

    void stop();

    void rand();

    void next();

    void prev();

    boolean paused();

    void pause();

    void resume();

    void show();

    void hide();

    void gone();
}
