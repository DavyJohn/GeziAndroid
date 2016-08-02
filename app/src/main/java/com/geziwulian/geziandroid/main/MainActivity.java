package com.geziwulian.geziandroid.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.netlibrary.ApiWrapper;
import com.geziwulian.netlibrary.model.dinner.BannerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;


public class MainActivity extends BaseActivity {

    private static final String TAG = getTagName(MainActivity.class);

    @BindView(R.id.main_viewpager)
    ViewPager mPager;
    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private FragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTest();
    }

    private void initTest() {
        final ApiWrapper  w = new ApiWrapper();
        Subscription s = w.loadBanner()
                .subscribe(newSubscriber(new Action1<List<BannerData>>() {
                    @Override
                    public void call(List<BannerData> bannerDatas) {
                        Log.e("=======",bannerDatas+"");
                        System.out.print(bannerDatas);
                    }
                }));
        mCompositeSubscription.add(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
