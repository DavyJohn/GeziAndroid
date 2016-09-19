package com.geziwulian.geziandroid.fragment.order.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.order.adapter.OrderSearchResultAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/19.
 */
public class OrderSearchResultActivity extends BaseActivity {

    @BindView(R.id.order_search_result_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.order_search_result_code)
    TextView mTextCode;
    @BindView(R.id.order_search_result_company_logo)
    ImageView mImageLogo;
    @BindView(R.id.order_search_result_toolbar)
    Toolbar mToolbar;
    private List<String> list = new ArrayList<>();
    private OrderSearchResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_search_result_layout);
        initView();
    }

    private void initView(){
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.addItemDecoration(new DividerDecoration(mContext));
        adapter = new OrderSearchResultAdapter(mContext);
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

    private void initData(){
        for (int i=0;i<4;i++){
            list.add(i+"");
        }
        mRecycler.setAdapter(adapter);
        adapter.addData(list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }
}
