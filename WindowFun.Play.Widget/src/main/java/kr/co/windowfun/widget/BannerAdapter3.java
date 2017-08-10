package kr.co.windowfun.widget;

import android.animation.Animator;
import android.content.Context;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONArray;

/**
 * Created by isyuun on 2017-08-10.
 */

class BannerAdapter3 extends BannerAdapter2 {
    public BannerAdapter3(Context context, JSONArray items) {
        super(context, items);
    }

    //private YoYo.YoYoString rope;

    @Override
    public void onBindViewHolder(__BannerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        //if (rope != null) rope.stop(true);
        //rope = YoYo.with(Techniques.valueOf(_play_effect))
        //        .duration(TIMER_MSEC_3T)
        //        .repeat(-1)
        //        .onEnd(new YoYo.AnimatorCallback() {
        //            @Override
        //            public void call(Animator animator) {
        //                //Log.wtf(__CLASSNAME__, "YoYo.onEnd()" + ":" + _type + ":" + _text_effect + ":" + _play_effect + ":" + uri + ":" + path);
        //            }
        //        })
        //        .playOn(holder.text);
    }

    @Override
    public void onViewAttachedToWindow(final __BannerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(__BannerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //if (rope != null) rope.stop(true);
    }
}
