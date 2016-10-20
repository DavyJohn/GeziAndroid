package com.geziwulian.geziandroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.home.HomeFragment;
import com.geziwulian.geziandroid.fragment.mine.MineFragment;
import com.geziwulian.geziandroid.fragment.order.OrderFragment;
import com.geziwulian.geziandroid.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = getTagName(MainActivity.class);

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_text)
    TextView mHomeText;
    @BindView(R.id.order_text)
    TextView mOrderText;
    @BindView(R.id.mine_text)
    TextView mMineText;
    @BindView(R.id.main_viewpager)
    ViewPager mPager;

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @OnClick(R.id.home_linear)
    void clickhome() {
        mPager.setCurrentItem(0);
        setTab(0);
        setUpToolbar(0);
    }

    @OnClick(R.id.order_linear)
    void clickorder() {
        mPager.setCurrentItem(1);
        setTab(1);
        setUpToolbar(1);

    }

    @OnClick(R.id.mine_linear)
    void clickmine() {
        mPager.setCurrentItem(2);
        setTab(2);
        setUpToolbar(2);

    }

    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(mContext);
        initFragment();
        setUpToolbar(0);

    }

    private void setUpToolbar(int index){
        switch (index){
            case 0:
                TextView mToolTitleHome = (TextView) mToolbar.findViewById(R.id.tooltitle);
                mToolTitleHome.setText(R.string.home);
                mToolbar.setTitle("");
                setSupportActionBar(mToolbar);
                break;
            case 1:
                TextView mToolTitleOrder = (TextView) mToolbar.findViewById(R.id.tooltitle);
                mToolTitleOrder.setText(R.string.order);
                mToolbar.setTitle("");
                setSupportActionBar(mToolbar);
                break;
            case 2:
                TextView mToolTitleMine = (TextView) mToolbar.findViewById(R.id.tooltitle);
                mToolTitleMine.setText(R.string.persional_center);
                mToolbar.setTitle("");
                setSupportActionBar(mToolbar);
                break;
        }
    }

    private void initFragment() {
        Fragment home = new HomeFragment();
        Fragment order = new OrderFragment();
        Fragment mine = new MineFragment();

        listFragment.add(home);
        listFragment.add(order);
        listFragment.add(mine);

        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }
        };

        mPager.setAdapter(pagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int num = mPager.getCurrentItem();
                setTab(num);
                setUpToolbar(num);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setTab(int index) {
        clean();
        switch (index) {
            case 0:
                mHomeText.setTextSize(12);
                break;
            case 1:
                mOrderText.setTextSize(12);
                break;
            case 2:
                mMineText.setTextSize(12);
                break;
        }
    }

    private void clean() {
        mOrderText.setTextSize(10);
        mMineText.setTextSize(10);
        mHomeText.setTextSize(10);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit(mContext);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTab(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }


}
