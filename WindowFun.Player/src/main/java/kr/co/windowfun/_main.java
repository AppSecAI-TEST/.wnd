package kr.co.windowfun;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.co.windowfun.widget.ImageView2;
import kr.co.windowfun.widget.TextView2;
import kr.co.windowfun.widget.VideoView2;

public class _main extends _Activity {
    final String root = Environment.getExternalStorageDirectory().getAbsolutePath();
    String root_mp4 = root + "/.mp4";

    protected ArrayList<TextView2> texts = new ArrayList<>();
    protected ArrayList<String> txt = new ArrayList<>();
    protected ArrayList<ImageView2> images = new ArrayList<>();
    protected ArrayList<String> jpg = new ArrayList<>();
    protected ArrayList<VideoView2> videos = new ArrayList<>();
    protected ArrayList<String> mp4 = new ArrayList<>();

    private void path() {
        if (!requestForPermission()) {
            return;
        }
        //Log.wtf(__CLASSNAME__, getMethodName() + "List: " + root_mp4);
        File directory = new File(root_mp4);
        File[] files = directory.listFiles();
        Log.e(__CLASSNAME__, getMethodName() + "List: " + files);
        if (files != null) {
            ////Log.d(__CLASSNAME__, "Size: "+ files.length);
            for (int i = 0; i < files.length; i++) {
                //Log.d(__CLASSNAME__, getMethodName() + "PathName:" + files[i].getPath());
                if (files[i].isFile()) mp4.add(files[i].getPath());
            }
        }
    }

    CircleMenu menu;
    private ArrayList<Map<Integer, Object[]>> menus = new ArrayList<>();

    private Map<Integer, Object[]> getMenu(int index) {
        Map<Integer, Object[]> menu = menus.get(index);
        return menu;
    }

    private String getMenuURL(int index) {
        String url = "";
        Map<Integer, Object[]> menu = getMenu(index);
        for (Map.Entry<Integer, Object[]> entry : menu.entrySet()) {
            url = (String) entry.getValue()[2];
            break;
        }
        return url;
    }

    private String getMenuType(int index) {
        String url = "";
        Map<Integer, Object[]> menu = getMenu(index);
        for (Map.Entry<Integer, Object[]> entry : menu.entrySet()) {
            url = (String) entry.getValue()[1];
            break;
        }
        return url;
    }

    private Intent getMenuIntent(int index) {
        Intent intent = null;
        Map<Integer, Object[]> menu = getMenu(index);
        for (Map.Entry<Integer, Object[]> entry : menu.entrySet()) {
            intent = (Intent) entry.getValue()[2];
            break;
        }
        return intent;
    }

