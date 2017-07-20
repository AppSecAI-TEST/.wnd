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
        String _division = "division";
        //title: "9번동영상",
        String _title = "title";
        //start_day: "20170701",
        String _start_day = "start_day";
        //end_day: "20170731",
        String _end_day = "end_day";
        //contents_order: "30",
        String _contents_order = "contents_order";
        //repeat2: "1",
        String _repeat2 = "repeat2";
        //display: "내밀기",
        String _display = "display";
        //use_yn3: "Y",
        String _use_yn3 = "use_yn3";
        //filename: "http://windowfun.co.kr/upload/2017/07/19/13/26/02/잇섭의 KT 라인프렌즈 스마트폰 리뷰! [KT].mp4",
        String _filename = "filename";
        //filesize: "18040399"
        String _filesize = "filesize";
    }
}
