package kr.co.windowfun.widget;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

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
    }

    public TextView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.font = "";
        this.line = false;
        this.valign = text_valign.top;
        this.size = text_size.xxxlarge;
        this.backgound = ContextCompat.getColor(getContext(), R.color.text_default_background);
        init();
    }

    private String font = "";
    private boolean line = false;
    private text_valign valign = text_valign.center;
    private text_size size = text_size.xxxlarge;
    private int backgound = 0;

    Runnable init = new Runnable() {
        @Override
        public void run() {
            textView = (android.widget.TextView) findViewById(R.id.text_view);
            textFont(TextView3.this.font);
            textLine(TextView3.this.line);
            textVAlign(TextView3.this.valign);
            textSize(TextView3.this.size);
            textBackgound(TextView3.this.backgound);
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
        textBackgound(this.backgound);
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
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + line);
        this.line = line;
        try {
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
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + size + ":" + size.value());
        this.size = size;
        try {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(size.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * text_valign: "top|center|bottom"
     */
    public void textVAlign(text_valign valign) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + valign + ":" + valign.value() + ":" + textView.getLayoutParams());
        this.valign = valign;
        //text
        try {
            textView.setGravity(valign.value());
            //FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) textView.getLayoutParams();
            //params.gravity = valign.value();
            //textView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //scroll
        try {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                params.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                params.removeRule(RelativeLayout.CENTER_VERTICAL);
                params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            } else {
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                params.addRule(RelativeLayout.CENTER_VERTICAL, 0);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
            }
            switch (this.valign) {
                case top:
                    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    break;
                case center:
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    break;
                case bottom:
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    break;
            }
            setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void textBackgound(int backgound) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + backgound);
        this.backgound = backgound;
        try {
            textView.setBackgroundColor(backgound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
