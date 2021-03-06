package kr.co.windowfun;

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
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.util.CFile;
import kr.co.windowfun.util.TextUtil;
import kr.co.windowfun.widget.ProgressBar;

/**
 * 로그인/조회/다운
 * Created by isyuun on 2017-07-12.
 */

class home2 extends home implements __API {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getBaseContext(), demo.class));
                }
            });

            findViewById(R.id.demo2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getBaseContext(), demo2.class));
                }
            });

            findViewById(R.id.demo3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getBaseContext(), demo3.class));
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
                    _send();
                }
            });

            findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete();
                }
            });

            findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reset();
                }
            });

            findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _send();
                }
            });

            findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.banner_long).setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        init();
    }

    private void _send() {
        if (getApp().isLogin()) send();
        else login();
    }

    private Runnable reset = new Runnable() {
        @Override
        public void run() {
            logout();
            delete();
        }
    };

    protected void reset() {
        mHandler.removeCallbacks(reset);
        mHandler.post(reset);
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
        mHandler.postDelayed(init, TIMER_MSEC_1T);
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
        clear();
        mHandler.removeCallbacks(send);
        mHandler.postDelayed(send, TIMER_MSEC_1H);
    }

    Runnable send = new Runnable() {
        @Override
        public void run() {
            Log.w(__CLASSNAME__, getMethodName() + ":" + getApp().userid + ":" + getApp().token);
            String url;
            //url = "http://windowfun.co.kr/Manager/API/W_cfg.html?userid=windowfun4&token=C3S/ugUsBlM03ohSSxEwlsXURU9dfm9czOT4KcI6xihfEHM0st/d^ohgqos"; //test
            //url = "http://windowfun.co.kr/Manager/API/W_cfg.html?userid=" + getApp().userid + "&token=" + getApp().token; //test
            //send(url);
            url = W_API_URL_CONFIG;
            RequestParams params = new RequestParams();
            params.put("userid", getApp().userid);
            params.put("token", getApp().token);
            send(url, params);
        }
    };

    private Runnable open = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getBaseContext(), main.class));
        }
    };

    private void open() {
        mHandler.removeCallbacks(open);
        mHandler.postDelayed(open, TIMER_MSEC_1H);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Toast.makeText(this, getApp().userid + ":" + error_message, Toast.LENGTH_SHORT).show();
        if ("0".equalsIgnoreCase(error_code) && "0".equalsIgnoreCase(result_code)) {
            //Log.w(__CLASSNAME__, getMethodName() + ":" + "[[" + getApp().token + "]]");
            getApp().result_type = getJSONObject(response, result.result_type);
            getApp().result_menu = getJSONArray(response, result.result_menu);
            getApp().result_c1 = getJSONArray(response, result.result_c1);
            getApp().result_c3 = getJSONArray(response, result.result_c3);
            getApp().result_c4 = getJSONArray(response, result.result_c4);
            getApp().result_c5 = getJSONArray(response, result.result_c5);
            getApp().result_banner = getJSONArray(response, result.result_banner);
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c1]\n" + toString(getApp().result_c1, 2));
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c4]\n" + toString(getApp().result_c4, 2));
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c5]\n" + toString(getApp().result_c5, 2));
            //Log.i(__CLASSNAME__, getMethodName() + "\n[result_c5]\n" + toString(getApp().result_c5, 2));
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
        delete(getApp().result_c3);
        delete(getApp().result_c4);
        delete(getApp().result_c5);
    }

    private void delete(JSONArray results) {
        //Log.w(__CLASSNAME__, getMethodName() + results);
        for (int i = 0; i < (results != null ? results.length() : 0); i++) {
            try {
                JSONObject item = (JSONObject) results.get(i);
                final String file_name = item.getString(result_c.file_name);
                final String file_size = item.getString(result_c.file_size);
                if (TextUtils.isEmpty(file_name) || TextUtils.isEmpty(file_size)) {
                    continue;
                }
                final CFile file = new CFile(file_name, file_size);
                if (file.equal()) {
                    Log.e(__CLASSNAME__, getMethodName() + "[FILE]" + "[DEL]" + file.sizes() + ":" + file.exists() + ":" + file.equal() + "\n[file]" + file.file + "\n[file_size]" + file_size + "\n[uri]" + file_name + "\n[url]" + file.url + "\n[name]" + file.name + "\n[path]" + file.path + "\n[size]" + file.size);
                    new File(file.path).delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Map<String, Uri> adds = new HashMap<>();

    private void add(JSONArray results) {
        for (int i = 0; i < (results != null ? results.length() : 0); i++) {
            try {
                JSONObject item = (JSONObject) results.get(i);
                final String file_name = item.getString(result_c.file_name);
                final String file_size = item.getString(result_c.file_size);
                if (TextUtils.isEmpty(file_name) || TextUtils.isEmpty(file_size)) {
                    continue;
                }
                final CFile file = new CFile(file_name, file_size);
                if (!file.sizes()) {
                    if (adds.get(file.path) == null) {
                        Log.e(__CLASSNAME__, getMethodName() + "[FILE]" + "[ADD]" + file.sizes() + ":" + file.exists() + ":" + file.equal() + "\n[file]" + file.file + "\n[file_size]" + file_size + "\n[uri]" + file_name + "\n[url]" + file.url + "\n[name]" + file.name + "\n[path]" + file.path + "\n[size]" + file.size);
                        totalSizes += Long.parseLong(file_size);
                        adds.put(file.path, Uri.parse(file_name));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Map<String, Boolean> downs = new HashMap<>();
    int count = 0;
    private Runnable down = new Runnable() {
        @Override
        public void run() {
            totalSizes = 0;

            adds.clear();
            add(getApp().result_c1);
            add(getApp().result_c3);
            add(getApp().result_c4);
            add(getApp().result_c5);

            for (final Map.Entry<String, Uri> entry : adds.entrySet()) {
                final String file_name = entry.getValue().toString();
                final String path = entry.getKey();
                final String url = TextUtil.getFileUrl(file_name);
                Log.w(__CLASSNAME__, getMethodName() + "[FILE]" + "[DL]"/* + "\n[file_size]" + file_size*/ + "\n[uri]" + file_name + "\n[url]" + url/* + "\n[name]" + name*/ + "\n[path]" + path/* +  "\n[size]" + size*/);
                if (downs.get(path) == null || !downs.get(path)) {
                    down(url, path);
                    downs.put(path, true);
                }
            }

            bytesWrittens.clear();
            for (final Map.Entry<String, Boolean> entry : downs.entrySet()) {
                bytesWrittens.put(entry.getKey(), 0L);
            }

            if (adds.size() == 0) {
                complete();
                open();
            }

            Log.wtf(__CLASSNAME__, getMethodName() + "[DOWN]" + "[!!!START!!!]" + "[" + count + "/" + downs.size() + "/" + adds.size() + "]");
        }
    };

    private void down() {
        mHandler.removeCallbacks(down);
        mHandler.postDelayed(down, TIMER_MSEC_1H);
    }

    private Runnable complete = new Runnable() {
        @Override
        public void run() {
            ((TextView) findViewById(R.id.label)).setText("다운완료...");
            ((ProgressBar) findViewById(R.id.progress1)).setMax((int) PROGRESS_MAX);
            ((ProgressBar) findViewById(R.id.progress1)).setProgress((int) PROGRESS_MAX);
            ((ProgressBar) findViewById(R.id.progress2)).setMax((int) PROGRESS_MAX);
            ((ProgressBar) findViewById(R.id.progress2)).setProgress((int) PROGRESS_MAX);
        }
    };

    private void complete() {
        Log.wtf(__CLASSNAME__, getMethodName() + "[DOWN]" + "[!!!END!!!]" + "[" + count + "/" + downs.size() + "/" + adds.size() + "]");
        count = 0;
        adds.clear();
        downs.clear();
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
        if (count == downs.size()) {
            complete();
            open();
        }
    }

    String src;
    String dst;
    long bytesWritten;
    Map<String, Long> bytesWrittens = new HashMap<>();
    long totalSize;
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
                for (final Map.Entry<String, Boolean> entry : downs.entrySet()) {
                    if (dst.equalsIgnoreCase(entry.getKey())) {
                        //Log.d(__CLASSNAME__, getMethodName() + ":" + index + "->" + bytesWritten + ":" + ":" + entry.getValue() + "->" + dst);
                        bytesWrittens.put(entry.getKey(), bytesWritten);
                        break;
                    }
                }
                long bytes = 0;
                for (final Map.Entry<String, Long> entry : bytesWrittens.entrySet()) {
                    bytes += entry.getValue();
                }
                //label
                String text = "다운중...";
                text += "\t";
                text += "[" + count + "/" + downs.size() + "건" + "]";
                text += "\t";
                //text += "[" + (int) ((float) bytesWritten / (float) totalSize * PROGRESS_PER) + "/" + (int) ((float) totalSize / (float) totalSize * PROGRESS_PER) + "%" + "]";
                //text += "\t";
                text += "[" + (int) ((float) bytes / (float) totalSizes * PROGRESS_PER) + "/" + (int) ((float) totalSizes / (float) totalSizes * PROGRESS_PER) + "%" + "]";
                text += "\t";
                //text += Uri.decode(src);
                //text += "\t";
                //text += "->" + dst;
                //text += "\t";
                //text += TextUtil.getFileName(dst);
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
