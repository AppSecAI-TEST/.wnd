package kr.co.windowfun;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.co.windowfun.api.JSONObject2;
import kr.co.windowfun.widget._BannerAdpater;
import kr.co.windowfun.widget.__BannerView;

/**
 * result_banner...
 * Created by isyuun on 2017-08-08.
 */

class __main4 extends __main3 {
    private __BannerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void _banner() {
        try {
            //Log.wtf(__CLASSNAME__, "_banner()" + ":" + getApp().result_banner.toString(2));
            for (int i = 0; i < getApp().result_banner.length(); i++) {
                JSONObject2 item = new JSONObject2(getApp().result_banner.get(i).toString());
                //Log.wtf(__CLASSNAME__, "_banner()" + ":" + item.toString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int banner = 0;
        switch (_c_type) {
            case A:
            case B:
            case C:
                banner = R.id.banner_long;
                break;
            case D:
            case E:
                banner = R.id.banner_short;
                break;
            case F:
                break;
        }
        if (banner < 1) return;
        findViewById(banner).setVisibility(View.VISIBLE);
        mRecyclerView = (__BannerView) findViewById(banner);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new _BannerAdpater(getBaseContext(), getApp().result_banner);
        mRecyclerView.setAdapter(mAdapter);
    }
}
