package kr.co.windowfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 로그인처리
 * Created by isyoon on 2017-07-25.
 */

class Activity2 extends Activity {
    //private Runnable getLoginInfo = new Runnable() {
    //    @Override
    //    public void run() {
    //        if (!isACTIONMAIN()) {
    //            return;
    //        }
    //        getApp().getLoginInfo();
    //        Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().userid + ":" + getApp().token);
    //        if (!getApp().isLogin()) {
    //            Log.wtf(__CLASSNAME__, getMethodName() + ":" + getApp().token);
    //            login();
    //        }
    //    }
    //};
    //
    //protected void getLoginInfo() {
    //    mHandler.removeCallbacks(getLoginInfo);
    //    mHandler.postDelayed(getLoginInfo, TIMER_OPEN_LONG);
    //}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApp().getLoginInfo();
    }

    private Runnable login = new Runnable() {
        @Override
        public void run() {
            Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().token);
            Intent intent = new Intent(Activity2.this, login.class);
            startActivity(intent);
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
