package com.geziwulian.geziandroid.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.ui.swipebacklayout.SwipeBackActivity;
import com.geziwulian.geziandroid.utils.AppManager;
import com.geziwulian.geziandroid.utils.DataCleanManager;
import com.kyleduo.switchbutton.SwitchButton;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/8/4.
 * 设置
 */
public class SettingActivity extends SwipeBackActivity {

    private static final String TAG = getTagName(SettingActivity.class);
    @BindView(R.id.set_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rom_size)
    TextView mSizeText;
    @BindView(R.id.setting_switch_btn)
    SwitchButton mSwitchBtn;
    @OnClick(R.id.clear_rom)
    void clear() {
        DataCleanManager.cleanCache(this);
        DataCleanManager.cleanSharedPreference(this);
        mSizeText.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        setupToolabr();
        initView();
    }

    private void setupToolabr() {
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
        mSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true){
                    mSwitchBtn.setBackColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.switch_color)));
                }else {
                    mSwitchBtn.setBackColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.gray_light)));
                }
            }
        });

        File cacheFile = new File(this.getCacheDir(), "httpcache");
        try {
            mSizeText.setText(DataCleanManager.getCacheSize(cacheFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
