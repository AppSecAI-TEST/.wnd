package kr.co.windowfun;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import kr.co.windowfun.widget.ImageView2;
import kr.co.windowfun.widget.VideoView2;

/**
 * Created by isyoon on 2017-07-12.
 */

public class main3 extends _main {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        videos.add(((VideoView2) findViewById(R.id.c1).findViewById(R.id.video)).path(mp4).mute(false));
        videos.add(((VideoView2)findViewById(R.id.c2).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((VideoView2)findViewById(R.id.c3).findViewById(R.id.video))/*.path(mp4)*/);
        videos.add(((VideoView2)findViewById(R.id.c4).findViewById(R.id.video))/*.path(mp4)*/);

        jpg.add("http://windowfun.co.kr/Manager/share/img/login_bg.jpg");
        jpg.add("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_Black_Womens_01.jpg?v=1458311938");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_Black_Womens_02.jpg?v=1458311938");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_Black_Womens_03.jpg?v=1458311938");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_White_Womens_04.jpg?v=1455732739");
        jpg.add("https://cdn.shopify.com/s/files/1/0065/0022/products/theCHIVE-Tank_White_Womens_05.jpg?v=1455732739");
        ////jpg.add("http://tv.giphy.com/?username=thechive");
        //jpg.add("https://thechive.files.wordpress.com/2017/06/whos-that-girl-from-stand-up-and-the-avn-awards-2.gif?w=600");
        //jpg.add("https://thechive.files.wordpress.com/2017/06/whos-that-girl-from-stand-up-and-the-avn-awards-24.gif?w=600");
        //jpg.add("https://thechive.files.wordpress.com/2017/06/whos-that-girl-from-stand-up-and-the-avn-awards-23.gif?w=600");

        /*images.add(((ImageView2) findViewById(R.id.plate0).findViewById(R.id.image)).path(jpg));*/
        images.add(((ImageView2) findViewById(R.id.c2).findViewById(R.id.image)).path(jpg));
        images.add(((ImageView2) findViewById(R.id.c3).findViewById(R.id.image)).path(jpg));
        images.add(((ImageView2) findViewById(R.id.c4).findViewById(R.id.image)).path(jpg));

        super.init();

        //for (VideoView2 v : videos) {
        //    v.stop();
        //}
    }
}
