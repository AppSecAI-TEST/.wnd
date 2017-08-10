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
    int TIMER_MSEC_1H = 100;
    int TIMER_MSEC_5H = 500;
    int TIMER_MSEC_1T = 1000;
    int TIMER_MSEC_3T = 3000;
    int TIMER_MSEC_5T = 5000;
    int TIMER_MSEC_10T = 10000;
    int TIMER_MSEC_30T = 30000;
}
