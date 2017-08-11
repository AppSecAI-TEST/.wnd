package kr.co.windowfun;

import android.view.View;

/**
 * result_type._c_type
 * Created by isyuun on 2017-08-07.
 */

class __main4 extends __main3 implements _ENUM {
    c_type _c_type = c_type.C;

    @Override
    protected void _contents() {
        super._contents();
        _c_type();
    }

    private void _c_type() {
        try {
            _c_type = _c_type.valueOf(getApp().result_type.getString(result_type.c_type));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //content
        switch (_c_type) {
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
