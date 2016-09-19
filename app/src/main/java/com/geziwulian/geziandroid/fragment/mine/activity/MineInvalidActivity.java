package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.adapter.MineInvalidAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/18.
 */
public class MineInvalidActivity extends BaseActivity {
    @BindView(R.id.mine_invalid_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mine_invalid_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.mine_invalid_swipe)
    SwipeRefreshLayout mSwipe;
    private MineInvalidAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_invalid_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolabr();
        initView();
    }

    private void initView(){
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.addItemDecoration(new DividerDecoration(mContext));
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showToast("刷新动作");
            }
        });
    }

    private void initData(){
        if (mSwipe.isRefreshing())mSwipe.setRefreshing(false);
        for (int i= 0;i<10;i++){
            list.add(i+"");
        }
        mRecycler.setAdapter(adapter);
        adapter.addData(list);
    }

    private void setUpToolabr(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
