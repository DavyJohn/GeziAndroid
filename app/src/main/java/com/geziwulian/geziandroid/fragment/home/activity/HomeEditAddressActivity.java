package com.geziwulian.geziandroid.fragment.home.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.MyContentProvider;
import com.geziwulian.geziandroid.utils.SqliteTool;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/25.
 * 修改地址
 */

public class HomeEditAddressActivity  extends BaseActivity{

    @BindView(R.id.home_edit_address_name_et)
    EditText mEditName;
    @BindView(R.id.home_edit_address_phone_et)
    EditText mEditPhone;
    @BindView(R.id.home_edit_address_detail_et)
    EditText mEditDetail;
    @BindView(R.id.home_add_address_info_location_text)
    TextView mTextLocation;
    @BindView(R.id.home_edit_address_toolbar)
    Toolbar mToolbar;

    private String name,phone,address;
    private int key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_edit_address_layout);
        key = getIntent().getIntExtra("key",-1);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void initView(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Cursor cursor = getContentResolver().query(MyContentProvider.URI,null,"_id=?",new String[]{String.valueOf(key)},null);
        while (cursor.moveToNext()){
            mEditDetail.setText(cursor.getString(cursor.getColumnIndex("userAAddress")));
            mEditName.setText(cursor.getString(cursor.getColumnIndex("userName")));
            mEditPhone.setText(cursor.getString(cursor.getColumnIndex("userPhone")));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save){
            //TODO 保存
            name = mEditName.getText().toString();
            phone = mEditPhone.getText().toString();
            address = mEditDetail.getText().toString();
            SqliteTool.getInstance().EditData(mContext,key,name,phone,address);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
