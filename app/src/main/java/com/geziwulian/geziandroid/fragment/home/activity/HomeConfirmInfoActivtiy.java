package com.geziwulian.geziandroid.fragment.home.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/20.
 * 确认信息
 */
public class HomeConfirmInfoActivtiy extends BaseActivity {
    @BindView(R.id.home_confirm_info_toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_confirm_info_layout);
        AppManager.getAppManager().addActivity(mContext);
        initView();
    }

    private void initView(){
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
}
