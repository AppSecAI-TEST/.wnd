package kr.co.windowfun;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONObject;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * 관리자기능
 * Created by isyoon on 2017-07-27.
 */

class home extends _Activity {
    public boolean show = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {
            findViewById(R.id.left).setOnClickListener(mOnClickListener);
            findViewById(R.id.right).setOnClickListener(mOnClickListener);
            findViewById(R.id.left).setOnLongClickListener(mOnLongClickListener);
            findViewById(R.id.right).setOnLongClickListener(mOnLongClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.admin).setVisibility(View.INVISIBLE); //test
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i(__CLASSNAME__, getMethodName() + show);
            if (!show) hides();
        }
    };

    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Log.i(__CLASSNAME__, getMethodName() + show);
            boolean ret = false;
            if (show) {
                shows();
                ret = true;
            }
            hide();
            show = true;
            return ret;
        }
    };

    private Runnable hide = new Runnable() {
        @Override
        public void run() {
            show = false;
            Log.wtf(__CLASSNAME__, getMethodName() + show);
        }
    };

    private void hide() {
        mHandler.removeCallbacks(hide);
        mHandler.postDelayed(hide, TIMER_RESET_SHORT);
    }

    private Runnable shows = new Runnable() {
        @Override
        public void run() {
            if (findViewById(R.id.admin).getVisibility() == View.VISIBLE) return;
            YoYo.with(Techniques.DropOut)
                    .duration(1000)
                    .onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                        }
                    })
                    .playOn(findViewById(R.id.admin));
            findViewById(R.id.admin).setVisibility(View.VISIBLE);
            Log.wtf(__CLASSNAME__, getMethodName() + show);
        }
    };

    private void shows() {
        mHandler.removeCallbacks(shows);
        mHandler.post(shows);
    }

    private Runnable hides = new Runnable() {
        @Override
        public void run() {
            if (findViewById(R.id.admin).getVisibility() == View.INVISIBLE) return;
            findViewById(R.id.admin).setVisibility(View.INVISIBLE);
            //YoYo.with(Techniques.Hinge)
            //        .duration(1000)
            //        .onEnd(new YoYo.AnimatorCallback() {
            //            @Override
            //            public void call(Animator animator) {
            //                findViewById(R.id.admin).setVisibility(View.INVISIBLE);
            //            }
            //        })
            //        .playOn(findViewById(R.id.admin));
            Log.wtf(__CLASSNAME__, getMethodName() + show);
        }
    };

    private void hides() {
        mHandler.removeCallbacks(hides);
        mHandler.post(hides);
    }

    String report;

    private Runnable reports = new Runnable() {
        @Override
        public void run() {
            ((TextView) findViewById(R.id.report)).setText((CharSequence) home.this.report);
        }
    };

    private void reports(String report) {
        this.report = report;
        mHandler.removeCallbacks(reports);
        mHandler.post(reports);
    }

    private Runnable clear = new Runnable() {
        @Override
        public void run() {
            ((TextView) findViewById(R.id.report)).setText("");
        }
    };

    protected void clear() {
        mHandler.removeCallbacks(clear);
        mHandler.post(clear);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        try {
            reports(getMethodName() + "\n[status]\n" + statusCode + "\n[headers]\n" + debugHeaders(headers) + "\n[throwable]\n" + throwable + "\n[errorResponse]\n" + errorResponse.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        try {
            reports(getMethodName() + "\n[status]\n" + statusCode + "\n[headers]\n" + debugHeaders(headers) + "\n[response]\n" + response.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onFailure(String src, String dst, int statusCode, Header[] headers, Throwable throwable, File file) {
        super.onFailure(src, dst, statusCode, headers, throwable, file);
        reports(getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[statusCode]" + statusCode + "\n[header]\n" + debugHeaders(headers) + "\n[throwable]\n" + throwable.getStackTrace() + "\n[file]" + file);
    }

    @Override
    protected void onSuccess(String src, String dst, int statusCode, Header[] headers, File response) {
        super.onSuccess(src, dst, statusCode, headers, response);
        //reports(getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[statusCode]" + statusCode + "\n[header]\n" + debugHeaders(headers) + "\n[response]" + response);
    }
}
