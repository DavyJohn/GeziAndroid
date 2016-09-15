package com.geziwulian.geziandroid.fragment.home;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.DividerDecoration;
import com.geziwulian.netlibrary.ApiWrapper;
import com.geziwulian.netlibrary.model.dinner.BannerData;
import com.iflytek.msc.MSC;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/1.
 */
public class HomeFragment extends BaseFragment {

    private boolean isPrepared;//初始化标志位
    @BindView(R.id.home_recycler)
    RecyclerView mRecycler;
    private Unbinder unbinder;

    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);
        initView();
//        bannerThread.run();
        Log.e("HomeFragment执行","onViewCreated");
        isPrepared = true;
        lazyLoad();
    }

    private void initView() {
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity()));
        adapter = new HomeAdapter(getActivity());
        mRecycler.setAdapter(adapter);

    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared ||!isVisible){
            Log.e("HomeFraghment执行lazyload","不执行");
        }else {
            Log.e("HomeFraghment执行lazyload","执行");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //解绑
        unbinder.unbind();
        //回收滚动

    }

}
