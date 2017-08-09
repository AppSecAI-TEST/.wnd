package kr.co.windowfun;

/**
 * Created by isyoon on 2017-07-19.
 */

public interface _JSON {
    //JSON:result
    interface result {
        String error_code = "error_code";
        String error_message = "error_message";
        String result_code = "result_code";
        String result_data = "result_data";
        String result_type = "result_type";
        String result_menu = "result_menu";
        String result_c1 = "result_c1";
        String result_c3 = "result_c3";
        String result_c4 = "result_c4";
        String result_c5 = "result_c5";
        String result_banner = "result_banner"; //result_c2
    }

    //JSON:result_data
    interface result_data {
        String token = "token";
        String userid = "userid";
    }

    //JSON:result_type
    interface result_type {
        //c_type:"A|B|C|D|E|F",
        String c_type = "c_type";
        //m_type: "A|B|C|...",
        String m_type = "m_type";
        //reg_dm:"20170719132751",
        String reg_dm = "reg_dm";
        //upd_dm:"20170719132751"
        String upd_dm = "upd_dm";
    }

    //JSON:result_menu
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
        //menu: "서브메뉴 = result_menu반복)"
        String menu = "menu";
    }

    //JSON:result_banner
    interface result_banner {
        //icon: "http://…",
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
        //text_effect: "3.1. 범례",
        String text_effect = "text_effect";
        //play_effect: "3.2. 범례",
        String play_effect = "play_effect";
    }

    //JSON:result_c
    interface result_c {
        //icon: "http://…",
        String icon = "icon";
        //type: "text|image|video|html",
        String type = "type";
        //title: "타이틀",
        String title = "title";
        //text: "텍스트",
        String text = "text";
        //text_effect: "3.1. 범례",
        String text_effect = "text_effect";
        String text_font = "text_font";
        String text_line = "text_line";
        String text_size = "text_size";
        String text_valign = "text_valign";
        String text_color = "text_color";
        String text_backcolor = "text_backcolor";
        //link: "n/a",
        String link = "link";
        //data: "",
        String data = "data";
        //time_start: "20170701",
        String time_start = "time_start";
        //time_end: "20170731",
        String time_end = "time_end";
        //play_effect: "3.2. 범례",
        String play_effect = "play_effect";
        //play_length: "30",
        String play_length = "play_length";
        //play_repeat: "1",
        String play_repeat = "play_repeat";
        //file_name: "http://windowfun.co.kr/upload/2017/07/19/13/26/02/잇섭의 KT 라인프렌즈 스마트폰 리뷰! [KT].mp4",
        String file_name = "file_name";
        //file_size: "18040399"
        String file_size = "file_size";
        //use_yn: "Y"
        String use_yn = "use_yn3";
    }
}
