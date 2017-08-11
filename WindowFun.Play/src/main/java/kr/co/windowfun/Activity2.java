package kr.co.windowfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 로그인처리
 * Created by isyuun on 2017-07-25.
 */

class Activity2 extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApp().getLoginInfo();
    }

    private Runnable login = new Runnable() {
        @Override
        public void run() {
            Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().token);
            Intent intent = new Intent(getBaseContext(), login.class);
            startActivityForResult(intent, _RESULT_LOGIN);
        }
    };

    protected void login() {
        mHandler.removeCallbacks(login);
        mHandler.post(login);
    }

    private Runnable logout = new Runnable() {
        @Override
        public void run() {
            Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().token);
            getApp().clearUserInfo();
            showLogIn();
        }
    };

    protected void logout() {
        mHandler.removeCallbacks(logout);
        mHandler.post(logout);
    }

    protected void showLogIn() {
    }
}
