package kr.co.windowfun.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/**
 * _text_font: "TBD",<br>
 * _text_line: "true|false",<br>
 * _text_size: "_text|tiny|small|normal|large|xlarge",<br>
 * _text_valign: "top|center|bottom",<br>
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

    private String font = "";
    private boolean line = false;
    private text_valign valign = text_valign.top;
    private text_size size = text_size.xxxlarge;
    private int color = Color.parseColor("#ffffffff");  //default:R.color.text_default_color;
    private int backcolor = Color.parseColor("#55ff00ff");  //default:R.color.test_default_backcolor);

    @Override
    public void text(String text, String type) {
        super.text(text, type);
        try {
            textFont(this.font);
            textLine(this.line);
            textVAlign(this.valign);
            textSize(this.size);
            textColor(TextView3.this.color);
            textBackColor(this.backcolor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setText(String text) {
        super.setText(text);
    }

    /**
     * TODO:_text_font
     */
    public void textFont(String font) {
        //Log.wtf(__CLASSNAME__, getMethodName() + font);
        this.font = font;
    }

    /**
     * _text_line: "true|false"
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
     * _text_size: "_text|tiny|small|normal|large|xlarge"
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
     * _text_valign: "top|center|bottom"
     */
    public void textVAlign(text_valign valign) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + valign + ":" + valign.value() + ":" + textView.getLayoutParams());
        this.valign = valign;
        //_text
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

    public void textColor(int color) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + color);
        this.color = color;
        try {
            textView.setTextColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void textBackColor(int backcolor) {
        //Log.wtf(__CLASSNAME__, getMethodName() + ":" + backcolor);
        this.backcolor = backcolor;
        try {
            textView.setBackgroundColor(backcolor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
