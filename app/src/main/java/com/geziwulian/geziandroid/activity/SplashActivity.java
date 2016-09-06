package com.geziwulian.geziandroid.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.AppManager;

/**
 * Created by 志浩 on 2016/8/2.
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main_layout);
        AppManager.getAppManager().addActivity(mContext);
    }
}
