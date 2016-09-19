package com.geziwulian.geziandroid.fragment.home.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeSenderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/19.
 */
public class HomeSenderActivity extends BaseActivity {

    @BindView(R.id.home_sender_address_menger_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_sender_address_menger_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.home_sender_address_menger_recycler)
    RecyclerView mRecycler;

    private List<String> list = new ArrayList<>();
    private HomeSenderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_sender_address_menger_layout);
        AppManager.getAppManager().addActivity(mContext);
        initView();
    }

    private void initView(){
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new HomeSenderAdapter(mContext);
    }

    private void initData(){
        if (mSwipe.isRefreshing()) mSwipe.setRefreshing(false);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 属性操作
            }
        });

        mRecycler.setAdapter(adapter);
        for (int i =0;i<3;i++ ){
            list.add(i+"");
        }
        adapter.addData(list);
        adapter.setOnClickItemListener(new HomeSenderAdapter.OnClickItemListeren() {
            @Override
            public void onClickItem(View view, int postion) {
                adapter.inputPostion(postion);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSwipe.isRefreshing()) mSwipe.setRefreshing(true);
        initData();
    }
}
