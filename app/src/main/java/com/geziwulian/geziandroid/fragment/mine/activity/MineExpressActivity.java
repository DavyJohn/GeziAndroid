package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.MIneExpressCancelFragment;
import com.geziwulian.geziandroid.fragment.mine.MIneExpressFinishedFragment;
import com.geziwulian.geziandroid.fragment.mine.MIneExpressHandleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/15.
 * 我的快递
 */
public class MineExpressActivity  extends BaseActivity implements OnTabSelectListener{

    @BindView(R.id.mine_express_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.mine_express_sliding_tab)
    SlidingTabLayout mSliding;
    @BindView(R.id.mine_express_vp)
    ViewPager mVpager;
    private List<Fragment> listView = new ArrayList<>();
    private FragmentPagerAdapter adapter;
    private final String[] mTitles = {"处理中","已完成","已取消"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_express_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setUpToolbar();
        initView();
    }

    private void initView(){
        Fragment handle = new MIneExpressHandleFragment();
        Fragment finished = new MIneExpressFinishedFragment();
        Fragment cancle = new MIneExpressCancelFragment();

        listView.add(handle);
        listView.add(finished);
        listView.add(cancle);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listView.get(position);
            }

            @Override
            public int getCount() {
                return listView.size();
            }
        };
        mVpager.setAdapter(adapter);
        mSliding.setViewPager(mVpager,mTitles);
        mSliding.setOnTabSelectListener(MineExpressActivity.this);

    }
    private void setUpToolbar(){
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
    public void onTabSelect(int position) {
        mVpager.setCurrentItem(position);
    }

    @Override
    public void onTabReselect(int position) {
        //TODO 复选
        mVpager.setCurrentItem(position);
    }
}
