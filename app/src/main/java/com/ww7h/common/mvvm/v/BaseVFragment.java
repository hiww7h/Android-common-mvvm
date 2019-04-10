package com.ww7h.common.mvvm.v;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ww7h.common.mvvm.R;
import com.ww7h.common.test.m.TestModel;
import com.ww7h.ww.common.bases.fragment.BaseFragment;
import com.ww7h.ww.common.listeners.OnRecyclerItemClick;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.mvvm.v
 * 创建时间：  2019/4/5 18:57
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public abstract class BaseVFragment<T extends BaseFragment<T>, B extends ViewDataBinding> extends BaseFragment<T> {

    /**
     * 当前视图绑定的对象
     */
    protected B vdBinding;

    @Nullable
    @Override
    protected View getContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        vdBinding = DataBindingUtil.inflate(inflater, getResourceId() ,container , false);
        return vdBinding.getRoot();
    }

    @Override
    protected boolean getDesignPattern() {
        return true;
    }
}
