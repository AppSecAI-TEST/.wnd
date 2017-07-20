package kr.co.windowfun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class menu extends Activity
{
	@SuppressLint({ "SdCardPath", "SetJavaScriptEnabled" })
	public void onCreate(Bundle savedInstanceState){                                
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		WebView webView = (WebView)findViewById(R.id.webView1);
		webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
		webView.getSettings().setJavaScriptEnabled(true);

		webView.clearCache(true);                    
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);                        
		webView.loadUrl("http://oir.co.kr/screen/index.html");
	}
}



