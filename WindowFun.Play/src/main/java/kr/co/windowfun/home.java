package kr.co.windowfun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by isyoon on 2017-07-12.
 */

public class home extends _Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.typea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, main.class));
            }
        });

        findViewById(R.id.typeb1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, main2.class));
            }
        });

        findViewById(R.id.typeb2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, main3.class));
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        send();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!getApp().isLogin()) {
            showLogIn();
        } else {
            hideLogin();
        }
    }

    private Runnable showLogin = new Runnable() {
        @Override
        public void run() {
            //findViewById(R.id.id).setVisibility(View.VISIBLE);
            //findViewById(R.id.pw).setVisibility(View.VISIBLE);
            findViewById(R.id.login).setVisibility(View.VISIBLE);
            findViewById(R.id.logout).setVisibility(View.INVISIBLE);
        }
    };

    protected void showLogIn() {
        Log.d(__CLASSNAME__, getMethodName());
        mHandler.removeCallbacks(showLogin);
        mHandler.postDelayed(showLogin, 0);
    }

    private Runnable hideLogin = new Runnable() {
        @Override
        public void run() {
            //findViewById(R.id.id).setVisibility(View.INVISIBLE);
            //findViewById(R.id.pw).setVisibility(View.INVISIBLE);
            findViewById(R.id.login).setVisibility(View.INVISIBLE);
            findViewById(R.id.logout).setVisibility(View.VISIBLE);
        }
    };

    protected void hideLogin() {
        Log.d(__CLASSNAME__, getMethodName());
        mHandler.removeCallbacks(hideLogin);
        mHandler.postDelayed(hideLogin, 0);
    }

    private void send() {
        mHandler.removeCallbacks(send);
        mHandler.postDelayed(send, TIMER_OPEN_SHORT);
    }

    Runnable send = new Runnable() {
        @Override
        public void run() {
            Log.e(__CLASSNAME__, getMethodName() + ":" + getApp().userid + ":" + getApp().token);
            String url = "http://windowfun.co.kr/Manager/API/W_cfg.html?userid=windowfun4&token=C3S/ugUsBlM03ohSSxEwlsXURU9dfm9czOT4KcI6xihfEHM0st/d^ohgqos";
            //url = "http://windowfun.co.kr/Manager/API/W_cfg.html?userid=" + getApp().userid + "&token=" + getApp().token;
            //send(url);
            url = "http://windowfun.co.kr/Manager/API/W_cfg.html";
            RequestParams params = new RequestParams();
            params.put("userid", getApp().userid);
            params.put("token", getApp().token);
            send(url, params);
        }
    };

    private Runnable open = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(home.this, main4.class));
        }
    };

    private void open() {
        mHandler.removeCallbacks(open);
        mHandler.postDelayed(open, TIMER_OPEN_SHORT);
    }

    Map<String, Uri> lists = new HashMap<>();

    private void downs(JSONArray results) {
        for (int i = 0; i < results.length(); i++) {
            String filename = null;
            String filesize = null;
            JSONObject item = null;
            try {
                item = (JSONObject) results.get(i);
                filename = item.getString(result_c.filename);
                filesize = item.getString(result_c.filesize);
                if (!TextUtils.isEmpty(filename) && !TextUtils.isEmpty(filesize)) {
                    String name = filename.substring(filename.lastIndexOf("/") + 1);
                    String path = root_mp4 + File.separator + name;
                    String url = filename.substring(0, filename.lastIndexOf("/") + 1) + Uri.encode(name/*, "UTF-8"*/);
                    File file = new File(path);
                    boolean add = true;
                    long size = 0L;
                    if (file.exists() && file.isFile()) {
                        size = file.length();
                        if (size == Long.parseLong(filesize)) {
                            add = false;
                            item.put(result_c.filename, path);
                        }
                    }
                    //Log.w(__CLASSNAME__, getMethodName() + ":" + add + "\n[filesize]" + filesize + "\n[uri]" + filename + "\n[url]" + url + "\n[name]" + name + "\n[path]" + path +  "\n[size]" + size);
                    if (add) {
                        lists.put(path, Uri.parse(filename));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    int count = 0;
    private Runnable down = new Runnable() {
        @Override
        public void run() {
            lists.clear();

            downs(getApp().result_c1);
            downs(getApp().result_c2);
            downs(getApp().result_c3);
            downs(getApp().result_c4);

            count = 0;
            for (final Map.Entry<String, Uri> entry : lists.entrySet()) {
                Log.wtf(__CLASSNAME__, getMethodName() + "\n[key]" + entry.getKey() + "\n[value]" + entry.getValue());
                AsyncHttpClient client = new AsyncHttpClient();
                final String filename = String.valueOf(entry.getValue());
                final String name = filename.substring(filename.lastIndexOf("/") + 1);
                final String path = entry.getKey();
                final String url = filename.substring(0, filename.lastIndexOf("/") + 1) + Uri.encode(name/*, "UTF-8"*/);
                client.get(url, new FileAsyncHttpResponseHandler(new File(path), false, false, true) {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                        Log.e(__CLASSNAME__, getMethodName() + "\n" + url + "\n[statusCode]" + statusCode + "\n[headers]" + headers + "\n[throwable]" + throwable + "\n[file]" + file);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, File response) {
                        Log.w(__CLASSNAME__, getMethodName() + "\n" + url + "\n[statusCode]" + statusCode + "\n[headers]" + headers + "\n[response]" + response);
                        count++;
                        if (count == lists.size()) {
                            open();
                        }
                    }
                });
            }

            if (lists.size() == 0) {
                open();
            }
        }
    };

    private void down() {
        mHandler.removeCallbacks(down);
        mHandler.postDelayed(down, TIMER_OPEN_SHORT);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Toast.makeText(this, getApp().userid + ":" + error_message, Toast.LENGTH_SHORT).show();
        if ("0".equalsIgnoreCase(error_code) && "0".equalsIgnoreCase(result_code)) {
            //Log.e(__CLASSNAME__, getMethodName() + ":" + "[[" + getApp().token + "]]");
            getApp().result_c1 = getJSONArray(response, "result_c1");
            getApp().result_c2 = getJSONArray(response, "result_c2");
            getApp().result_c3 = getJSONArray(response, "result_c3");
            getApp().result_c4 = getJSONArray(response, "result_c4");
            getApp().result_c2 = getApp().result_c1; //test
            getApp().result_c3 = getApp().result_c1; //test
            getApp().result_c4 = getApp().result_c1; //test
            //Log.w(__CLASSNAME__, getMethodName() + "\n[result_c1]\n" + toString(getApp().result_c1, 2));
            //Log.w(__CLASSNAME__, getMethodName() + "\n[result_c2]\n" + toString(getApp().result_c2, 2));
            //Log.w(__CLASSNAME__, getMethodName() + "\n[result_c3]\n" + toString(getApp().result_c3, 2));
            //Log.w(__CLASSNAME__, getMethodName() + "\n[result_c4]\n" + toString(getApp().result_c4, 2));
            //open();
            down();
        } else {
            Log.e(__CLASSNAME__, getMethodName() + ":" + "[[" + error_message + "]]");
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }
}
