package com.ww7h.common.test;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.ww7h.common.test.m.TestModelImpl;

import java.util.List;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test
 * 创建时间：  2019/4/9 20:06
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class DataRepository {
    private static DataRepository sInstance;

    private MediatorLiveData<List<TestModelImpl>> mObservableProducts;

    private DataRepository(final LoadData loadData) {
        mObservableProducts = new MediatorLiveData<>();

        mObservableProducts.addSource(loadData.laodTestList(), new Observer<List<TestModelImpl>>() {
            @Override
            public void onChanged(@Nullable List<TestModelImpl> testModels) {
                if (loadData.getDataCreated().getValue() != null) {
                    mObservableProducts.postValue(testModels);
                }
            }
        });
    }

    public static DataRepository getInstance(final LoadData loadData) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(loadData);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<TestModelImpl>> getTests() {
        return mObservableProducts;
    }

}
