package com.geziwulian.geziandroid.fragment.home.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeAddresseeAdapter;
import com.geziwulian.geziandroid.utils.AddressData;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.MyAddresseeProvider;
import com.geziwulian.geziandroid.utils.SimpleDividerItemDecoration;
import com.geziwulian.geziandroid.utils.SqliteTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/9/20.
 * 收件
 */
public class HomeAddresseeActivity extends BaseActivity {
    @BindView(R.id.home_addressee_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_sender_address_menger_swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.home_sender_address_menger_recycler)
    RecyclerView mRecycler;
    @OnClick(R.id.home_sender_address_menger_add_new_address) void addNewAddress(){
        Constant.HOME_SAVE = 1;
        startActivity(HomeAddAddressInfoActivity.class);
    }

    private HomeAddresseeAdapter adapter;
    private List<AddressData> list = new ArrayList<>();
    private List<Integer> key = new ArrayList<>();
    private Cursor cursor;
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
                initData();
            }
        });

        mRecycler.setAdapter(adapter);
        list.clear();
        cursor = getContentResolver().query(MyAddresseeProvider.URI,null,null,null,null);
        while (cursor.moveToNext()){
            AddressData data = new AddressData();
            data.setName(cursor.getString(cursor.getColumnIndex("userAddresseeName")));
            data.setAddress(cursor.getString(cursor.getColumnIndex("userAddresseeInfo")));
            data.setPhone(cursor.getString(cursor.getColumnIndex("userAddresseePhone")));
            list.add(data);
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
                Constant.HOME_EDIT = 1;
                getKey();
                Intent intent = new Intent(mContext,HomeEditAddressActivity.class);
                intent.putExtra("key",key.get(postion));
                startActivity(intent);
            }
        });

        adapter.setOnClickDeteleListener(new HomeAddresseeAdapter.OnClickDeteleListener() {
            @Override
            public void onClickDetele(View view, int postion) {
                getKey();
                SqliteTool.getInstance().deleteAddresseeData(mContext,key.get(postion));
                initData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mSwipe.isRefreshing()) mSwipe.setRefreshing(true);
        initData();
    }

    private void getKey(){
        cursor = getContentResolver().query(MyAddresseeProvider.URI,null,null,null,null);
        key.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("key_id"));
            key.add(id);
        }
    }
}
