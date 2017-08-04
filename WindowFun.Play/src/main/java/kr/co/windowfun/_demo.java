package kr.co.windowfun;

import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-08-04.
 */

class _demo extends __main {
    protected void rand() {  //test
        for (final __ImageView i : images) {
            i.rand();
        }
        for (final __VideoView v : videos) {
            v.rand();
        }
    }
}
