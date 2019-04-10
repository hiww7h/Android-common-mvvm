package com.ww7h.common.mvvm.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.mvvm.vm
 * 创建时间：  2019/4/4 18:40
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public abstract class BaseVM extends AndroidViewModel {

    public BaseVM(@NonNull Application application) {
        super(application);
    }

}
