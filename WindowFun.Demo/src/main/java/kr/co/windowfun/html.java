package kr.co.windowfun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kr.co.windowfun.app.Activity2;

/**
 * Created by isyoon on 2017-07-05.
 */

public class html extends Activity2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(kr.co.windowfun.R.layout.html);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final WebView html = (WebView) findViewById(kr.co.windowfun.R.id.html);
        html.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
        html.getSettings().setJavaScriptEnabled(true);
        //webView.setBackgroundColor(0);  투명

        html.clearCache(true);
        WebSettings webSettings = html.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            String ext = url.substring(url.lastIndexOf("."));
            //if (ext.equalsIgnoreCase(".pdf")) /*url = "http://drive.google.com/viewerng/viewer?embedded=true&url=" + url;*///Log.i(__CLASSNAME__, getMethodName() + ":" + url);
            //Log.w(__CLASSNAME__, getMethodName() + ":" + ext + ":" + url);
            html.loadUrl(url);
        }

        FloatingActionButton close = (FloatingActionButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                html.this.finish();
            }
        });
    }
}
