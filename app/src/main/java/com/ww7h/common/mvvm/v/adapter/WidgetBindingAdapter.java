package com.ww7h.common.mvvm.v.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.mvvm.v.adapter
 * 创建时间：  2019/4/26 16:18
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class WidgetBindingAdapter {

    @BindingAdapter({"image"})
    public static void glideLoadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
