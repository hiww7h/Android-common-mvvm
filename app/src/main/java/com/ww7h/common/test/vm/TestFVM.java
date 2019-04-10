package com.ww7h.common.test.vm;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ww7h.common.mvvm.vm.BaseVM;
import com.ww7h.common.test.BasicApp;
import com.ww7h.common.test.DataRepository;
import com.ww7h.common.test.m.TestModelImpl;

import java.util.List;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test.vm
 * 创建时间：  2019/4/8 20:34
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class TestFVM extends BaseVM {

    private final LiveData<List<TestModelImpl>> mObservableTests;

    public LiveData<List<TestModelImpl>> getTests() {
        return mObservableTests;
    }

    public TestFVM(@NonNull Application application, DataRepository repository) {
        super(application);

        mObservableTests = repository.getTests();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private DataRepository dataRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            dataRepository = ((BasicApp)application).getRepository();

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TestFVM(mApplication, dataRepository);
        }
    }

}
