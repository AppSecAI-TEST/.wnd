package kr.co.windowfun.widget;

import android.os.Handler;

/**
 * Created by isyoon on 2017-07-19.
 */

public interface _TAG {
    Handler mHandler = new Handler();

    //TIMER
    int TIMER_OPEN_SHORT = 100;
    int TIMER_OPEN_LONG = 1000;
    int TIMER_JPG_SHORT = 3000;
    int TIMER_JPG_LONG = 5000;
    int TIMER_GIF_SHORT = 10000;
    int TIMER_GIF_LONG = 30000;

    //JSON
    String _error_code = "error_code";
    String _error_message = "error_message";
    String _result_data = "result_data";
    String _result_code = "result_code";

    //result_data
    interface result_data {
        String token = "token";
        String userid = "userid";
    }

    //result_c
    interface result_c {
        //division: "M",
        String division = "division";
        //title: "9번동영상",
        String title = "title";
        //start_day: "20170701",
        String start_day = "start_day";
        //end_day: "20170731",
        String end_day = "end_day";
        //contents_order: "30",
        String contents_order = "contents_order";
        //repeat2: "1",
        String repeat2 = "repeat2";
        //display: "내밀기",
        String display = "display";
        //use_yn3: "Y",
        String use_yn3 = "use_yn3";
        //filename: "http://windowfun.co.kr/upload/2017/07/19/13/26/02/잇섭의 KT 라인프렌즈 스마트폰 리뷰! [KT].mp4",
        String filename = "filename";
        //filesize: "18040399"
        String filesize = "filesize";
    }
}
