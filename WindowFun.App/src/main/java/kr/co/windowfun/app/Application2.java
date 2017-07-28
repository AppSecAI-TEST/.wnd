package kr.co.windowfun.app;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;

/**
 * Created by isyoon on 2017-07-28.
 */

class Application2 extends Application {
    protected final StringBuilder debugHeaders(Header[] headers) {
        if (headers != null) {
            StringBuilder builder = new StringBuilder();
            int idx = 0;
            for (Header h : headers) {
                String _h = String.format(Locale.US, "%s : %s", h.getName(), h.getValue());
                builder.append(_h);
                if (idx < headers.length - 1) builder.append("\n");
                idx++;
            }
            return builder;
        }
        return null;
    }

    //protected final StringBuilder debugThrowable(Throwable t) {
    //    return new StringBuilder("" + t.getStackTrace());
    //}
    //
    //protected final StringBuilder debugResponse(String response) {
    //    return new StringBuilder(response);
    //}
    //
    //protected final StringBuilder debugStatusCode(int statusCode) {
    //    return new StringBuilder(""  + statusCode);
    //}
}
