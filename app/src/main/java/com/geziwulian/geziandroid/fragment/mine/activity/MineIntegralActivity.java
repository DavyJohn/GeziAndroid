package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.adapter.MineIntegralAdapter;
import com.geziwulian.geziandroid.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/16.
 */
public class MineIntegralActivity extends BaseActivity {

    @BindView(R.id.mine_integral_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mine_integral_recycler)
    RecyclerView mRecycler;

    private List<String> list = new ArrayList<>();
    private MineIntegralAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_integral_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolabr();
        initView();
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

    private void initView(){
        adapter = new MineIntegralAdapter(mContext);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.addItemDecoration(new DividerDecoration(mContext));
    }

    private void initData(){
        for (int i=0;i<10;i++){
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
