package kr.co.windowfun;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.app.Activity2;
import kr.co.windowfun.widget._TAG;

/**
 * Created by isyoon on 2017-07-18.
 */

class _Activity extends Activity2 implements _TAG {
    final String root = Environment.getExternalStorageDirectory().getAbsolutePath();
    String root_mp4 = root + "/.mp4";

    String error_message;
    String error_code;
    String result_code;

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            Log.d(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[response]\n" + response.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onSuccess(statusCode, headers, response);
        error_message = getString(response, _error_message);
        error_code = getString(response, _error_code);
        result_code = getString(response, _result_code);
        //login data
        getApp().token = getString(getJSONObject(response, _result_data), result_data.token);
        getApp().userid = getString(getJSONObject(response, _result_data), result_data.userid);
        //Toast.makeText(this, getApp().userid + ":" + getApp().token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
            Log.d(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[throwable]\n" + throwable + "\n[errorResponse]\n" + errorResponse.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    protected _Application getApp() {
        return (_Application) getApplication();
    }

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
            Intent intent = new Intent(_Activity.this, login.class);
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
