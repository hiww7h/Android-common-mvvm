package com.ww7h.common.test.v;

import android.databinding.DataBindingUtil;

import com.ww7h.common.mvvm.R;
import com.ww7h.common.mvvm.databinding.ActivityTestBinding;
import com.ww7h.common.mvvm.v.BaseVActivity;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test
 * 创建时间：  2019/4/8 17:27
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class TestActivity extends BaseVActivity<TestActivity, ActivityTestBinding> {

    @Override
    protected int getContentView() {
        return R.layout.activity_test;
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initView() {
        vdBinding.setTitle("123");
        getSupportFragmentManager().beginTransaction().add(R.id.test_fl, new TestFragment()).commit();
    }

}
