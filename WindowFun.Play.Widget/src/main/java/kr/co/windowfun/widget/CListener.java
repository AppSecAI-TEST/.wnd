package kr.co.windowfun.widget;

import android.view.View;

/**
 * 컨텐츠리스너<br>
 * Created by isyoon on 2017-07-24.
 */

public interface CListener {
    void onPrepared(View v);
    void onError(View v);
    void onCompletion(View v);
}
