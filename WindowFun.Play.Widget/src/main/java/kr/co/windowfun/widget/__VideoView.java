package kr.co.windowfun.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * 수퍼마켓클래스
 * Created by isyuun on 8/2/2017.
 */

public class __VideoView extends VideoView2 {
    public __VideoView(Context context) {
        super(context);
    }

    public __VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public __VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public __VideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
