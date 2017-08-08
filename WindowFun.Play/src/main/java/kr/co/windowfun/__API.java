package kr.co.windowfun;

/**
 * Created by isyuun on 2017-08-04.
 */

public interface __API extends _DEF {
    String URI_HTTP = "http";
    String URI_HTTPS = "https";
    String URI_1COLON = ":";
    String URI_2SLASH = "//";
    String URI_SCHEME_HTTP = URI_HTTP + URI_1COLON + URI_2SLASH ;
    String URI_SCHEME_HTTPS = URI_HTTPS + URI_1COLON + URI_2SLASH ;
    String API_HOST = "windowfun.co.kr";
    String API_PORT = "80";
    String API_PATH = "/Manager/API/";
    String API_CONFIG = "W_cfg.html";
    String API_LOGIN = "W_login.html";
    String W_API_URL_PATH = URI_SCHEME_HTTP + API_HOST + API_PATH;
    /**
     * <a href="http://windowfun.co.kr/Manager/API/W_cfg.html">W_API_URL_CONFIG</a>
     */
    String W_API_URL_CONFIG = W_API_URL_PATH + API_CONFIG;
    /**
     * <a href="http://windowfun.co.kr/Manager/API/W_login.html">W_API_URL_LOGIN</a>
     */
    String W_API_URL_LOGIN = W_API_URL_PATH + API_LOGIN;
}
