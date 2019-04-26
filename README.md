# Android MVVM 框架搭建

## MVVM 概述

### Model–View–ViewModel (MVVM) 是一个软件架构设计模式

MVC：（View-Model-Controller）
将View、Model、Controller代码块进行划分，使得程序大部分分离，降低耦合。

MVP：（VIew-Model-Presenter）
![Image text](https://raw.githubusercontent.com/ww7hcom/StaticFiles/master/mvvmimagefile/MVP.png)
由于MVC中View和Model之间的依赖太强，导致Activity中的代码过于臃肿。为了他们可以绝对独立的存在，慢慢演化出了MVP。在MVP中View并不直接使用Model，它们之间的通信是通过 Presenter (MVC中的Controller)来进行的。


MVVM：（Model–View–ViewModel）
![Image text](https://raw.githubusercontent.com/ww7hcom/StaticFiles/master/mvvmimagefile/MVVM.png)
MVVM可以算是MVP的升级版，将Presenter改名为ViewModel。关键在于View和Model的双向绑定，当View有用户输入后，ViewModel通知Model更新数据，同理Model数据更新后，ViewModel通知View更新。

### MVVM 使用到的库

1、[Android官方架构组件Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
2、Android官方架构组件ViewModel
3、Android官方架构组件LiveData
4、Android官方架构组件Paging
5、Android官方架构组件Navigation
6、Android官方架构组件Data
7、[Android-common](https://github.com/ww7hcom/Android-common)
8、[Android-common-mvvm](https://github.com/ww7hcom/Android-common-mvvm)

## MVVM 实现

### 定义基类

#### 1、BaseVActivity

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

#### 2、BaseVFragment

    package com.ww7h.common.mvvm.v;

    import android.databinding.DataBindingUtil;
    import android.databinding.ViewDataBinding;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.ww7h.ww.common.bases.fragment.BaseFragment;

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

### 调用Demo

#### 1、定义layout文件，主要差别，root标签<layout></layout>，数据绑定使用<data></data>标签，<variable></variabl>标签用来绑定数据，@{}数据使用
    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>
            <variable
                name="mainDetailModel"
                type="com.ww7h.purchasing.main.model.MainDetailModel"/>

            <variable
                name="event"
                type="com.ww7h.purchasing.main.event.MainDetailEvent"/>

        </data>

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <Button
                android:id="@+id/jump_next_btn"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:onClick="@{event.jumpClick}"/>


            <RadioGroup
                android:id="@+id/b_nav_group"
                android:layout_alignParentBottom="true"
                android:gravity="center_vertical"
                android:layout_width="match_parent"
                android:layout_height="@dimen/dp_54"
                android:orientation="horizontal">
                <com.ww7h.ww.common.bases.view.radiobutton.RemindRadioButton
                    android:id="@+id/b_nav_1"
                    android:checked="true"
                    android:textSize="@dimen/sp_12"
                    android:text="@{mainDetailModel.navi1Name}"
                    app:remindMarginRight="@dimen/dp_30"
                    app:remindNumber="@{mainDetailModel.navi1Number}"
                    app:remindTextWidth="10dp"
                    app:remindTextHeight="10dp"
                    app:remindTextSize="8sp"
                    app:remindTextColor="@color/color_6f"
                    app:remindTextBgColor="@color/color_2f230"
                    android:textColor="@color/bottom_nav_text_color"
                    android:gravity="center"
                    android:drawableTop="@drawable/bottom_nav_check_icon1"
                    android:button="@null"
                    android:layout_weight="1"
                    android:layout_width="@dimen/dp_0"
                    android:layout_height="wrap_content" />
                <com.ww7h.ww.common.bases.view.radiobutton.RemindRadioButton
                    android:id="@+id/b_nav_2"
                    android:textSize="@dimen/sp_12"
                    android:text="@{mainDetailModel.navi2Name}"
                    app:remindNumber="@{mainDetailModel.navi2Number}"
                    app:remindTextWidth="5dp"
                    app:remindTextHeight="10dp"
                    app:remindTextSize="8sp"
                    app:remindTextColor="@color/color_6f"
                    app:remindTextBgColor="@color/color_2f230"
                    app:remindMarginRight="@dimen/dp_30"
                    android:textColor="@color/bottom_nav_text_color"
                    android:drawableTop="@drawable/bottom_nav_check_icon2"
                    android:gravity="center"
                    android:button="@null"
                    android:layout_weight="1"
                    android:layout_width="@dimen/dp_0"
                    android:layout_height="wrap_content" />
                <com.ww7h.ww.common.bases.view.radiobutton.RemindRadioButton
                    android:id="@+id/b_nav_3"
                    android:textSize="@dimen/sp_12"
                    android:text="@{mainDetailModel.navi3Name}"
                    app:remindNumber="@{mainDetailModel.navi3Number}"
                    app:remindMarginRight="@dimen/dp_30"
                    app:remindTextWidth="10dp"
                    app:remindTextHeight="10dp"
                    app:remindTextSize="8sp"
                    app:remindTextColor="@color/color_6f"
                    app:remindTextBgColor="@color/color_2f230"
                    android:textColor="@color/bottom_nav_text_color"
                    android:drawableTop="@drawable/bottom_nav_check_icon3"
                    android:gravity="center"
                    android:button="@null"
                    android:layout_weight="1"
                    android:layout_width="@dimen/dp_0"
                    android:layout_height="wrap_content" />
                <com.ww7h.ww.common.bases.view.radiobutton.RemindRadioButton
                    android:id="@+id/b_nav_4"
                    android:textSize="@dimen/sp_12"
                    android:text="@{mainDetailModel.navi4Name}"
                    app:remindNumber="@{mainDetailModel.navi4Number}"
                    app:remindMarginRight="@dimen/dp_30"
                    app:remindTextWidth="10dp"
                    app:remindTextHeight="10dp"
                    app:remindTextSize="8sp"
                    app:remindTextColor="@color/color_6f"
                    app:remindTextBgColor="@color/color_2f230"
                    android:textColor="@color/bottom_nav_text_color"
                    android:drawableTop="@drawable/bottom_nav_check_icon4"
                    android:gravity="center"
                    android:button="@null"
                    android:layout_weight="1"
                    android:layout_width="@dimen/dp_0"
                    android:layout_height="wrap_content" />
            </RadioGroup>
        </RelativeLayout>

    </layout>

#### 2、创建Activity，完成视图绑定和调用

    package com.ww7h.purchasing.main.view;

    import android.arch.lifecycle.LiveData;
    import android.arch.lifecycle.Observer;
    import android.arch.lifecycle.ViewModelProviders;
    import android.content.Intent;
    import android.support.annotation.Nullable;

    import com.ww7h.common.mvvm.v.BaseVActivity;
    import com.ww7h.purchasing.PTApplication;
    import com.ww7h.purchasing.R;
    import com.ww7h.purchasing.databinding.ActivityMainDetailBinding;
    import com.ww7h.purchasing.main.event.MainDetailEvent;
    import com.ww7h.purchasing.main.model.MainDetailModel;
    import com.ww7h.purchasing.main.viewmodel.MainDetailViewModel;
    import com.ww7h.ww.common.utils.DensityUtil;
    import com.ww7h.ww.common.utils.LogUtil;
    import com.ww7h.ww.common.utils.ScreenUtil;


    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   PurchasingTreasure
     * 包名：     com.ww7h.purchasing.main.view
     * 创建时间：  2019/4/23 20:53
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class MainDetailActivity extends BaseVActivity<MainDetailActivity, ActivityMainDetailBinding> {

        MainDetailViewModel mViewModel;

        @Override
        protected int getContentView() {
            return R.layout.activity_main_detail;
        }

        @Override
        protected void initAction() {
            vdBinding.setEvent(new MainDetailEvent(mViewModel));
        }

        @Override
        protected void initView() {

            MainDetailViewModel.Factory factory = new MainDetailViewModel.Factory(PTApplication.getInstance());
            mViewModel = ViewModelProviders.of(this, factory).get(MainDetailViewModel.class);
            subscribeUi(mViewModel.getMainDetailModel());

        }

        private MainDetailModel mainDetailModel;

        private void subscribeUi(LiveData<MainDetailModel> modelLiveData) {
            modelLiveData.observe(this, new Observer<MainDetailModel>() {
                @Override
                public void onChanged(@Nullable MainDetailModel mainModel) {
                    if (mainModel != null) {
                        float width = ScreenUtil.Companion.getScreenWidth(activity) / 8f;
                        mainDetailModel = mainModel;
                        vdBinding.setMainDetailModel(mainDetailModel);

                        vdBinding.bNav1.setRemindWidth(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav1.setRemindHeight(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav1.setRemindMarginRight(width - DensityUtil.INSTANCE.sp2px(activity, 15));

                        vdBinding.bNav2.setRemindWidth(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav2.setRemindHeight(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav2.setRemindMarginRight(width - DensityUtil.INSTANCE.sp2px(activity, 20));

                        vdBinding.bNav3.setRemindWidth(DensityUtil.INSTANCE.sp2px(activity, 15));
                        vdBinding.bNav3.setRemindHeight(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav3.setRemindMarginRight(width - DensityUtil.INSTANCE.sp2px(activity, 25));

                        vdBinding.bNav4.setRemindWidth(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav4.setRemindHeight(DensityUtil.INSTANCE.sp2px(activity, 10));
                        vdBinding.bNav4.setRemindMarginRight(width - DensityUtil.INSTANCE.sp2px(activity, 15));
                    }
                    vdBinding.executePendingBindings();
                }
            });
        }



        @Override
        protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            LogUtil.e(TAG, "-------");

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            LogUtil.e(TAG, "-------");

        }


    }

#### 3、创建Model，需要继承BaseObservable，get方法添加@Bindable注解，set方法添加notifyPropertyChanged(BR.navi1Number)，完成数据双向绑定。

    package com.ww7h.purchasing.main.model;

    import android.databinding.BaseObservable;
    import android.databinding.Bindable;

    import com.ww7h.common.mvvm.BR;


    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   PurchasingTreasure
     * 包名：     com.ww7h.purchasing.main.model
     * 创建时间：  2019/4/25 20:55
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class MainDetailModel extends BaseObservable {

        private String navi1Name;
        private String navi2Name;
        private String navi3Name;
        private String navi4Name;

        private int navi1Number;
        private int navi2Number;
        private int navi3Number;
        private int navi4Number;

        @Bindable
        public String getNavi1Name() {
            return navi1Name;
        }

        @Bindable
        public int getNavi1Number() {
            return navi1Number;
        }

        @Bindable
        public String getNavi2Name() {
            return navi2Name;
        }

        @Bindable
        public int getNavi2Number() {
            return navi2Number;
        }

        @Bindable
        public String getNavi3Name() {
            return navi3Name;
        }

        @Bindable
        public int getNavi3Number() {
            return navi3Number;
        }

        @Bindable
        public String getNavi4Name() {
            return navi4Name;
        }

        @Bindable
        public int getNavi4Number() {
            return navi4Number;
        }

        public void setNavi1Name(String navi1Name) {
            this.navi1Name = navi1Name;
        }

        public void setNavi2Name(String navi2Name) {
            this.navi2Name = navi2Name;
        }

        public void setNavi3Name(String navi3Name) {
            this.navi3Name = navi3Name;
        }

        public void setNavi4Name(String navi4Name) {
            this.navi4Name = navi4Name;
        }

        public void setNavi1Number(int navi1Number) {
            this.navi1Number = navi1Number;
            notifyPropertyChanged(BR.navi1Number);
        }

        public void setNavi2Number(int navi2Number) {
            this.navi2Number = navi2Number;
        }

        public void setNavi3Number(int navi3Number) {
            this.navi3Number = navi3Number;
        }

        public void setNavi4Number(int navi4Number) {
            this.navi4Number = navi4Number;
        }
    }

#### 4、创建ViewModel，创建、操作数据

    package com.ww7h.purchasing.main.viewmodel;

    import android.app.Application;
    import android.arch.lifecycle.LiveData;
    import android.arch.lifecycle.MutableLiveData;
    import android.arch.lifecycle.ViewModel;
    import android.arch.lifecycle.ViewModelProvider;
    import android.support.annotation.NonNull;

    import com.ww7h.common.mvvm.vm.BaseVM;
    import com.ww7h.purchasing.main.model.MainDetailModel;

    import org.jetbrains.annotations.NotNull;

    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   PurchasingTreasure
     * 包名：     com.ww7h.purchasing.main.viewmodel
     * 创建时间：  2019/4/26 10:25
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class MainDetailViewModel extends BaseVM {

        private MainDetailModel mainDetailModel;

        private MainDetailViewModel(@NonNull Application application) {
            super(application);
        }

        public LiveData<MainDetailModel> getMainDetailModel() {
            mainDetailModel = new MainDetailModel() {
                {
                    setNavi1Name("清单");
                    setNavi1Number(2);
                    setNavi1Name("日程");
                    setNavi2Number(88);
                    setNavi1Name("购买人");
                    setNavi3Number(111);
                    setNavi1Name("邮寄");
                    setNavi4Number(1);
                }
            };

            return new MutableLiveData<MainDetailModel>() {
                {
                    postValue(mainDetailModel);
                }
            };
        }

        public void updateNavi1Number(int number) {
            mainDetailModel.setNavi1Number(number);
        }

        public static class Factory extends ViewModelProvider.NewInstanceFactory {

            @NonNull
            private final Application mApplication;

            public Factory(@NonNull Application application) {
                mApplication = application;
            }

            @NotNull
            @Override
            public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
                return (T) new MainDetailViewModel(mApplication);
            }
        }

    }

#### 5、创建事件管理类，用来监听事件，并输出给ViewModel

    package com.ww7h.purchasing.main.event;

    import android.view.View;

    import com.ww7h.purchasing.main.viewmodel.MainDetailViewModel;
    import com.ww7h.ww.common.utils.ToastUtil;

    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   PurchasingTreasure
     * 包名：     com.ww7h.purchasing.main.view
     * 创建时间：  2019/4/26 11:40
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class MainDetailEvent {

        private MainDetailViewModel viewModel;

        public MainDetailEvent(MainDetailViewModel viewModel) {
            this.viewModel = viewModel;
        }

        public void jumpClick(View view) {

            ToastUtil.showShortToast("123");

            viewModel.updateNavi1Number(1);
        }

    }

#### 6、BaseVFragment的使用

    package com.ww7h.purchasing.main.view;

    import android.arch.lifecycle.LiveData;
    import android.arch.lifecycle.Observer;
    import android.arch.lifecycle.ViewModelProviders;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v7.widget.LinearLayoutManager;

    import com.ww7h.common.mvvm.v.BaseVFragment;
    import com.ww7h.purchasing.PTApplication;
    import com.ww7h.purchasing.R;
    import com.ww7h.purchasing.databinding.FragmentMainBinding;
    import com.ww7h.purchasing.main.model.MainModelInterface;
    import com.ww7h.purchasing.main.view.adapter.MainAdapter;
    import com.ww7h.purchasing.main.viewmodel.MainViewModel;
    import com.ww7h.ww.common.bases.view.recyclerview.decoration.SpaceItemDecoration;
    import com.ww7h.ww.common.listeners.OnRecyclerItemClick;
    import com.ww7h.ww.common.utils.DensityUtil;

    import java.util.List;
    import java.util.Objects;

    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   PurchasingTreasure
     * 包名：     com.ww7h.purchasing
     * 创建时间：  2019/4/17 14:51
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class MainFragment extends BaseVFragment<MainFragment, FragmentMainBinding> implements OnRecyclerItemClick<MainModelInterface> {

        private MainAdapter mainAdapter;

        @Override
        public int getResourceId() {
            return R.layout.fragment_main;
        }

        @Override
        public void initAction() {

        }

        @Override
        public void initView() {
            mainAdapter = new MainAdapter(getActivity());
            mainAdapter.setRecyclerItemClick(this);
            vdBinding.mainContentRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            vdBinding.setMainAdapter(mainAdapter);
            vdBinding.mainContentRv.addItemDecoration(new SpaceItemDecoration(DensityUtil.INSTANCE.dp2px(Objects.requireNonNull(getActivity()),1f),1));
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            MainViewModel.Factory factory = new MainViewModel.Factory(PTApplication.getInstance());
            final MainViewModel viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
            subscribeUi(viewModel.getMainModelList());
        }

        private void subscribeUi(LiveData<List<MainModelInterface>> liveData) {
            liveData.observe(this, new Observer<List<MainModelInterface>>() {
                @Override
                public void onChanged(@Nullable List<MainModelInterface> mainModels) {
                    if (mainModels != null) {
                        mainAdapter.replaceDataList(mainModels);
                    }
                    vdBinding.executePendingBindings();
                }
            });
        }

        @Override
        public void onItemClick(@org.jetbrains.annotations.Nullable MainModelInterface mainModelInterface) {
            assert mainModelInterface != null;
            Intent intent = new Intent(getActivity(), MainDetailActivity.class);
            startActivity(intent);
        }

    }

#### 7、适配器视图绑定

    package com.ww7h.purchasing.main.view.adapter;

    import android.content.Context;
    import android.databinding.DataBindingUtil;
    import android.support.annotation.NonNull;
    import android.view.LayoutInflater;
    import android.view.ViewGroup;

    import com.ww7h.purchasing.R;
    import com.ww7h.purchasing.databinding.ItemMainBinding;
    import com.ww7h.purchasing.main.model.MainModelInterface;
    import com.ww7h.ww.common.bases.view.recyclerview.adapters.BaseRecyclerViewAdapter;
    import com.ww7h.ww.common.bases.view.recyclerview.adapters.RecyclerViewHolder;
    import com.ww7h.ww.common.listeners.OnRecyclerItemClick;


    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   PurchasingTreasure
     * 包名：     com.ww7h.purchasing.main.view.adapter
     * 创建时间：  2019/4/17 16:51
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class MainAdapter extends BaseRecyclerViewAdapter<MainAdapter.MainViewHolder, MainModelInterface> {

        private OnRecyclerItemClick<MainModelInterface> recyclerItemClick;
        private Context mContext;

        public MainAdapter(Context context) {
            mContext = context;
        }

        @Override
        protected void onBindViewHolder(MainViewHolder holder, int position, int viewType) {
            holder.binding.setOnItemClickListener(recyclerItemClick);
            holder.binding.setItemModel(getItem(position));
        }

        @Override
        protected boolean areItemsTheSame(MainModelInterface oldM, MainModelInterface newM) {
            return oldM.getItemName().equals(newM.getItemName());
        }

        @Override
        protected boolean areContentsTheSame(MainModelInterface oldM, MainModelInterface newM) {
            return oldM.getItemName().equals(newM.getItemName())
                    && oldM.getItemContent().equals(newM.getItemContent())
                    && oldM.getItemIconPath().equals(newM.getItemIconPath());
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemMainBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_main,
                            viewGroup, false);

            return new MainViewHolder(binding);
        }

        public void setRecyclerItemClick(OnRecyclerItemClick<MainModelInterface> recyclerItemClick) {
            this.recyclerItemClick = recyclerItemClick;
        }

        class MainViewHolder extends RecyclerViewHolder {

            ItemMainBinding binding;

            MainViewHolder(ItemMainBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }

    }

#### 8、用到的视图

    <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android">

        <data>
            <variable
                name="mainAdapter"
                type="com.ww7h.purchasing.main.view.adapter.MainAdapter"/>
        </data>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <android.support.v7.widget.RecyclerView
                android:id="@+id/main_content_rv"
                android:adapter="@{mainAdapter}"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

        </LinearLayout>

    </layout>


        <?xml version="1.0" encoding="utf-8"?>
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

        <data>
            <variable
                name="onItemClickListener"
                type="com.ww7h.ww.common.listeners.OnRecyclerItemClick"/>
            <variable
                name="itemModel"
                type="com.ww7h.purchasing.main.model.MainModelInterface"/>
        </data>

        <RelativeLayout
            android:padding="@dimen/dp_10"
            android:background="@color/color_6f"
            android:onClick="@{() -> onItemClickListener.onItemClick(itemModel)}"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <ImageView
                android:id="@+id/item_icon_iv"
                android:layout_width="@dimen/dp_48"
                android:layout_height="@dimen/dp_48"
                android:scaleType="center"
                app:image="@{itemModel.itemIconPath}"
                android:layout_marginEnd="@dimen/dp_10"
                android:contentDescription="@{itemModel.itemName}" />
            <TextView
                android:id="@+id/item_name_tv"
                android:textSize="@dimen/sp_17"
                android:layout_toEndOf="@+id/item_icon_iv"
                android:text="@{itemModel.itemName}"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
            <TextView
                android:layout_toEndOf="@+id/item_icon_iv"
                android:layout_below="@+id/item_name_tv"
                android:text="@{itemModel.itemContent}"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
        </RelativeLayout>

    </layout>

#### 9、图片加载app:image="@{itemModel.itemIconPath}"，定义image

    package com.ww7h.common.mvvm.v.adapter;

    import android.databinding.BindingAdapter;
    import android.widget.ImageView;

    import com.bumptech.glide.Glide;

    /**
     * ================================================
     * 描述：
     * 来源：     Android Studio.
     * 项目名：   Android-common-mvvm
     * 包名：     com.ww7h.common.mvvm.v.adapter
     * 创建时间：  2019/4/26 16:18
     *
     * @author ww  Github地址：https://github.com/ww7hcom
     * ================================================
     */
    public class WidgetBindingAdapter {

        @BindingAdapter({"image"})
        public static void glideLoadImage(ImageView imageView, String url) {
            Glide.with(imageView.getContext()).load(url).into(imageView);
        }

    }

### 最终效果

![Image text](https://raw.githubusercontent.com/ww7hcom/StaticFiles/master/mvvmimagefile/1194h-xli8.gif)