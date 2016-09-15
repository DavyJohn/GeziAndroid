package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.MineAddressMengerGetFragment;
import com.geziwulian.geziandroid.fragment.mine.MineAddressMengerSendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/9/14.
 * 地址管理
 */
public class MineAddressMengerActivity extends BaseActivity {

    @BindView(R.id.mine_address_menger_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mine_address_menger_send_goods)
    TextView mTextSend;

    @BindView(R.id.mine_address_menger_get_goods)
    TextView mTextGet;

    @BindView(R.id.mine_address_menger_viewpager)
    ViewPager mViewPager;

    @OnClick(R.id.mine_address_menger_send_goods) void send(){
        setTab(0);
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.mine_address_menger_get_goods) void get(){
        setTab(1);
        mViewPager.setCurrentItem(1);
    }

    private List<Fragment> view = new ArrayList<Fragment>();
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_address_menger_layout);
        AppManager.getAppManager().addActivity(mContext);
        setupToolbar();
    }

    private void initData(){
        setTab(0);
        Fragment sendFragmeng = new MineAddressMengerSendFragment();
        Fragment getFragment = new MineAddressMengerGetFragment();

        view.add(sendFragmeng);
        view.add(getFragment);

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

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int num = mViewPager.getCurrentItem();
                setTab(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    private void setTab(int index){
        clean();
        switch (index){
            case 0:
                mTextSend.setTextColor(ContextCompat.getColor(mContext,R.color.maincolor));
                break;
            case 1:
                mTextGet.setTextColor(ContextCompat.getColor(mContext,R.color.maincolor));
                break;
        }
    }

    private void clean() {
        mTextSend.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_black));
        mTextGet.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_black));
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }
}
