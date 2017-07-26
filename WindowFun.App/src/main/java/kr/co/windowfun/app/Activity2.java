package kr.co.windowfun.app;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.api.JsonHttpResponseHandler2;

/**
 * JSON전송
 * Created by isyoon on 2017-07-17.
 */

class Activity2 extends Activity {
    AsyncHttpClient client = new AsyncHttpClient();

    private JsonHttpResponseHandler2 response = new JsonHttpResponseHandler2() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            Log.wtf(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[response]\n" + response);
            super.onSuccess(statusCode, headers, response);
            Activity2.this.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            Log.wtf(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[headers]\n" + throwable + "\n[errorResponse]\n" + errorResponse);
            super.onFailure(statusCode, headers, throwable, errorResponse);
            Activity2.this.onFailure(statusCode, headers, throwable, errorResponse);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
            Log.wtf(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[response]\n" + response);
            super.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            Log.wtf(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[headers]\n" + throwable + "\n[errorResponse]\n" + errorResponse);
            super.onFailure(statusCode, headers, throwable, errorResponse);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            Log.wtf(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[responseString]\n" + responseString + "\n[throwable]\n" + throwable);
            super.onFailure(statusCode, headers, responseString, throwable);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            Log.wtf(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[responseString]\n" + responseString);
            super.onSuccess(statusCode, headers, responseString);
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

    protected String getString(JSONObject response, String name) {
        try {
            return response.getString(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected JSONObject getJSONObject(JSONObject response, String name) {
        try {
            return response.getJSONObject(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected JSONArray getJSONArray(JSONObject response, String name) {
        try {
            return response.getJSONArray(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String toString(JSONObject response, int indentSpaces) {
        try {
            return response.toString(indentSpaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String toString(JSONArray response, int indentSpaces) {
        try {
            return response.toString(indentSpaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
