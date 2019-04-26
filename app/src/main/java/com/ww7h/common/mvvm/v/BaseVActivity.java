package com.ww7h.common.mvvm.v;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.ww7h.ww.common.bases.activity.BaseActivity;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.mvvm.v
 * 创建时间：  2019/4/5 18:54
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public abstract class BaseVActivity<T extends BaseActivity<T>, B extends ViewDataBinding> extends BaseActivity<T> {

    protected B vdBinding;

    @Override
    protected boolean getDesignPattern() {
        return true;
    }

    @Override
    protected void initContentView() {
        super.initContentView();
        vdBinding = DataBindingUtil.setContentView(this, getContentView());
    }
}
