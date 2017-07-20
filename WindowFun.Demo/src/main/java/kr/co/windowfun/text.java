package kr.co.windowfun;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.hanks.htextview.base.HTextView;
import com.hanks.htextview.fall.FallTextView;

import kr.co.windowfun.app.Activity2;

/**
 * Created by isyoon on 2017-07-11.
 */

public class text extends Activity2 {
    String[] sentences = {
            //"What is design?",
            //"Design is not just",
            //"what it looks like and feels like.",
            "Design is how it works. - Steve Jobs",
            //"Older people",
            //"sit down and ask,",
            //"'What is it?'",
            //"but the boy asks,",
            "'What can I do with it?'. - Steve Jobs",
            //"Swift",
            //"Objective-C",
            //"iPhone",
            //"iPad",
            //"Mac Mini",
            //"MacBook Pro",
            //"Mac Pro",
            //"爱老婆",
            //"老婆和女儿",
            "만세만세만세만세만세 윈도펀 만세만세만세만세만세",
            "세만세만세만세만세만 이종천 세만세만세만세만세만",
    };
    int index;

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Log.w(__CLASSNAME__, getMethodName() + ":" + v);
            if (v instanceof HTextView) {
                if (index > sentences.length - 1) {
                    index = 0;
                }
                ((HTextView) v).animateText(sentences[index]);
                index++;
            }
        }
    }

    private Handler mHandler;
    View[] vs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(kr.co.windowfun.R.layout.text);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.vs = new View[]{
                findViewById(R.id.text_line),
                findViewById(R.id.text_fade),
                findViewById(R.id.text_typer),
                findViewById(R.id.text_rainbow),
                findViewById(R.id.text_scale),
                findViewById(R.id.text_evaporate),
                findViewById(R.id.text_fall),
        };

        for (View v : text.this.vs) {
            v.setOnClickListener(new ClickListener());
        }

        mHandler = new Handler();
        new ChangeText().execute(this.vs);

        FloatingActionButton close = (FloatingActionButton) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.this.finish();
            }
        });
    }

    private boolean isPaused = false;

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

    class ChangeText extends AsyncTask<View, Void, Integer> {

        @Override
        protected Integer doInBackground(View... params) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (!text.this.isPaused) {
                mHandler.post(changeText);
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private Runnable changeText = new Runnable() {
        @Override
        public void run() {
            if (index > sentences.length - 1) {
                index = 0;
            }
            for (View v : text.this.vs) {
                ////Log.w(__CLASSNAME__, getMethodName() + ":" + v);
                if (v instanceof HTextView) {
                    ((HTextView) v).animateText(sentences[index]);
                }
                v.setSelected(true);
            }
            index++;
        }
    };

    class AnimateText extends AsyncTask<View, Void, Integer> {

        @Override
        protected Integer doInBackground(View... params) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (!text.this.isPaused) {
                mHandler.post(changeText);
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    int progress = 0;
    private Runnable animateText = new Runnable() {
        @Override
        public void run() {
            if (progress > 100f) {
                progress = 0;
            }
            for (View v : text.this.vs) {
                ////Log.w(__CLASSNAME__, getMethodName() + ":" + v);
                if (v instanceof HTextView) {
                    ((HTextView) v).setProgress(progress / 100f);
                }
                v.setSelected(true);
            }
            progress++;
        }
    };
}
