package kr.co.windowfun.widget;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by isyuun on 8/1/2017.
 */

class ScrollAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scroll_child, parent, false);
            viewHolder = new ViewHolderItem(view);
        } else if (viewType == TYPE_HEADER || viewType == TYPE_FOOTER) {
            //inflate your layout and pass it to view holder
            //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_footer, parent, false);
            View view = new View(parent.getContext());
            DisplayMetrics metrics = parent.getContext().getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            view.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT));
            viewHolder = new ViewHolderHeaderOrFooter(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        // 1 for header and 1 for footer
        return 4 + 1 + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        else if (isPositionFooter(position))
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getItemCount() - 1;
    }


    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolderItem extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolderItem(View v) {
            super(v);
            mView = v;
        }
    }

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolderHeaderOrFooter extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolderHeaderOrFooter(View v) {
            super(v);
            mView = v;
        }
    }
}
