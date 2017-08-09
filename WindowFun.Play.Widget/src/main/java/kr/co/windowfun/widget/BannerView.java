package kr.co.windowfun.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import kr.co.windowfun._DEF;

/**
 * Created by isyuun on 2017-08-08.
 */

class BannerView extends RecyclerView implements _DEF {
    Context context;
    private RecyclerView.LayoutManager mLayoutManager;

    public BannerView(Context context) {
        super(context);
        this.context = context;
    }

    public BannerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public BannerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    /**
     *
     * @param SPEED Change this value (default=25f)
     */
    public void setLayoutManager(final float SPEED) {
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView,RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(context) {
                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        //return SPEED / displayMetrics.densityDpi;
                        return ((float) displayMetrics.densityDpi) / SPEED;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }
        };
        super.setLayoutManager(LayoutManager);
    }

    @Deprecated
    @Override
    public void setLayoutManager(LayoutManager layout) {
        //super.setLayoutManager(layout);
    }

    public void marquee() {
        final int speedScroll = 0;
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count == getAdapter().getItemCount()) count = 0;
                if (count < getAdapter().getItemCount()) {
                    smoothScrollToPosition(++count);
                    mHandler.postDelayed(this, speedScroll);
                }
            }
        };
        mHandler.postDelayed(runnable, speedScroll);
    }
}
