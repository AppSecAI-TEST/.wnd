package kr.co.windowfun.app;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * 다운로드
 * Created by isyuun on 2017-07-26.
 */

class Application4 extends Application3 {

    protected void down(final String src, final String dst) {
        Log.i(__CLASSNAME__, getMethodName() + ":" + src + ":" + dst);
        AsyncHttpClient down = new AsyncHttpClient();
        down.get(src, new FileAsyncHttpResponseHandler(new File(dst), false, false, true) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                //Log.w(__CLASSNAME__, getMethodName() + "\n" + src + "\n[statusCode]" + statusCode + "\n[header]\n" + debugHeaders(headers) + "\n[throwable]\n" + throwable.getStackTrace() + "\n[file]" + file);
                Application4.this.onFailure(src, dst, statusCode, headers, throwable, file);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                //Log.i(__CLASSNAME__, getMethodName() + "\n" + src + "\n[statusCode]" + statusCode + "\n[header]\n" + debugHeaders(headers) + "\n[response]" + response);
                Application4.this.onSuccess(src, dst, statusCode, headers, response);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                //Log.i(__CLASSNAME__, getMethodName() + "\n" + src + "\n[bytesWritten]" + bytesWritten + "\n[totalSize]" + totalSize);
                super.onProgress(bytesWritten, totalSize);
                Application4.this.onProgress(src, dst, bytesWritten, totalSize);
            }
        });
    }

    protected void onFailure(String src, String dst, int statusCode, Header[] headers, Throwable throwable, File file) {
        Log.w(__CLASSNAME__, getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[statusCode]" + statusCode + "\n[header]\n" + debugHeaders(headers) + "\n[throwable]\n" + throwable.getStackTrace() + "\n[file]" + file);
    }

    protected void onSuccess(String src, String dst, int statusCode, Header[] headers, File response) {
        Log.w(__CLASSNAME__, getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[statusCode]" + statusCode + "\n[header]\n" + debugHeaders(headers) + "\n[response]" + response);
    }

    protected void onProgress(String src, String dst, long bytesWritten, long totalSize) {
        //Log.i(__CLASSNAME__, getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[bytesWritten]" + bytesWritten + "\n[totalSize]" + totalSize);
    }
}
