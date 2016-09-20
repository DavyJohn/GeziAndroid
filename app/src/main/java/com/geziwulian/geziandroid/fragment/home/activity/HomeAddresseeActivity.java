package com.geziwulian.geziandroid.fragment.home.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeAddresseeAdapter;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeSenderAdapter;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/9/20.
 */
public class HomeAddresseeActivity extends BaseActivity {
    @BindView(R.id.home_addressee_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_sender_address_menger_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.home_sender_address_menger_recycler)
    RecyclerView mRecycler;
    @OnClick(R.id.home_sender_address_menger_add_new_address) void addNewAddress(){
        startActivity(HomeAddAddressInfoActivity.class);
    }

    private HomeAddresseeAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_addressee_layout);
        initView();
    }
    private void initView(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecycler.setHasFixedSize(true);
        mRecycler.addItemDecoration(new SimpleDividerItemDecoration(mContext,8));
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new HomeAddresseeAdapter (mContext);
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
        list.clear();
        for (int i =0;i<2;i++ ){
            list.add(i+"");
        }
        adapter.addData(list,Constant.HOME_ADDRESSEE_SIGN );
        adapter.setOnClickItemListener(new HomeAddresseeAdapter.OnClickItemListeren() {
            @Override
            public void onClickItem(View view, int postion) {
                Constant.HOME_ADDRESSEE_SIGN = postion;
                adapter.inputPostion(postion);
            }
        });

        adapter.setOnClickEditListener(new HomeAddresseeAdapter.OnClickEditListener() {
            @Override
            public void onClickEdit(View view, int postion) {
                showToast("编辑");
            }
        });

        adapter.setOnClickDeteleListener(new HomeAddresseeAdapter.OnClickDeteleListener() {
            @Override
            public void onClickDetele(View view, int postion) {
                showToast("删除");
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
