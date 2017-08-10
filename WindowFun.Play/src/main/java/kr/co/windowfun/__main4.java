package kr.co.windowfun;

import android.view.View;

import kr.co.windowfun.api.JSONObject2;
import kr.co.windowfun.widget.__BannerAdapter;
import kr.co.windowfun.widget.__BannerView;

/**
 * result_banner...
 * Created by isyuun on 2017-08-08.
 */

class __main4 extends __main3 {

    __BannerView banner;

    @Override
    protected void init() {
        super.init();
        _banner();
    }

    private void _banner() {
        try {
            //Log.wtf(__CLASSNAME__, "_banner()" + ":" + getApp().result_banner.toString(2));
            for (int i = 0; i < getApp().result_banner.length(); i++) {
                JSONObject2 item = new JSONObject2(getApp().result_banner.get(i).toString());
                //Log.wtf(__CLASSNAME__, "_banner()" + ":" + item.toString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = 0;
        switch (_c_type) {
            case A:
            case B:
            case C:
                id = R.id.banner_long;
                break;
            case D:
            case E:
                id = R.id.banner_short;
                break;
            case F:
                break;
        }
        if (id < 1) return;
        findViewById(id).setVisibility(View.VISIBLE);

        //banner start
        banner = (__BannerView) findViewById(id);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        banner.setHasFixedSize(true);

        // use a linear layout manager
        banner.setLayoutManager(10.0f);

        // specify an adapter (see also next example)
        banner.setAdapter(new __BannerAdapter(getBaseContext(), getApp().result_banner));

        //marquee
        marquee();
    }

    private Runnable marquee = new Runnable() {
        @Override
        public void run() {
            banner.marquee();
        }
    };

    private void marquee() {
        mHandler.removeCallbacks(marquee);
        mHandler.postDelayed(marquee, TIMER_MSEC_5H);
    }

    @Override
    protected void onResume() {
        super.onResume();
        marquee();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (banner != null) banner.stop();
    }
}
