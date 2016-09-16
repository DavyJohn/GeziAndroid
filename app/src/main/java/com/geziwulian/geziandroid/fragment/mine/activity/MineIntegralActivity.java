package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;

/**
 * Created by 志浩 on 2016/9/16.
 */
public class MineIntegralActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integral_main_layout);
        AppManager.getAppManager().addActivity(mContext);
    }
}
