package kr.co.windowfun;

import kr.co.windowfun.widget._TAG;

/**
 * 수퍼액티비티
 * Created by isyoon on 2017-07-18.
 */

class _Activity extends Activity2 implements _TAG {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
