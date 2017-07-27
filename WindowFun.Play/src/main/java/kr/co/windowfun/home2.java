package kr.co.windowfun;

import android.app.*;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.widget.CFile;
import kr.co.windowfun.widget.ProgressBar;
import kr.co.windowfun.widget._TextUtil;

/**
 * 로그인/조회/다운
 * Created by isyoon on 2017-07-12.
 */

class home2 extends home {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(home2.this, demo.class));
                }
            });

            findViewById(R.id.demo2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(home2.this, demo2.class));
                }
            });

            findViewById(R.id.demo3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(home2.this, demo3.class));
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

            findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        init();
    }

    private Runnable init = new Runnable() {
        @Override
        public void run() {
            if (getApp().isLogin()) send();
            else login();
        }
    };

    private void init() {
        mHandler.removeCallbacks(init);
        mHandler.postDelayed(init, TIMER_OPEN_LONG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getApp().isLogin()) hideLogin();
        else showLogIn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case _RESULT_OK:
                break;
            case _RESULT_CANCELED:
                break;
            case _RESULT_LOGIN:
                send();
                break;
            default:
                break;
        }
    }

    private Runnable showLogin = new Runnable() {
        @Override
        public void run() {
            try {
                //findViewById(R.id.id).setVisibility(View.VISIBLE);
                //findViewById(R.id.pw).setVisibility(View.VISIBLE);
                findViewById(R.id.login).setVisibility(View.VISIBLE);
                findViewById(R.id.logout).setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            try {
                //findViewById(R.id.id).setVisibility(View.INVISIBLE);
                //findViewById(R.id.pw).setVisibility(View.INVISIBLE);
                findViewById(R.id.login).setVisibility(View.INVISIBLE);
                findViewById(R.id.logout).setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            Log.w(__CLASSNAME__, getMethodName() + ":" + getApp().userid + ":" + getApp().token);
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
            startActivity(new Intent(home2.this, main.class));
        }
    };

    private void open() {
        mHandler.removeCallbacks(open);
        mHandler.postDelayed(open, TIMER_OPEN_SHORT);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Toast.makeText(this, getApp().userid + ":" + error_message, Toast.LENGTH_SHORT).show();
        if ("0".equalsIgnoreCase(error_code) && "0".equalsIgnoreCase(result_code)) {
            //Log.w(__CLASSNAME__, getMethodName() + ":" + "[[" + getApp().token + "]]");
            getApp().result_c1 = getJSONArray(response, result.result_c1);
            getApp().result_c2 = getJSONArray(response, result.result_c2);
            getApp().result_c3 = getJSONArray(response, result.result_c3);
            getApp().result_c4 = getJSONArray(response, result.result_c4);
            getApp().result_c2 = getApp().result_c1; //test
            getApp().result_c3 = getApp().result_c1; //test
            getApp().result_c4 = getApp().result_c1; //test
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c1]\n" + toString(getApp().result_c1, 2));
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c2]\n" + toString(getApp().result_c2, 2));
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c3]\n" + toString(getApp().result_c3, 2));
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c4]\n" + toString(getApp().result_c4, 2));
            //open();
            down();
        } else {
            Log.w(__CLASSNAME__, getMethodName() + ":" + "[[" + error_message + "]]");
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    private void delete() {
        delete(getApp().result_c1);
        delete(getApp().result_c2);
        delete(getApp().result_c3);
        delete(getApp().result_c4);
    }

    private void delete(JSONArray results) {
        //Log.wtf(__CLASSNAME__, getMethodName() + results);
        for (int i = 0; i < (results != null ? results.length() : 0); i++) {
            try {
                JSONObject item = (JSONObject) results.get(i);
                final String filename = item.getString(result_c.file_name);
                final String filesize = item.getString(result_c.filesize);
                if (TextUtils.isEmpty(filename) || TextUtils.isEmpty(filesize)) {
                    continue;
                }
                final CFile file = new CFile(filename, filesize);
                if (file.equal()) {
                    Log.wtf(__CLASSNAME__, getMethodName() + "[FILE]" + "[DEL]" + file.sizes() + ":" + file.exists() + ":" + file.equal() + "\n[file]" + file.file + "\n[filesize]" + filesize + "\n[uri]" + filename + "\n[url]" + file.url + "\n[name]" + file.name + "\n[path]" + file.path + "\n[size]" + file.size);
                    new File(file.path).delete();
                }
            } catch (Exception e) {
                //e.printStackTrace();
                Log.wtf(__CLASSNAME__, getMethodName() + "\n" + e.getStackTrace());
            }
        }
    }

    Map<String, Uri> lists = new HashMap<>();

    private void add(JSONArray results) {
        for (int i = 0; i < results.length(); i++) {
            try {
                JSONObject item = (JSONObject) results.get(i);
                final String filename = item.getString(result_c.file_name);
                final String filesize = item.getString(result_c.filesize);
                if (TextUtils.isEmpty(filename) || TextUtils.isEmpty(filesize)) {
                    continue;
                }
                final CFile file = new CFile(filename, filesize);
                if (!file.sizes()) {
                    if (lists.get(file.path) == null) {
                        Log.wtf(__CLASSNAME__, getMethodName() + "[FILE]" + "[ADD]" + file.sizes() + ":" + file.exists() + ":" + file.equal() + "\n[file]" + file.file + "\n[filesize]" + filesize + "\n[uri]" + filename + "\n[url]" + file.url + "\n[name]" + file.name + "\n[path]" + file.path + "\n[size]" + file.size);
                        totalSizes += Long.parseLong(filesize);
                        bytesWrittens.add(0L);
                        lists.put(file.path, Uri.parse(filename));
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
                Log.wtf(__CLASSNAME__, getMethodName() + "\n" + e.getStackTrace());
            }
        }
    }

    int count = 0;
    private Runnable down = new Runnable() {
        @Override
        public void run() {
            bytesWrittens.clear();
            totalSizes = 0;
            lists.clear();

            add(getApp().result_c1);
            add(getApp().result_c2);
            add(getApp().result_c3);
            add(getApp().result_c4);

            count = 0;

            for (final Map.Entry<String, Uri> entry : lists.entrySet()) {
                final String filename = entry.getValue().toString();
                final String path = entry.getKey();
                final String url = _TextUtil.getFileUrl(filename);
                Log.w(__CLASSNAME__, getMethodName() + "[FILE]" + "[DL]"/* + "\n[filesize]" + filesize*/ + "\n[uri]" + filename + "\n[url]" + url/* + "\n[name]" + name*/ + "\n[path]" + path/* +  "\n[size]" + size*/);
                down(url, path);
            }

            if (lists.size() == 0) {
                complete();
                open();
            }
        }
    };

    private void down() {
        Log.wtf(__CLASSNAME__, getMethodName() + "[DOWN]" + "[!!!START!!!]");
        mHandler.removeCallbacks(down);
        mHandler.postDelayed(down, TIMER_OPEN_SHORT);
    }

    private Runnable complete = new Runnable() {
        @Override
        public void run() {
            ((TextView) findViewById(R.id.label)).setText("다운완료...");
        }
    };

    private void complete() {
        Log.wtf(__CLASSNAME__, getMethodName() + "[DOWN]" + "[!!!END!!!]" + lists.size());
        mHandler.removeCallbacks(complete);
        mHandler.post(complete);
    }

    @Override
    protected void onFailure(String src, String dst, int statusCode, Header[] headers, Throwable throwable, File file) {
        super.onFailure(src, dst, statusCode, headers, throwable, file);
        down(src, dst);
    }

    @Override
    protected void onSuccess(String src, String dst, int statusCode, Header[] headers, File response) {
        super.onSuccess(src, dst, statusCode, headers, response);
        count++;
        if (count == lists.size()) {
            complete();
            open();
        }
    }

    String src;
    String dst;
    long bytesWritten;
    long totalSize;
    List<Long> bytesWrittens = new ArrayList<>();
    long totalSizes;

    @Override
    protected void onProgress(String src, String dst, long bytesWritten, long totalSize) {
        super.onProgress(src, dst, bytesWritten, totalSize);
        this.src = src;
        this.dst = dst;
        this.bytesWritten = bytesWritten;
        this.totalSize = totalSize;
        mHandler.removeCallbacks(onProgress);
        mHandler.post(onProgress);
    }

    private Runnable onProgress = new Runnable() {
        @Override
        public void run() {
            try {
                //bytes
                int index = 0;
                for (final Map.Entry<String, Uri> entry : lists.entrySet()) {
                    if (dst.equalsIgnoreCase(entry.getKey())) {
                        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + index + ":" + bytesWritten + ":" + dst);
                        bytesWrittens.set(index, bytesWritten);
                        break;
                    }
                    index++;
                }
                long bytes = 0;
                for (Long item : bytesWrittens) {
                    bytes += item;
                }
                //label
                String text = "다운중...";
                text += "\t";
                text += "(" + (index + 1) + "/" + lists.size() + "건" + ")";
                text += "\t";
                //text += "(" + (int) ((float) bytesWritten / (float) totalSize * PROGRESS_PERCENT) + "/" + (int) ((float) totalSize / (float) totalSize * PROGRESS_PERCENT) + "%" + ")";
                //text += "\t";
                text += "(" + (int) ((float) bytes / (float) totalSizes * PROGRESS_PERCENT) + "/" + (int) ((float) totalSizes / (float) totalSizes * PROGRESS_PERCENT) + "%" + ")";
                text += "\t";
                //text += Uri.decode(src);
                //text += "\t";
                //text += "->" + dst;
                //text += "\t";
                //text += _TextUtil.getFileName(dst);
                //text += "\t";
                ((TextView) findViewById(R.id.label)).setText(text);
                //color
                int blue = android.R.color.holo_blue_light;
                int red = android.R.color.holo_red_light;
                //progress1
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((ProgressBar) findViewById(R.id.progress1)).setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), blue)));
                } else {
                    ((ProgressBar) findViewById(R.id.progress1)).getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(), blue), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                ((ProgressBar) findViewById(R.id.progress1)).setMax((int) (/*totalSize / totalSize * */PROGRESS_MAX));
                ((ProgressBar) findViewById(R.id.progress1)).setProgress((int) ((float) bytesWritten / (float) totalSize * PROGRESS_MAX));
                //progress2
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((ProgressBar) findViewById(R.id.progress2)).setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), red)));
                } else {
                    ((ProgressBar) findViewById(R.id.progress2)).getProgressDrawable().setColorFilter(ContextCompat.getColor(getApplicationContext(), red), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                ((ProgressBar) findViewById(R.id.progress2)).setMax((int) (/*totalSizes / totalSizes * */PROGRESS_MAX));
                ((ProgressBar) findViewById(R.id.progress2)).setProgress((int) ((float) bytes / (float) totalSizes * PROGRESS_MAX));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}