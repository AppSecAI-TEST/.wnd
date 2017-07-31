package kr.co.windowfun.widget;

import android.app.Activity;
import android.os.Environment;
import android.os.Handler;

/**
 * Created by isyoon on 2017-07-19.
 */

public interface _TAG {
    boolean DEBUG = BuildConfig.DEBUG;

    String root = Environment.getExternalStorageDirectory().getAbsolutePath();
    String root_mp4 = root + "/.mp4";

    Handler mHandler = new Handler();

    //PROGRESS
    float PROGRESS_PER = 100f;
    float PROGRESS_MAX = 1000f;

    //RESULT
    int _RESULT_OK = Activity.RESULT_OK;
    int _RESULT_CANCELED = Activity.RESULT_CANCELED;
    int _RESULT_LOGIN = Activity.RESULT_FIRST_USER;

    //TIMER
    int TIMER_RESET_SHORT = 3000;
    int TIMER_RESET_LONG = 5000;
    int TIMER_OPEN_SHORT = 100;
    int TIMER_OPEN_LONG = 1000;
    int TIMER_JPG_SHORT = 3000;
    int TIMER_JPG_LONG = 5000;
    int TIMER_GIF_SHORT = 10000;
    int TIMER_GIF_LONG = 30000;

    //JSON
    interface result {
        String error_code = "error_code";
        String error_message = "error_message";
        String result_code = "result_code";
        String result_data = "result_data";
        String result_type = "result_type";
        String result_menu = "result_menu";
        String result_banner = "result_banner"; //result_c2
        String result_c1 = "result_c1";
        String result_c3 = "result_c3";
        String result_c4 = "result_c4";
        String result_c5 = "result_c5";
    }

    //result_data
    interface result_data {
        String token = "token";
        String userid = "userid";
    }

    //result_type
    interface result_type {
        //type:"A|B|C|D|E|F",
        String type = "type";
        //reg_dm:"20170719132751",
        String reg_dm = "reg_dm";
        //upd_dm:"20170719132751"
        String upd_dm = "upd_dm";
    }

    //result_menu
    interface result_menu {
        //icon: "wf_menu_event|wf_menu_game|wf_menu_home|wf_menu_member|wf_menu_point|wf_menu_retrieval|wf_menu_search|wf_menu_setup|wf_menu_star|wf_menu_store|wf_menu_view|...",
        String icon = "icon";
        //type: "html|video|...",
        String type = "type";
        //title: "타이틀",
        String title = "title";
        //text: "텍스트",
        String text = "text";
        //link: "http://...",
        String link = "link";
        //data: "",
        String data = "data";
        //menu: "서브메뉴(result_menu반복)"
        String menu = "menu";
    }

    //"line|fade|typer|rainbow|scale|evaporate|fall"
    interface effect_text {
        String line = "line";
        String fade = "fade";
        String typer = "typer";
        String rainbow = "rainbow";
        String scale = "scale";
        String evaporate = "evaporate";
        String fall = "fall";
    }


    //"text|image|video|html"
    interface c_type {
        String text = "text";
        String image = "image";
        String video = "video";
        String html = "html";
    }

        //result_c
    interface result_c {
        //icon: "http://…",
        String icon = "icon";
        //type: "text|image|video|html",
        String type = "type";
        //title: "타이틀",
        String title = "title";
        //text: "텍스트",
        String text = "text";
        //link: "n/a",
        String link = "link";
        //data: "",
        String data = "data";
        //effect_text: "3.1. 범례",
        String effect_text = "effect_text";
        //effect_play: "3.2. 범례",
        String effect_play = "effect_play";
        //time_start: "20170701",
        String time_start = "time_start";
        //time_end: "20170731",
        String time_end = "time_end";
        //play_length: "30",
        String play_length = "play_length";
        //play_repeat: "1",
        String play_repeat = "play_repeat";
        //file_name: "http://windowfun.co.kr/upload/2017/07/19/13/26/02/잇섭의 KT 라인프렌즈 스마트폰 리뷰! [KT].mp4",
        String file_name = "filename";
        //filesize: "18040399"
        String filesize = "filesize";
        //use_yn: "Y"
        String use_yn = "use_yn3";
    }
}
