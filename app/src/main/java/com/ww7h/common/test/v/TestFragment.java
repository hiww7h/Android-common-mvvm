package com.ww7h.common.test.v;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.ww7h.common.mvvm.R;
import com.ww7h.common.mvvm.databinding.FragmentTestBinding;
import com.ww7h.common.mvvm.v.BaseVFragment;
import com.ww7h.common.test.m.TestModel;
import com.ww7h.common.test.m.TestModelImpl;
import com.ww7h.common.test.vm.TestFVM;
import com.ww7h.ww.common.listeners.OnRecyclerItemClick;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test.v
 * 创建时间：  2019/4/8 17:28
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class TestFragment extends BaseVFragment<TestFragment, FragmentTestBinding> {

    private TestAdapter testAdapter;

    @Override
    public int getResourceId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initAction() {

    }

    @Override
    public void initView() {

        TestFVM.Factory factory = new TestFVM.Factory(
                Objects.requireNonNull(getActivity()).getApplication());
        TestFVM testFVM = ViewModelProviders.of(fragment, factory).get(TestFVM.class);

        testAdapter = new TestAdapter();
        testAdapter.setItemClickListener(mItemClick);
        vdBinding.testRv.setAdapter(testAdapter);

        subscribeToModel(testFVM);
    }

    private void subscribeToModel(final TestFVM model) {

        // Observe product data
        model.getTests().observe(this, new Observer<List<TestModelImpl>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable List<TestModelImpl> testModels) {
                assert testModels != null;
                testAdapter.addDataList(testModels);
            }
        });

    }

    OnRecyclerItemClick<TestModel> mItemClick = new OnRecyclerItemClick<TestModel>() {
        @Override
        public void onItemClick(@Nullable TestModel testModel) {

        }
    };
}
