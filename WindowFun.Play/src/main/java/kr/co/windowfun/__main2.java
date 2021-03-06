package kr.co.windowfun;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hitomi.cmlibrary.CircleMenu2;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * result_menu
 * Created by isyuun on 2017-08-11.
 */

class __main2 extends __main {
    CircleMenu2 menu;

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
    protected void init() {
        super.init();
        _menus();
    }

    private void _menus() {
        //isyuun:Hitomis/CircleMenu
        menu = (CircleMenu2) findViewById(R.id.menu);
        ////Log.i(__CLASSNAME__, getMethodName() + "\t" + circleMenu);

        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_event, new Object[]{"#258CFF", "html", "http://windowfun.co.kr/type/typea/"});}});
        //menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_member, new Object[]{"#30A400", "html", "http://windowfun.co.kr/type/typeb/"});}});
        //menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_point, new Object[]{"#FF4B32", "html", "http://windowfun.co.kr/type/typec/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_retrieval, new Object[]{"#8A39FF", "html", "http://windowfun.co.kr/type/typed/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_store, new Object[]{"#FF6A00", "html", "http://windowfun.co.kr/type/typee/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_home, new Object[]{"#258CFF", "html", "http://drive.google.com/viewerng/viewer?embedded=true&url=http://windowfun.co.kr/type/windowfun.pdf"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_search, new Object[]{"#30A400", "video", root_mp4 + File.separator + "c" + File.separator + "c1.mp4"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_game, new Object[]{"#FF4B32", "html", "http://windowfun.co.kr/game/"});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_point, new Object[]{"#FF4B32", "_open", new Intent(getBaseContext(), text.class)});}});
        menus.add(new HashMap<Integer, Object[]>() {{put(R.drawable.wf_menu_member, new Object[]{"#8A39FF", "_open", new Intent(getBaseContext(), login.class)});}});

        menu.setMainMenu(ContextCompat.getColor(this, android.R.color.holo_red_dark),
                R.drawable.wf_icon_menu,
                R.drawable.wf_icon_close)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        //Log.d(__CLASSNAME__, getMethodName() + ":" + index + "->" + getMenuType(index));
                        if ("html".equalsIgnoreCase(getMenuType(index))) {
                            new OpenHTML().execute(getMenuURL(index));
                        } else if ("video".equalsIgnoreCase(getMenuType(index))) {
                            new OpenVIDEO().execute(getMenuURL(index));
                        } else if ("_open".equalsIgnoreCase(getMenuType(index))) {
                            new StartActivity().execute(getMenuIntent(index));
                        }
                    }

                })
                .setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                    @Override
                    public void onMenuOpened() {
                        ////Log.i(__CLASSNAME__, getMethodName());
                        mHandler.removeCallbacks(hideMenu);
                    }

                    @Override
                    public void onMenuClosed() {
                        ////Log.i(__CLASSNAME__, getMethodName());
                        mHandler.postDelayed(hideMenu, 5000);
                    }

                });

        for (int i = 0; i < menus.size(); i++) {
            ////Log.w(__CLASSNAME__, "" + _menus.get(i));
            HashMap<Integer, Object[]> map = (HashMap<Integer, Object[]>) menus.get(i);
            for (Map.Entry<Integer, Object[]> entry : map.entrySet()) {
                menu.addSubMenu(Color.parseColor((String) entry.getValue()[0]), entry.getKey());
            }
        }

        menu.setOnTouchListener(this);

        mHandler.postDelayed(hideMenu, TIMER_MSEC_1H);
    }


    private Runnable showMenu = new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacks(hideMenu);
            if (menu.getVisibility() != View.VISIBLE) {
                menu.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Landing)
                        .duration(1000)
                        .playOn(menu);
            }
            mHandler.postDelayed(hideMenu, 5000);
        }
    };

    protected void setShowMenu() {
        mHandler.removeCallbacks(showMenu);
        mHandler.postDelayed(showMenu, 0);
    }

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
                Thread.sleep(TIMER_MSEC_1T);
                openHTML(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void openHTML(String url) {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + url);
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(getBaseContext(), html.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    private class OpenVIDEO extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Thread.sleep(TIMER_MSEC_1T);
                openVIDEO(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void openVIDEO(String url) {
        //Log.w(__CLASSNAME__, getMethodName() + ":" + url);
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(getBaseContext(), video.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    private class StartActivity extends AsyncTask<Intent, Void, Intent> {

        @Override
        protected Intent doInBackground(Intent... params) {
            try {
                Thread.sleep(TIMER_MSEC_1T);
                startActivity(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
