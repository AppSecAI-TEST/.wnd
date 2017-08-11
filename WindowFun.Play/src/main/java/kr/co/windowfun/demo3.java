package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.__ImageView;
import kr.co.windowfun.widget.__VideoView;

/**
 * Created by isyuun on 2017-07-12.
 */

public class demo3 extends __demo {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        videos.add(((__VideoView) findViewById(R.id.c1).findViewById(R.id.video))/*.path(mp4).mute(false)*/);
        videos.add(((__VideoView) findViewById(R.id.c3).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView) findViewById(R.id.c4).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((__VideoView) findViewById(R.id.c5).findViewById(R.id.video))/*.path(mp4)*/);
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).path(mp4);
        ((__VideoView) findViewById(R.id.c1).findViewById(R.id.video)).mute(false);
        jpg.add("http://windowfun.co.kr/Manager/share/img/login_bg.jpg");
        jpg.add("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_Black_Womens_01.jpg?v=1458311938");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_Black_Womens_02.jpg?v=1458311938");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_Black_Womens_03.jpg?v=1458311938");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_White_Womens_04.jpg?v=1455732739");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_White_Womens_05.jpg?v=1455732739");
        ////jpg.add("http://tv.giphy.com/?username=thechive");
        jpg.add("http://s2.cdn.xiachufang.com/9748367af81b11e6bc9d0242ac110002_350w_262h.gif" + "?w=1920");
        jpg.add("https://i.imgur.com/1ALnB2s.gif" + "?w=1920");
        jpg.add("https://thechive.files.wordpress.com/2017/06/whos-that-girl-from-stand-up-and-the-avn-awards-2.gif" + "?w=300");
        jpg.add("https://thechive.files.wordpress.com/2017/06/whos-that-girl-from-stand-up-and-the-avn-awards-24.gif" + "?w=300");
        jpg.add("https://thechive.files.wordpress.com/2017/06/whos-that-girl-from-stand-up-and-the-avn-awards-23.gif" + "?w=300");
        /*images.add(((__ImageView) findViewById(R.id.plate0).findViewById(R.id.image)).path(jpg));*/
        images.add(((__ImageView) findViewById(R.id.c3).findViewById(R.id.image))/*.path(jpg)*/);
        images.add(((__ImageView) findViewById(R.id.c4).findViewById(R.id.image))/*.path(jpg)*/);
        images.add(((__ImageView) findViewById(R.id.c5).findViewById(R.id.image))/*.path(jpg)*/);
        ((__ImageView) findViewById(R.id.c3).findViewById(R.id.image)).path(jpg);
        ((__ImageView) findViewById(R.id.c4).findViewById(R.id.image)).path(jpg);
        ((__ImageView) findViewById(R.id.c5).findViewById(R.id.image)).path(jpg);
        super.init();
    }
}
