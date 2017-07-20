package kr.co.windowfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by isyoon on 2017-07-12.
 */

public class home extends _Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.main_typea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, main.class));
            }
        });

        findViewById(R.id.main_typeb1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, main2.class));
            }
        });

        findViewById(R.id.main_typeb2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, main3.class));
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(home.this, main3.class));
            }
        }, TIMER_OPEN_SHORT);
    }

    @Override
    protected void getToken() {
        //test
        //clearToken();
        super.getToken();
    }
}
