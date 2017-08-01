package kr.co.windowfun.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by isyuun on 8/1/2017.
 */

public class MarqueeView extends RecyclerView {
    Context context;

    public MarqueeView(Context context) {
        super(context);
        marqueList = this;
        this.context = context;
        init(context);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        marqueList = this;
        this.context = context;
        init(context);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        marqueList = this;
        this.context = context;
        init(context);
    }

    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            final int duration = 10;
            final int pixelsToMove = 10;
            marqueList.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    private RecyclerView marqueList;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    //private boolean loading = true;
    private boolean foundTotalPixel = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int totalMovedPixel;
    private int totalPixel;

    private void init(Context context) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        marqueList.setLayoutManager(layoutManager);
        marqueList.setAdapter(new ScrollAdapter());

        marqueList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalMovedPixel = totalMovedPixel + dx;
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                if (foundTotalPixel) {
                    if (totalItemCount > 2) {
                        View headerView = layoutManager.getChildAt(0);
                        View itemView = layoutManager.getChildAt(1);

                        if (itemView != null && headerView != null) {
                        /*total visible scrolling part is total pixel's of total item's count and header view*/
                            totalPixel = /*-c.getTop() +*/ ((totalItemCount - 2) * itemView.getWidth()) + (1 * headerView.getWidth());
                            Log.v("...", "Total pixel x!" + totalPixel);
                            foundTotalPixel = false;
                        }
                    }
                }

                //if (loading) {
                //if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                if (!foundTotalPixel && totalMovedPixel >= totalPixel) {
                    // loading = false;
                    Log.v("...", "Last Item Wow !");
                    Log.v("...", "totalMovedPixel !" + totalMovedPixel);

                    // use this to turn auto-scrolling off:
                    //mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    marqueList.setAdapter(null);
                    marqueList.setAdapter(new ScrollAdapter());
                    pastVisiblesItems = visibleItemCount = totalItemCount = 0;
                    totalMovedPixel = 0;

                }
            }
            // }
        });

        //// use this to turn auto-scrolling on:
        //mHandler.post(SCROLLING_RUNNABLE);
    }
}
