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
import com.geziwulian.geziandroid.fragment.mine.adapter.MineIntegralMallAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/16.
 */
public class MineIntegralMallActivity extends BaseActivity {

    @BindView(R.id.mine_integral_mall_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mine_integral_mall_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.mine_integral_mall_recycler)
    RecyclerView mListView;

    private List<String> list = new ArrayList<>();
    private MineIntegralMallAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_integral_mall_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolbar();
        initView();
    }

    private void initView(){
        adapter = new MineIntegralMallAdapter(mContext);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                showToast("刷新动作");
            }
        });
        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(mContext));
        mListView.addItemDecoration(new DividerDecoration(mContext));
    }

    private void initData(){
        mListView.setAdapter(adapter);
        for (int i=0;i<10;i++){
            list.add(i+"");
        }
        adapter.addData(list);
        //
        if (mSwipe.isRefreshing()) mSwipe.setRefreshing(false);
        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        adapter.setOnClickItemListener(new MineIntegralMallAdapter.OnClickItemListener() {
            @Override
            public void OnClickItem(View view, int postion) {
                showToast("item");
            }
        });

        adapter.setOnClickBtnListener(new MineIntegralMallAdapter.OnClickBtnListener() {
            @Override
            public void OnClickBtn(View view, int postion) {
                showToast("btn");
            }
        });
    }

    private void setUpToolbar() {
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
        getMenuInflater().inflate(R.menu.menu_order,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mine_integral_mall_menu_order){
            showToast("订单");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSwipe.isRefreshing()) mSwipe.setRefreshing(true);
        initData();
    }
}
