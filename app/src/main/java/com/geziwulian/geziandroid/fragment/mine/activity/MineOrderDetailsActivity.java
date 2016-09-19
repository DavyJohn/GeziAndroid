package com.geziwulian.geziandroid.fragment.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/16.
 */
public class MineOrderDetailsActivity extends BaseActivity {

    @BindView(R.id.mine_order_details_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_order_details_layout);
        AppManager.getAppManager().addActivity(mContext);

        setuUpToolBar();
    }

    private void setuUpToolBar(){
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
