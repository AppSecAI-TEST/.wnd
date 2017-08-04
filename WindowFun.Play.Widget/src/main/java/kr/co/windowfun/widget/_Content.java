package kr.co.windowfun.widget;

import android.net.Uri;

/**
 * Created by isyoon on 2017-07-19.
 */

interface _Content {
    void open(Uri uri);
    //void open();
    void play();
    void play(int length);
    void stop();
    void rand();
    void next();
    void prev();

    boolean paused();
    void pause();
    void resume();
}
