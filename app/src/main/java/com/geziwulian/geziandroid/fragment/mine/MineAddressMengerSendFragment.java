package com.geziwulian.geziandroid.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.adapter.MineAddressMengerAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/9/14.
 */
public class MineAddressMengerSendFragment extends BaseFragment {

    @BindView(R.id.mine_address_menger_fragment_swipe)
    SwipeRefreshLayout mSwipe;

    @BindView(R.id.mine_address_menger_fragment_recyclerview)
    RecyclerView mRecycler;

    private Unbinder unbinder;
    private List<String> list = new ArrayList<>();
    private MineAddressMengerAdapter adapter;
    private boolean isPrepared;//初始化标志位

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_address_menger_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        unbinder = ButterKnife.bind(this,view);
        initView();
        isPrepared = true;
        lazyLoad();
    }

    private void initView(){
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecycler.setHasFixedSize(true);
        mRecycler.addItemDecoration(new DividerDecoration(getActivity()));
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MineAddressMengerAdapter(mContext);
        mRecycler.setAdapter(adapter);

    }

    private void initData(){
        //shuju
        if (mSwipe.isRefreshing()) mSwipe.setRefreshing(false);
        for (int i=0;i<10;i++){
            list.add(i+"");
        }
        adapter.addData(list);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showToast("刷新动作");
                initData();
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

        adapter.setOnClickDeleteListener(new MineAddressMengerAdapter.OnClickDeleteListener() {
            @Override
            public void OnClickDelete(View view, int postion) {
                showToast("删除操作");
            }
        });
        adapter.setOnClickEditListener(new MineAddressMengerAdapter.OnClickEditListener() {
            @Override
            public void OnClickEdit(View view, int postion) {
                showToast("编辑操作");
            }
        });
        adapter.setOnClickItemListener(new MineAddressMengerAdapter.OnClickItemListener() {
            @Override
            public void OnClickItem(View view, int postion) {
                showToast("item操作");
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mSwipe.isRefreshing()) mSwipe.setRefreshing(true);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            //不执行lazyload
        }else {
            //执行lazyload
            mSwipe.setRefreshing(true);
            initData();
        }
    }
}
