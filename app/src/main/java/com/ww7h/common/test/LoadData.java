package com.ww7h.common.test;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.ww7h.common.test.m.TestModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test
 * 创建时间：  2019/4/9 20:13
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class LoadData {

    private static LoadData sInstance;

    private final MutableLiveData<Boolean> mIsDataCreated = new MutableLiveData<>();

    private LoadData() {

    }

    public static LoadData getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (LoadData.class) {
                if (sInstance == null) {
                    sInstance = new LoadData();
                }
            }
        }
        return sInstance;
    }

    public  LiveData<List<TestModelImpl>> laodTestList() {
        return new MutableLiveData<List<TestModelImpl>>();
    }

    public LiveData<Boolean> getDataCreated() {
        return mIsDataCreated;
    }

}
