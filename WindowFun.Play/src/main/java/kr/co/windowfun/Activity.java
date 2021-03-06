package kr.co.windowfun;

import android.util.Log;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import kr.co.windowfun.app.__Activity;

/**
 * JSON데이터처리
 * Created by isyuun on 2017-07-25.
 */

class Activity extends __Activity implements _DEF, _JSON {
    protected _Application getApp() {
        return (_Application) getApplication();
    }

    String error_message;
    String error_code;
    String result_code;

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        try {
            Log.d(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[headers]\n" + debugHeaders(headers) + "\n[response]\n" + response.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onSuccess(statusCode, headers, response);
        error_message = getString(response, result.error_message);
        error_code = getString(response, result.error_code);
        result_code = getString(response, result.result_code);
        //login data
        getApp().token = getString(getJSONObject(response, result.result_data), result_data.token);
        getApp().userid = getString(getJSONObject(response, result.result_data), result_data.userid);
        //Toast.makeText(this, getApp().userid + ":" + getApp().token, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        try {
            Log.d(__CLASSNAME__, getMethodName() + "\n[status]\n" + statusCode + "\n[headers]\n" + debugHeaders(headers) + "\n[throwable]\n" + throwable + "\n[errorResponse]\n" + errorResponse.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }
}
