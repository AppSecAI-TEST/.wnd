package kr.co.windowfun.widget;

import android.support.v7.widget.RecyclerView;

/**
 * Created by isyuun on 2017-08-10.
 */ // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class __BannerViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    protected __ImageView icon;
    protected android.widget.TextView title;
    protected android.widget.TextView text;
    protected __TextView banner_title;
    protected __TextView banner_text;

    public __BannerViewHolder(android.view.View v) {
        super(v);
        this.icon = (__ImageView) v.findViewById(R.id.icon);
        this.banner_text = (__TextView) v.findViewById(R.id.banner_text);
        this.title = (android.widget.TextView) v.findViewById(R.id.title);
        this.text = (android.widget.TextView) v.findViewById(R.id.text);
    }
}
