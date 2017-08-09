package kr.co.windowfun;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import kr.co.windowfun.api.JSONObject2;
import kr.co.windowfun.widget._BannerAdpater;
import kr.co.windowfun.widget.__BannerView;

/**
 * result_banner...
 * Created by isyuun on 2017-08-08.
 */

class __main4 extends __main3 {

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
        __BannerView banner  = (__BannerView) findViewById(id);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        banner.setHasFixedSize(true);

        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
        //    @Override
        //    public void smoothScrollToPosition(RecyclerView recyclerView,RecyclerView.State state, int position) {
        //        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(__main4.this) {
        //            private static final float SPEED = 4000f;// Change this value (default=25f)
        //            @Override
        //            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        //                return SPEED / displayMetrics.densityDpi;
        //            }
        //        };
        //        smoothScroller.setTargetPosition(position);
        //        startSmoothScroll(smoothScroller);
        //    }
        //};
        //mRecyclerView.setLayoutManager(mLayoutManager);
        banner.setLayoutManager(10.0f);

        // specify an adapter (see also next example)
        banner.setAdapter(new _BannerAdpater(getBaseContext(), getApp().result_banner));
        banner.marquee();
    }
}
