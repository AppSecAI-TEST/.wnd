package kr.co.windowfun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.app.Activity2;
import kr.co.windowfun.widget._TAG;

/**
 * Created by isyoon on 2017-07-18.
 */

class _Activity extends Activity2 implements _TAG {
    String error_message;
    String error_code;
    String result_code;
    String token;
    String userid;

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
        token = getString(getJSONObject(response, _result_data), result_data.token);
        userid = getString(getJSONObject(response, _result_data), result_data.userid);
        //Toast.makeText(this, userid + ":" + token, Toast.LENGTH_SHORT).show();
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

    private Runnable getToken = new Runnable() {
        @Override
        public void run() {
            if (!isACTIONMAIN()) {
                return;
            }
            SharedPreferences sharedPref = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            getApp().token = sharedPref.getString("token", null);
            Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().token);
            if (TextUtils.isEmpty(getApp().token)) {
                Log.wtf(__CLASSNAME__, getMethodName() + ":" + getApp().token);
                login();
            }
        }
    };

    protected void getToken() {
        mHandler.removeCallbacks(getToken);
        mHandler.postDelayed(getToken, TIMER_OPEN_LONG);
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
        mHandler.postDelayed(login, TIMER_OPEN_SHORT);
    }


    private Runnable setToken = new Runnable() {
        @Override
        public void run() {
            Log.d(__CLASSNAME__, getMethodName() + ":" + getApp().token);
            SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), MODE_PRIVATE).edit();
            editor.putString("token", getApp().token);
            editor.apply();
        }
    };

    protected void setToken(String token) {
        getApp().token = token;
        mHandler.removeCallbacks(setToken);
        mHandler.postDelayed(setToken, TIMER_OPEN_SHORT);
    }

    protected void clearToken() {
        setToken(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //test
        getToken();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(getToken);
    }
}
