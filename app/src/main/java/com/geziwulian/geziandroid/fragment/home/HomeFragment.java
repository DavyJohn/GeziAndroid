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
    @BindView(R.id.home_swipe)
    SwipeRefreshLayout mSwipe;
    private Unbinder unbinder;

    private HomeAdapter adapter;

    Thread bannerThread = new Thread(){
        @Override
        public void run() {
            Message bannerMessage = new Message();
            bannerMessage.what = 0;
            mHanner.sendMessage(bannerMessage);
        }
    };

    Handler mHanner = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    initBanner();
                    break;
            }
        }
    };
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
        Constant.IS_CANLOOP = 1;//恢复自动
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerDecoration(getActivity()));
        adapter = new HomeAdapter(getActivity());
        mRecycler.setAdapter(adapter);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showToast("刷新动作");
                initBanner();
            }
        });

        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingTolast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (lastItem == (totalItemCount - 1) && isSlidingTolast) {
                        showToast("到底部操作");
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isSlidingTolast = true;
                } else {
                    isSlidingTolast = false;
                }
            }

        });

    }

    //测试轮播
    private void initBanner() {
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.loadBanner()
                .subscribe(newSubscriber(new Action1<List<BannerData>>() {
                    @Override
                    public void call(List<BannerData> bannerDatas) {
                        if (bannerDatas != null) {
                            mSwipe.setRefreshing(false);
                            adapter.addBannerDat(bannerDatas);
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared ||!isVisible){
            Log.e("HomeFraghment执行lazyload","不执行");
        }else {
            Log.e("HomeFraghment执行lazyload","执行");
            mSwipe.setRefreshing(true);
            initBanner();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHanner.removeCallbacksAndMessages(null);
        //解绑
        unbinder.unbind();
        //回收滚动
        Constant.IS_CANLOOP = 0;
    }

}
