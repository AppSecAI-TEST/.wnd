package kr.co.windowfun.widget;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;

import kr.co.windowfun._DEF;
import kr.co.windowfun._ENUM;
import kr.co.windowfun._JSON;
import kr.co.windowfun.api.JSONObject2;

/**
 * Created by isyuun on 2017-08-10.
 */

class BannerAdapter2 extends BannerAdapter implements _DEF, _ENUM, _JSON {
    Context context;
    JSONArray items;

    public BannerAdapter2(Context context, JSONArray items) {
        super();
        this.context = context;
        this.items = items;
    }

    @Override
    public __BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
        __BannerViewHolder viewHolder = new __BannerViewHolder(view);
        return viewHolder;
    }

    /**
     * plane|line|fade|typer|rainbow|scale|evaporate|fall
     */
    String _text_effect = text_effect.rainbow.toString(); //default
    String _text_font = "font1"; //default
    String _text_line = "false"; //default
    /**
     * xxxlarge|xxlarge|xlarge|large|normal|small|xsmall|xxsmall|xxxsmall
     */
    String _text_size = text_size.xxlarge.toString(); //default
    /**
     * top|center|bottom
     */
    String _text_valign = text_valign.center.toString(); //default
    int _text_color = Color.parseColor("#ffffffff");  //default:R.color.text_default_color;
    int _text_backcolor = Color.parseColor("#55ff00ff");  //default:R.color.test_default_backcolor);
    //play...
    String _play_effect = Techniques.FlipInX.toString();
    String _play_length = null;
    //file...
    String file_name = null;

    @Override
    public void onBindViewHolder(__BannerViewHolder holder, int position) {
        JSONObject2 item = null;
        String icon = null;
        String title = null;
        String text = null;
        //item
        try {
            item = new JSONObject2(items.get(position).toString());
            icon = !item.isNullString(result_menu.icon) ? item.getString(result_menu.icon) : "NG";
            title = !item.isNullString(result_menu.title) ? item.getString(result_menu.title) : "NG";
            text = !item.isNullString(result_menu.text) ? item.getString(result_menu.text) : "NG";
            //Log.wtf("[[" + this.getClass().getName() + "]]", "banner()" + "\t" + icon + "\t" + title + "\t" + text);
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        try {
            //text_...
            _text_effect = text_effect.fall.toString(); //default
            _text_font = "font1"; //default
            _text_line = "false"; //default
            _text_size = text_size.small.toString(); //default
            _text_valign = text_valign.center.toString(); //default
            _text_color = ContextCompat.getColor(context, android.R.color.primary_text_dark); //default
            _text_backcolor = ContextCompat.getColor(context, android.R.color.transparent); //default
            //text_...
            _text_effect = !item.isNullString(result_c.text_effect) ? item.getString(result_c.text_effect) : _text_effect;
            _text_font = !item.isNullString(result_c.text_font) ? item.getString(result_c.text_font) : _text_font;
            _text_line = !item.isNullString(result_c.text_line) ? item.getString(result_c.text_line) : _text_line;
            _text_size = !item.isNullString(result_c.text_size) ? item.getString(result_c.text_size) : _text_size;
            _text_valign = !item.isNullString(result_c.text_valign) ? item.getString(result_c.text_valign) : _text_valign;
            String __text_color = String.format("#%08x", /*0xFFFFFFFF & */_text_color);
            String __text_backcolor = String.format("#%08x", /*0xFFFFFFFF & */_text_backcolor);
            _text_color = Color.parseColor(!item.isNullString(result_c.text_color) ? item.getString(result_c.text_color) : __text_color);
            _text_backcolor = Color.parseColor(!item.isNullString(result_c.text_backcolor) ? item.getString(result_c.text_backcolor) : __text_backcolor);
            //String msg = "";
            //msg += "\n[text_effect]" + _text_effect;
            //msg += "\n[text_font]" + _text_font;
            //msg += "\n[text_line]" + _text_line;
            //msg += "\n[text_size]" + _text_size;
            //msg += "\n[text_valign]" + _text_valign;
            //msg += "\n[text_color]" + _text_color + ":" + __text_color;
            //msg += "\n[text_backcolor]" + _text_backcolor + ":" + __text_backcolor;
            //Log.w(__CLASSNAME__, getMethodName() + msg);
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        try {
            //play_...
            _play_effect = !item.isNullString(result_c.play_effect) ? item.getString(result_c.play_effect) : _play_effect;
            _play_length = !item.isNullString(result_c.play_length) ? item.getString(result_c.play_length) : _play_length;
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        try {
            //play_...
            _play_effect = !item.isNullString(result_c.play_effect) ? item.getString(result_c.play_effect) : _play_effect;
            _play_length = !item.isNullString(result_c.play_length) ? item.getString(result_c.play_length) : _play_length;
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
        //try {
        //    //file_name
        //    file_name = !item.isNullString(result_c.file_name) ? item.getString(result_c.file_name) : file_name;
        //    //uri
        //    Uri uri = Uri.parse(file_name);
        //    //Log.w(__CLASSNAME__, getMethodName() + ":" + index + "->" + _type + ":" + _text + ":" + file_name + ":" + uri);
        //} catch (Exception e) {
        //    Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
        //    //e.printStackTrace();
        //}
        //icon
        holder.icon.path(icon.toString());
        //title
        holder.title.setText(title);
        //holder.title._open(title, _text_effect);
        //holder.title.textFont(_text_font);
        //holder.title.textLine(Boolean.parseBoolean(_text_line));
        //holder.title.textSize(text_size.valueOf(_text_size));
        //holder.title.textVAlign(text_valign.valueOf(_text_valign));
        //holder.title.textColor(Color.parseColor(_text_color));
        //holder.title.textBackColor(Color.parseColor(_text_backcolor));
        //text
        holder.text.setText(text);
        //holder.title._open(text, _text_effect);
        //holder.text.textFont(_text_font);
        //holder.text.textLine(Boolean.parseBoolean(_text_line));
        //holder.text.textSize(text_size.valueOf(_text_size));
        //holder.text.textVAlign(text_valign.valueOf(_text_valign));
        //holder.text.textColor(Color.parseColor(_text_color));
        //holder.text.textBackColor(Color.parseColor(_text_backcolor));
        //banner
        if (holder.banner_text.getVisibility() == android.view.View.VISIBLE) {
            holder.banner_text.text(text, _text_effect);
            holder.banner_text.textFont(_text_font);
            holder.banner_text.textLine(Boolean.parseBoolean(_text_line));
            holder.banner_text.textSize(text_size.valueOf(_text_size));
            holder.banner_text.textVAlign(text_valign.valueOf(_text_valign));
            holder.banner_text.textColor(_text_color);
            holder.banner_text.textBackColor(_text_backcolor);
            holder.banner_text.play();
        }
    }

    @Override
    public int getItemCount() {
        return items.length();
    }
}
