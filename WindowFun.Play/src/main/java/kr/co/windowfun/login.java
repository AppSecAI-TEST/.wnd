package kr.co.windowfun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by isyoon on 2017-07-18.
 */

public class login extends _Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ((EditText) findViewById(R.id.id)).setText("windowfun4"); //test
        ((EditText) findViewById(R.id.pw)).setText("4321"); //test

        //출처: http://202psj.tistory.com/251 [알레폰드의 IT 이모저모]
        //((EditText)findViewById(R.id.id)).setPrivateImeOptions("defaultInputmode=english;");
        //((EditText)findViewById(R.id.pw)).setPrivateImeOptions("defaultInputmode=english;");
        (/*(EditText)*/findViewById(R.id.id)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v instanceof EditText && hasFocus) {
                    ((EditText) v).setPrivateImeOptions("defaultInputmode=english;");
                }
            }
        });
        ((EditText)findViewById(R.id.pw)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    send();
                    handled = true;
                }
                return handled;            }
        });


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        if (!getApp().isLogin()) {
            showLogIn();
        } else {
            hideLogin();
        }
    }

    private Runnable showLogin = new Runnable() {
        @Override
        public void run() {
            findViewById(R.id.id).setVisibility(View.VISIBLE);
            findViewById(R.id.pw).setVisibility(View.VISIBLE);
            findViewById(R.id.login).setVisibility(View.VISIBLE);
            findViewById(R.id.logout).setVisibility(View.INVISIBLE);
        }
    };

    protected void showLogIn() {
        //Log.d(__CLASSNAME__, getMethodName());
        mHandler.removeCallbacks(showLogin);
        mHandler.postDelayed(showLogin, 0);
    }

    private Runnable hideLogin = new Runnable() {
        @Override
        public void run() {
            findViewById(R.id.id).setVisibility(View.INVISIBLE);
            findViewById(R.id.pw).setVisibility(View.INVISIBLE);
            findViewById(R.id.login).setVisibility(View.INVISIBLE);
            findViewById(R.id.logout).setVisibility(View.VISIBLE);
        }
    };

    protected void hideLogin() {
        //Log.d(__CLASSNAME__, getMethodName());
        mHandler.removeCallbacks(hideLogin);
        mHandler.postDelayed(hideLogin, 0);
    }

    private void send() {
        String id = "" + ((EditText) findViewById(R.id.id)).getText();
        String pw = "" + ((EditText) findViewById(R.id.pw)).getText();
        String url = "http://windowfun.co.kr/Manager/API/W_login.html?userid=windowfun4&password=4321";
        //url = "http://windowfun.co.kr/Manager/API/W_login.html?userid=" + id + "&password=" + pw;
        //send(url);
        url = "http://windowfun.co.kr/Manager/API/W_login.html";
        RequestParams params = new RequestParams();
        params.put("userid", id);
        params.put("password", pw);
        send(url, params);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Toast.makeText(this, getApp().userid + ":" + error_message, Toast.LENGTH_SHORT).show();
        if ("0".equalsIgnoreCase(error_code) && "0".equalsIgnoreCase(result_code)) {
            Log.w(__CLASSNAME__, getMethodName() + ":" + "[[" + getApp().token + "]]");
            getApp().setUserInfo();
            finish();
        } else {
            Log.w(__CLASSNAME__, getMethodName() + ":" + "[[" + error_message + "]]");
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }
}
