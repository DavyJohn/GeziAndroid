package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.adapter.MineCouponAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/16.
 */
public class MineCouponActivity extends BaseActivity {

    @BindView(R.id.mine_coupon_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mine_coupon_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.mine_coupon_recycler)
    RecyclerView mRecycler;

    private MineCouponAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_coupon_layout);
        AppManager.getAppManager().addActivity(mContext);
        setupToolbar();
        initView();
    }

    private void initView(){
        adapter = new MineCouponAdapter(mContext);
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
        for (int i=0;i<9;i++){
            list.add(i+"");
        }
        mRecycler.setAdapter(adapter);
        adapter.addData(list);
    }
    private void setupToolbar() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_conpon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_conpon_shixiao){
            startActivity(MineIntegralActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSwipe.isRefreshing())mSwipe.setRefreshing(true);
        initData();
    }
}
