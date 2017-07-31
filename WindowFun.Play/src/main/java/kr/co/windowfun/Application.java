package kr.co.windowfun;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;

import kr.co.windowfun.app.__Application;
import kr.co.windowfun.widget._TAG;

/**
 * Created by isyoon on 2017-07-26.
 */

public class Application extends __Application implements _TAG {
    String userid;
    String token;
    JSONArray result_c1;
    JSONArray result_c3;
    JSONArray result_c4;
    JSONArray result_c5;

    protected boolean isLogin() {
        Log.d(__CLASSNAME__, getMethodName() + ":" + userid + ":" + token);
        return !TextUtils.isEmpty(userid) && !TextUtils.isEmpty(token);
    }

    private Runnable getLoginInfo = new Runnable() {
        @Override
        public void run() {
            SharedPreferences sharedPref = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            userid = sharedPref.getString("userid", null);
            token = sharedPref.getString("token", null);
            Log.d(__CLASSNAME__, getMethodName() + ":" + userid + ":" + token);
        }
    };

    protected void getLoginInfo() {
        mHandler.removeCallbacks(getLoginInfo);
        mHandler.post(getLoginInfo);
    }

    private Runnable setUserInfo = new Runnable() {
        @Override
        public void run() {
            //Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().userid + ":" + getApp().token);
            SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), MODE_PRIVATE).edit();
            editor.putString("token", token);
            editor.putString("userid", userid);
            editor.apply();
        }
    };

    protected void setUserInfo() {
        mHandler.removeCallbacks(setUserInfo);
        mHandler.post(setUserInfo);
    }

    protected void clearUserInfo() {
        this.userid = null;
        this.token = null;
        setUserInfo();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getLoginInfo();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        setUserInfo();
    }
}
