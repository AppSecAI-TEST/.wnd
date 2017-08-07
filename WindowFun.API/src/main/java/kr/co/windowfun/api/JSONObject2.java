package kr.co.windowfun.api;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Map;

/**
 * Created by isyuun on 2017-08-07.
 */

public class JSONObject2 extends org.json.JSONObject {
    public JSONObject2(String json) throws JSONException {
        super(json);
    }

    @Override
    public boolean isNull(String name) {
        return super.isNull(name);
    }

    public boolean isNullString(String name) {
        try {
            return isNull(name) || TextUtils.isEmpty(getString(name));
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
