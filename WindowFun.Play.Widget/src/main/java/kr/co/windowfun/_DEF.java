package kr.co.windowfun;

import android.app.Activity;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import kr.co.windowfun.widget.BuildConfig;

/**
 * Created by isyuun on 8/2/2017.
 */

public interface _DEF {
    boolean DEBUG = BuildConfig.DEBUG;

    String TEXTVIEW_DEFAULT_TEXT = "window Fun Fun Fun Fun Fun Fun Fun";
    int TEXTVIEW_VIRTUAL_WIDTH = 10000;


    String root = Environment.getExternalStorageDirectory().getAbsolutePath();
    String root_mp4 = root + "/.mp4";

    Handler mHandler = new Handler(Looper.getMainLooper());

    //PROGRESS
    float PROGRESS_PER = 100f;
    float PROGRESS_MAX = 1000f;

    //RESULT
    int _RESULT_OK = Activity.RESULT_OK;
    int _RESULT_CANCELED = Activity.RESULT_CANCELED;
    int _RESULT_LOGIN = Activity.RESULT_FIRST_USER;

    //TIMER
    int TIMER_RESET_SHORT = 3000;
    int TIMER_RESET_LONG = 5000;
    int TIMER_OPEN_SHORT = 100;
    int TIMER_OPEN_NORMAL = 500;
    int TIMER_OPEN_LONG = 1000;
    int TIMER_IMG_SHORT = 3000;
    int TIMER_IMG_LONG = 5000;
    int TIMER_ANI_SHORT = 1000;
    int TIMER_ANI_NORMAL = 10000;
    int TIMER_ANI_LONG = 30000;
}
