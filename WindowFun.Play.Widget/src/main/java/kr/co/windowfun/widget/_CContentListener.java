package kr.co.windowfun.widget;

import android.view.View;

/**
 * 컨텐츠리스너<br>
 * Created by isyoon on 2017-07-24.
 */

public interface _CContentListener {
    void onPrepared(__CContent c, View v);
    void onError(__CContent c, View v);
    void onCompletion(__CContent c, View v);
}
