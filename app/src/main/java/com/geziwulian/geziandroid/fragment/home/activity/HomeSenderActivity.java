package com.geziwulian.geziandroid.fragment.home.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeSenderAdapter;
import com.geziwulian.geziandroid.utils.AddressData;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.MyContentProvider;
import com.geziwulian.geziandroid.utils.SimpleDividerItemDecoration;
import com.geziwulian.geziandroid.utils.SqliteTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @OnClick(R.id.home_sender_address_menger_add_new_address) void addNewAddress(){
        startActivity(HomeAddAddressInfoActivity.class);

    }
    private List<AddressData> list = new ArrayList<>();
    private HomeSenderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_sender_address_menger_layout);
        AppManager.getAppManager().addActivity(mContext);
        initView();
        showLog("onCreate");
    }

    private void initView(){
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecycler.setHasFixedSize(true);
        mRecycler.addItemDecoration(new SimpleDividerItemDecoration(mContext,8));
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
        //测试数据
        Cursor cursor = getContentResolver().query(MyContentProvider.URI,null,null,null,null);
        list.clear();
        while (cursor.moveToNext()){
            AddressData data = new AddressData();
            String data1 = cursor.getString(cursor.getColumnIndex("userName"));
            data.setName(data1);
            String data2 = cursor.getString(cursor.getColumnIndex("userPhone"));
            data.setPhone(data2);
            String data3 = cursor.getString(cursor.getColumnIndex("userAAddress"));
            data.setAddress(data3);
            list.add(data);
        }
        if (mSwipe.isRefreshing()) mSwipe.setRefreshing(false);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 属性操作
                initData();
            }
        });

        mRecycler.setAdapter(adapter);
        adapter.addData(list, Constant.HOME_SENDER_SIGN);//-1 null

        adapter.setOnClickItemListener(new HomeSenderAdapter.OnClickItemListeren() {
            @Override
            public void onClickItem(View view, int postion) {
                Constant.HOME_SENDER_SIGN = postion;
                adapter.inputPostion(postion);
            }
        });

        adapter.setOnClickEditListener(new HomeSenderAdapter.OnClickEditListener() {
            @Override
            public void onClickEdit(View view, int postion) {
                showToast("编辑");
            }
        });

        adapter.setOnClickDeteleListener(new HomeSenderAdapter.OnClickDeteleListener() {
            @Override
            public void onClickDetele(View view, int postion) {
                SqliteTool.getInstance().deleteData(mContext,list.get(postion).getAddress());
                initData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("onstart");
        if (!mSwipe.isRefreshing()) mSwipe.setRefreshing(true);
        initData();
    }
}
