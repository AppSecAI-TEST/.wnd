package kr.co.windowfun;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import kr.co.windowfun.widget._TAG;

/**
 * 수퍼액티비티
 * Created by isyoon on 2017-07-18.
 */

class _Activity extends Activity2 implements _TAG {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(__CLASSNAME__, getMethodName());
        super.onCreate(savedInstanceState);
        if (isACTIONMAIN()) {
            requestForPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e(__CLASSNAME__, getMethodName() + ":" + requestCode + ":" + permissions + grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

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
