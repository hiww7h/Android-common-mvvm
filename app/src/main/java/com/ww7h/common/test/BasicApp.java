package com.ww7h.common.test;

import android.app.Application;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test
 * 创建时间：  2019/4/9 21:05
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class BasicApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LoadData getData() {
        return LoadData.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getData());
    }

}
