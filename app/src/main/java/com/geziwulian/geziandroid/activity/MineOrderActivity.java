package com.geziwulian.geziandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.MineOrderPageLeftFragment;
import com.geziwulian.geziandroid.fragment.mine.MineOrderPageRightFragment;
import com.geziwulian.geziandroid.ui.swipebacklayout.SwipeBackActivity;
import com.geziwulian.geziandroid.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/8/6.
 * 查看订单
 */
public class MineOrderActivity extends SwipeBackActivity {

    private static final String TAG = getTagName(MineOrderActivity.class);
    @BindView(R.id.tab_layout)
    SegmentTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mPage;
    @BindView(R.id.mine_order_toolbar)
    Toolbar mToolbar;
    String[] title = {"订单", "取消"};

    private List<Fragment> view = new ArrayList<Fragment>();
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_order_layout);
        AppManager.getAppManager().addActivity(mContext);
        initView();
        setupToolbar();
        mTabLayout.setTabData(title);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
                mPage.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
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

    private void initView() {
        Fragment leftPage = new MineOrderPageLeftFragment();
        Fragment rightPage = new MineOrderPageRightFragment();

        view.add(leftPage);
        view.add(rightPage);

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return view.get(position);
            }

            @Override
            public int getCount() {
                return view.size();
            }
        };

        mPage.setAdapter(pagerAdapter);

        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int num = mPage.getCurrentItem();
                mTabLayout.setCurrentTab(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
