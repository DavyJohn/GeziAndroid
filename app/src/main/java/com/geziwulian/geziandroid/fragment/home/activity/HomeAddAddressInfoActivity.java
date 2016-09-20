package com.geziwulian.geziandroid.fragment.home.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.geziwulian.geziandroid.AppManager;
import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.MapViewActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.SqliteTool;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/9/20.
 * 添加新地址
 */
public class HomeAddAddressInfoActivity  extends BaseActivity{
    @BindView(R.id.home_add_address_info_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_add_address_info_detail_et)
    EditText mEtDetail;
    @BindView(R.id.home_add_address_info_location_et)
    EditText mEtLocation;
    @BindView(R.id.home_add_address_info_name_et)
    EditText mEtName;
    @BindView(R.id.home_add_address_info_phone_et)
    EditText mEtPhone;
    @OnClick(R.id.home_add_address_info_location_image) void onClickLocation(){
        startActivity(MapViewActivity.class);
    }
    String name,phone,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_add_address_info_layout);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save){
            name = mEtName.getText().toString().trim();
            phone = mEtPhone.getText().toString().trim();
            address = mEtDetail.getText().toString().trim();
            if (mEtName.getText().toString().length() != 0&& mEtPhone.getText().toString().length() != 0&&mEtDetail.getText().toString().length() != 0){
                SqliteTool.getInstance().addData(mContext,name,phone,address);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
