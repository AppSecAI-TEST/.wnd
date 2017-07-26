package kr.co.windowfun;

import android.util.Log;

import kr.co.windowfun.widget._TAG;

/**
 * 수퍼액티비티
 * Created by isyoon on 2017-07-18.
 */

class _Activity extends Activity2 implements _TAG {

    @Override
    public void onBackPressed() {
        Log.e(__CLASSNAME__, getMethodName());
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        Log.e(__CLASSNAME__, getMethodName());
        super.onDestroy();
    }
}
