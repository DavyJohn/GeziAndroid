package com.geziwulian.geziandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.ui.swipebacklayout.SwipeBackActivity;
import com.geziwulian.geziandroid.utils.DataCleanManager;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/8/4.
 */
public class SettingActivity extends SwipeBackActivity {

    private static final String TAG = getTagName(SettingActivity.class);

    @BindView(R.id.rom_size)
    TextView mSizeText;
    @OnClick(R.id.clear_rom) void  clear(){
        DataCleanManager.cleanCache(this);
        DataCleanManager.cleanSharedPreference(this);
        mSizeText.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main_layout);
        initView();
    }

    private void initView() {
        File cacheFile = new File(this.getCacheDir(), "httpcache");
        try {
            mSizeText.setText(DataCleanManager.getCacheSize(cacheFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
