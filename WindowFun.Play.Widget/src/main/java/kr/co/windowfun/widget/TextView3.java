package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;

/**
 * text_font: "TBD",<br>
 * text_line: "true|false",<br>
 * text_size: "text|tiny|small|normal|large|xlarge",<br>
 * text_valign: "top|center|bottom",<br>
 * Created by isyuun on 8/2/2017.
 */

class TextView3 extends TextView2 {
    public TextView3(Context context) {
        super(context);
        init();
    }

    public TextView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public TextView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private String font = "";
    private boolean line = false;
    private text_valign valign = text_valign.center;
    private text_size size = text_size.xxxlarge;

    Runnable init = new Runnable() {
        @Override
        public void run() {
            textView = (android.widget.TextView) findViewById(R.id.text_view);
            textFont(TextView3.this.font);
            textLine(TextView3.this.line);
            textVAlign(TextView3.this.valign);
            textSize(TextView3.this.size);
        }
    };

    private void init() {
        if (isInEditMode()) return;
        mHandler.removeCallbacks(init);
        mHandler.postDelayed(init, 0);
    }

    @Override
    public void open(Uri uri) {
        super.open(uri);
        textFont(this.font);
        textLine(this.line);
        textVAlign(this.valign);
        textSize(this.size);
        //textView.setBackgroundColor(Color.parseColor("FF0F"));
    }

    /**
     * TODO:text_font: "TBD"
     */
    public void textFont(String font) {
        //Log.wtf(__CLASSNAME__, getMethodName() + font);
        this.font = font;
    }

    /**
     * text_line: "true|false"
     */
    public void textLine(boolean line) {
        try {
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + line);
            this.line = line;
            //line
            //textView.setMaxLines(1);
            textView.setSingleLine(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * text_size: "text|tiny|small|normal|large|xlarge"
     */
    public void textSize(text_size size) {
        try {
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + size + ":" + size.value());
            this.size = size;
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(size.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * text_valign: "top|center|bottom"
     */
    public void textVAlign(text_valign valign) {
        try {
            //Log.wtf(__CLASSNAME__, getMethodName() + ":" + valign + ":" + valign.value() + ":" + textView.getLayoutParams());
            this.valign = valign;
            //textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setGravity(valign.value());
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) textView.getLayoutParams();
            params.gravity = valign.value();
            textView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
