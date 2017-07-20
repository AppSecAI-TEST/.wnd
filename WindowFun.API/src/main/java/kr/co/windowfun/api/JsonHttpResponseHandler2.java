package kr.co.windowfun.api;

import android.util.Log;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by isyoon on 2017-07-18.
 */

public class JsonHttpResponseHandler2 extends JsonHttpResponseHandler {

    public JsonHttpResponseHandler2() {
        super();
    }

    /**
     * Returns when request succeeds
     *
     * @param statusCode http response status line
     * @param headers    response headers if any
     * @param response   parsed response if any
     */
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        //try {
        //    Log.d(__CLASSNAME__, "onSuccess()" + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[response]\n" + response.toString(2));
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        super.onSuccess(statusCode, headers, response);
    }

    ///**
    // * Returns when request succeeds
    // *
    // * @param statusCode http response status line
    // * @param headers    response headers if any
    // * @param response   parsed response if any
    // */
    //@Override
    //public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
    //    try {
    //        Log.d(__CLASSNAME__, "onSuccess()" + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[response]\n" + response.toString(2));
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    super.onSuccess(statusCode, headers, response);
    //}
    //
    //@Override
    //public void onSuccess(int statusCode, Header[] headers, String responseString) {
    //    try {
    //        Log.d(__CLASSNAME__, "onSuccess()" + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[responseString]\n" + responseString);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    super.onSuccess(statusCode, headers, responseString);
    //}

    /**
     * Returns when request failed
     *
     * @param statusCode    http response status line
     * @param headers       response headers if any
     * @param throwable     throwable describing the way request failed
     * @param errorResponse parsed response if any
     */
    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        //try {
        //    Log.d(__CLASSNAME__, "onFailure()" + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[throwable]\n" + throwable + "\n[errorResponse]\n" + errorResponse.toString(2));
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    ///**
    // * Returns when request failed
    // *
    // * @param statusCode    http response status line
    // * @param headers       response headers if any
    // * @param throwable     throwable describing the way request failed
    // * @param errorResponse parsed response if any
    // */
    //@Override
    //public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
    //    try {
    //        Log.d(__CLASSNAME__, "onFailure()" + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[throwable]\n" + throwable + "\n[errorResponse]\n" + errorResponse.toString(2));
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    super.onFailure(statusCode, headers, throwable, errorResponse);
    //}
    //
    //@Override
    //public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
    //    try {
    //        Log.d(__CLASSNAME__, "onFailure()" + "\n[status]\n" + statusCode + "\n[header]\n" + headers + "\n[throwable]\n" + throwable + "\n[responseString]\n" + responseString + ":" + "\n[throwable]\n" + throwable);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    super.onFailure(statusCode, headers, responseString, throwable);
    //}
}
