package kr.co.windowfun.app;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.api.JsonHttpResponseHandler2;

/**
 * Created by isyoon on 2017-07-17.
 */

public class Application2 extends Application {
    AsyncHttpClient client = new AsyncHttpClient();

    private JsonHttpResponseHandler2 response = new JsonHttpResponseHandler2() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Application2.this.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);
            Application2.this.onFailure(statusCode, headers, throwable, errorResponse);
        }
    };

    public void setResponse(JsonHttpResponseHandler2 response) {
        this.response = response;
    }

    public JsonHttpResponseHandler2 getResponse() {
        return this.response;
    }

    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
    }

    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
    }

    protected void send(String url) {
        Log.d(__CLASSNAME__, getMethodName() + ":" + url);
        client.post(url, response);
    }

    protected void send(String url, RequestParams params) {
        Log.d(__CLASSNAME__, getMethodName() + ":" + url + ":" + params);
        client.post(url, params, response);
    }
}