    private int getMenuColor(int index) {
        String color = "#FFFFFF";
        Map<Integer, Object[]> menu = getMenu(index);
        for (Map.Entry<Integer, Object[]> entry : menu.entrySet()) {
            color = (String) entry.getValue()[0];
            break;
        }
        return Color.parseColor(color);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    private void pause() {
        for (final VideoView2 v : videos) {
            v.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    private void resume() {
        for (final VideoView2 v : videos) {
            v.resume();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        manus();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mHandler.postDelayed(init, TIMER_OPEN_SHORT);
    }

    private Runnable init = new Runnable() {
        @Override
        public void run() {
            path();
            init();
        }
    };

    protected void init() {
        videos();
        images();
        banner();
    }

    private void manus() {
        //isyoon:Hitomis/CircleMenu
        menu = (CircleMenu) findViewById(R.id.menu);
        ////Log.w(__CLASSNAME__, getMethodName() + "\t" + circleMenu);

        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_event, new Object[]{"#258CFF", "html", "http://windowfun.co.kr/type/typea/"});}});
        //menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_member, new Object[]{"#30A400", "html", "http://windowfun.co.kr/type/typeb/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_point, new Object[]{"#FF4B32", "html", "http://windowfun.co.kr/type/typec/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_retrieval, new Object[]{"#8A39FF", "html", "http://windowfun.co.kr/type/typed/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_store, new Object[]{"#FF6A00", "html", "http://windowfun.co.kr/type/typee/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_home, new Object[]{"#258CFF", "html", "http://drive.google.com/viewerng/viewer?embedded=true&url=http://windowfun.co.kr/type/windowfun.pdf"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_search, new Object[]{"#30A400", "video", root_mp4 + File.separator + "c" + File.separator + "c1.mp4"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_game, new Object[]{"#FF4B32", "html", "http://windowfun.co.kr/game/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_icon_member, new Object[]{"#8A39FF", "open", new Intent(_main.this, login.class)});}});

        menu.setMainMenu(ContextCompat.getColor(this, android.R.color.holo_red_dark),
                R.drawable.wf_icon_menu,
                R.drawable.ic_close_white_48dp)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        //Log.d(__CLASSNAME__, getMethodName() + ":" + index + ":" + getMenuType(index));
                        if ("html".equalsIgnoreCase(getMenuType(index))) {
                            new OpenHTML().execute(getMenuURL(index));
                        } else if ("video".equalsIgnoreCase(getMenuType(index))) {
                            new OpenVIDEO().execute(getMenuURL(index));
                        } else if ("open".equalsIgnoreCase(getMenuType(index))) {
                            new StartActivity().execute(getMenuIntent(index));
                        }
                    }

                })
                .setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                    @Override
                    public void onMenuOpened() {
                        ////Log.w(__CLASSNAME__, getMethodName());
                        mHandler.removeCallbacks(hideMenu);
                    }

                    @Override
                    public void onMenuClosed() {
                        ////Log.w(__CLASSNAME__, getMethodName());
                        mHandler.postDelayed(hideMenu, 5000);
                    }

                });

        for (int i = 0; i < menus.size(); i++) {
            ////Log.e(__CLASSNAME__, "" + menus.get(i));
            HashMap<Integer, Object[]> map = (HashMap<Integer, Object[]>) menus.get(i);
            for (Map.Entry<Integer, Object[]> entry : map.entrySet()) {
                menu.addSubMenu(Color.parseColor((String) entry.getValue()[0]), entry.getKey());
            }
        }

        mHandler.postDelayed(hideMenu, TIMER_OPEN_SHORT);
    }

    private void banner() {

        WebView webView = (WebView) findViewById(R.id.banner);
        webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.setBackgroundColor(0);  투명

        webView.clearCache(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://windowfun.co.kr/type/index2.html");

        //isyoon
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void images() {
        for (final ImageView2 i : images) {
            i.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View i, MotionEvent event) {
                    //////Log.e(__CLASSNAME__, getMethodName() + ":" + i + "," + event);
                    float w = i.getWidth();
                    //float h = i.getHeight();
                    float x = event.getX();
                    //float y = event.getY();
                    if (x < w / 2) {
                        ((ImageView2) i).prev();
                    } else if (x > w / 2) {
                        ((ImageView2) i).next();
                    }
                    for (VideoView2 video : videos) {
                        if (i == video) {
                            video.mute(false);
                        } else {
                            video.mute(true);
                        }
                    }
                    mHandler.post(showMenu);
                    return false;
                }
            });

            i.rand();
        }
    }

    private void videos() {
        for (final VideoView2 v : videos) {
            //리스너 등록
            v.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    v.next();
                }
            });
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //////Log.e(__CLASSNAME__, getMethodName() + ":" + v + "," + event);
                    float w = v.getWidth();
                    //float h = v.getHeight();
                    float x = event.getX();
                    //float y = event.getY();
                    if (x < w / 2) {
                        ((VideoView2) v).prev();
                    } else if (x > w / 2) {
                        ((VideoView2) v).next();
                    }
                    for (VideoView2 video : videos) {
                        if (v == video) {
                            video.mute(false);
                        } else {
                            video.mute(true);
                        }
                    }
                    mHandler.post(showMenu);
                    return false;
                }
            });

            v.rand();
        }
    }

    private Runnable showMenu = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(hideMenu);
            if (menu.getVisibility() != View.VISIBLE) {
                YoYo.with(Techniques.Landing)
                        .duration(1000)
                        .playOn(menu);
                menu.setVisibility(View.VISIBLE);
            }
            mHandler.postDelayed(hideMenu, 5000);
        }
    };

    private Runnable hideMenu = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(hideMenu);
            if (menu.getVisibility() != View.INVISIBLE) {
                YoYo.with(Techniques.TakingOff)
                        .duration(1000)
                        .onEnd(new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                findViewById(R.id.menu).setVisibility(View.INVISIBLE);
                            }
                        })
                        .playOn(menu);
            }
        }
    };

    private class OpenHTML extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Thread.sleep(TIMER_OPEN_LONG);
                openHTML(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void openHTML(String url) {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + url);
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(_main.this, html.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    private class OpenVIDEO extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Thread.sleep(TIMER_OPEN_LONG);
                openVIDEO(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void openVIDEO(String url) {
        //Log.e(__CLASSNAME__, getMethodName() + ":" + url);
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(_main.this, video.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    private class StartActivity extends AsyncTask<Intent, Void, Intent> {

        @Override
        protected Intent doInBackground(Intent... params) {
            try {
                Thread.sleep(TIMER_OPEN_LONG);
                startActivity(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
