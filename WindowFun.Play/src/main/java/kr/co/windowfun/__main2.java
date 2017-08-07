package kr.co.windowfun;

import android.view.View;

import kr.co.windowfun.widget._CContentListener;

/**
 * result_type
 * Created by isyuun on 2017-08-07.
 */

class __main2 extends __main implements _CContentListener {
    @Override
    protected void init() {
        super.init();
        start();
    }

    @Override
    protected void _contents() {
        super._contents();
        c_type();
    }

    @Override
    protected void _banner() {
        super._banner();
    }

    _ENUM.c_type c_type = _ENUM.c_type.C;

    private void c_type() {
        try {
            c_type = _ENUM.c_type.valueOf(getApp().result_type.getString(result_type.c_type));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //content
        switch (c_type) {
            case A:
                findViewById(R.id.banner_short).setVisibility(View.GONE);
                findViewById(R.id.banner_long).setVisibility(View.VISIBLE);
                findViewById(R.id.cs).setVisibility(View.GONE);
                findViewById(R.id.c3).setVisibility(View.GONE);
                findViewById(R.id.c4).setVisibility(View.GONE);
                findViewById(R.id.c5).setVisibility(View.GONE);
                break;
            case B:
                findViewById(R.id.banner_short).setVisibility(View.GONE);
                findViewById(R.id.banner_long).setVisibility(View.VISIBLE);
                findViewById(R.id.c5).setVisibility(View.GONE);
                break;
            case C:
                findViewById(R.id.banner_short).setVisibility(View.GONE);
                findViewById(R.id.banner_long).setVisibility(View.VISIBLE);
                break;
            case D:
                findViewById(R.id.banner_short).setVisibility(View.VISIBLE);
                findViewById(R.id.banner_long).setVisibility(View.GONE);
                findViewById(R.id.c5).setVisibility(View.GONE);
                break;
            case E:
                findViewById(R.id.banner_short).setVisibility(View.VISIBLE);
                findViewById(R.id.banner_long).setVisibility(View.GONE);
                break;
            case F:
                findViewById(R.id.banner_short).setVisibility(View.GONE);
                findViewById(R.id.banner_long).setVisibility(View.GONE);
                findViewById(R.id.cs).setVisibility(View.GONE);
                findViewById(R.id.c3).setVisibility(View.GONE);
                findViewById(R.id.c4).setVisibility(View.GONE);
                findViewById(R.id.c5).setVisibility(View.GONE);
                break;
        }
    }

}
