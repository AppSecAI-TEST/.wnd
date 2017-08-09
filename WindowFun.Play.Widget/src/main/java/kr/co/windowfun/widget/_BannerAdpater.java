package kr.co.windowfun.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import kr.co.windowfun._ENUM;
import kr.co.windowfun._JSON;
import kr.co.windowfun.api.JSONObject2;

/**
 * Created by isyuun on 2017-08-08.
 */

public class _BannerAdpater extends RecyclerView.Adapter<_BannerAdpater.ViewHolder> implements _ENUM, _JSON {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    protected String getMethodName() {
        ////Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int idx = 0;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement item = ste[i];
            ////Log.i(_CLASSNAME_, "" + item.getClassName());
            if (item.getClassName().contains(_CLASSNAME_)) {
                idx = i;
                //Log.v(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
    }

    Context context;
    JSONArray items;

    // Provide a suitable constructor (depends on the kind of dataset)
    public _BannerAdpater(Context context, JSONArray items) {
        this.context = context;
        this.items = items;
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        protected __ImageView icon;
        protected android.widget.TextView title;
        protected android.widget.TextView text;
        protected __TextView banner_title;
        protected __TextView banner_text;

        public ViewHolder(View v) {
            super(v);
            this.icon = (__ImageView) v.findViewById(R.id.icon);
            this.banner_text = (__TextView) v.findViewById(R.id.banner_text);
            this.title = (android.widget.TextView) v.findViewById(R.id.title);
            this.text = (android.widget.TextView) v.findViewById(R.id.text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * plane|line|fade|typer|rainbow|scale|evaporate|fall
     */
    String _text_effect = text_effect.rainbow.toString(); //default
    String _text_font = "font:TBD"; //default
    String _text_line = "false"; //default
    /**
     * xxxlarge|xxlarge|xlarge|large|normal|small|xsmall|xxsmall|xxxsmall
     */
    String _text_size = text_size.xxlarge.toString(); //default
    /**
     * top|center|bottom
     */
    String _text_valign = text_valign.center.toString(); //default
    int _text_color = Color.parseColor("#ff000000"); //default
    int _text_backcolor = Color.parseColor("#55ff00ff"); //default

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
            _text_font = "font:TBD"; //default
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
            String msg = "";
            msg += "\n[text_effect]" + _text_effect;
            msg += "\n[text_font]" + _text_font;
            msg += "\n[text_line]" + _text_line;
            msg += "\n[text_size]" + _text_size;
            msg += "\n[text_valign]" + _text_valign;
            msg += "\n[text_color]" + _text_color + ":" + __text_color;
            msg += "\n[text_backcolor]" + _text_backcolor + ":" + __text_backcolor;
            Log.i(__CLASSNAME__, getMethodName() + msg);
        } catch (Exception e) {
            Log.wtf(__CLASSNAME__, getMethodName() + Log.getStackTraceString(e));
            //e.printStackTrace();
        }
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
        if (holder.banner_text.getVisibility() == View.VISIBLE) {
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
