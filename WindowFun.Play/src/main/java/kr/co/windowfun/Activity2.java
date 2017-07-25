package kr.co.windowfun;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * 다운로드처리<br>
 * Created by isyoon on 2017-07-25.
 */

class Activity2 extends Activity {

    protected void down(final String src, final String dst) {
        Log.i(__CLASSNAME__, getMethodName() + ":" + src + ":" + dst);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(src, new FileAsyncHttpResponseHandler(new File(dst), false, false, true) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                //Log.w(__CLASSNAME__, getMethodName() + "\n" + src + "\n[statusCode]" + statusCode + "\n[headers]" + headers + "\n[throwable]" + throwable + "\n[file]" + file);
                Activity2.this.onFailure(src, dst, statusCode, headers, throwable, file);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                //Log.i(__CLASSNAME__, getMethodName() + "\n" + src + "\n[statusCode]" + statusCode + "\n[headers]" + headers + "\n[response]" + response);
                Activity2.this.onSuccess(src, dst, statusCode, headers, response);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                //Log.i(__CLASSNAME__, getMethodName() + "\n" + src + "\n[bytesWritten]" + bytesWritten + "\n[totalSize]" + totalSize);
                super.onProgress(bytesWritten, totalSize);
                Activity2.this.onProgress(src, dst, bytesWritten, totalSize);
            }
        });
    }

    protected void onFailure(String src, String dst, int statusCode, Header[] headers, Throwable throwable, File file) {
        Log.w(__CLASSNAME__, getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[statusCode]" + statusCode + "\n[headers]" + headers + "\n[throwable]" + throwable + "\n[file]" + file);
    }

    protected void onSuccess(String src, String dst, int statusCode, Header[] headers, File response) {
        Log.w(__CLASSNAME__, getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[statusCode]" + statusCode + "\n[headers]" + headers + "\n[response]" + response);
    }

    protected void onProgress(String src, String dst, long bytesWritten, long totalSize) {
        //Log.i(__CLASSNAME__, getMethodName() + "\n[src]" + src + "\n[dst]" + dst + "\n[bytesWritten]" + bytesWritten + "\n[totalSize]" + totalSize);
    }
}
