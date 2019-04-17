package com.ww7h.common.test.v;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ww7h.common.mvvm.R;
import com.ww7h.common.mvvm.databinding.ItemTestBinding;
import com.ww7h.common.test.m.TestModel;
import com.ww7h.common.test.m.TestModelImpl;
import com.ww7h.ww.common.bases.view.recyclerview.adapters.BaseRecyclerViewAdapter;
import com.ww7h.ww.common.bases.view.recyclerview.adapters.RecyclerViewHolder;
import com.ww7h.ww.common.listeners.OnRecyclerItemClick;

import org.jetbrains.annotations.NotNull;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test.v
 * 创建时间：  2019/4/9 19:15
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class TestAdapter extends BaseRecyclerViewAdapter<TestAdapter.TestViewHolder, TestModelImpl> {

    private OnRecyclerItemClick<TestModel> itemClickListener;

    @Override
    protected boolean areContentsTheSame(TestModelImpl testModel, TestModelImpl t1) {
        return testModel.getId().equals(t1.getId());
    }

    @Override
    protected boolean areItemsTheSame(TestModelImpl testModel, TestModelImpl t1) {
        return testModel.getId().equals(t1.getId())
                || testModel.getImagePath().equals(t1.getImagePath())
                || testModel.getName().equals(t1.getName())
                || testModel.getContentDescription().equals(t1.getContentDescription())
                || testModel.getInformation().equals(t1.getInformation());

    }

    @Override
    protected void onBindViewHolder(@NotNull TestViewHolder testViewHolder, int i, int i1) {
        testViewHolder.binding.setItemModel(getItem(i));
        Glide.with(testViewHolder.binding.getRoot().getContext())
                .load(getItem(i).getImagePath()).into(testViewHolder.binding.testImageIv);
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTestBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_test,
                        viewGroup, false);
        binding.setItemClickListener(itemClickListener);

        return new TestViewHolder(binding);
    }

    public void setItemClickListener(OnRecyclerItemClick<TestModel> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class TestViewHolder extends RecyclerViewHolder{

        ItemTestBinding binding;

        TestViewHolder(ItemTestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
