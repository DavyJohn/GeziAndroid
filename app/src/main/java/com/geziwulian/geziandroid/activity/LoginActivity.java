package com.geziwulian.geziandroid.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/8/2.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.btnSure)
    Button mBtnSend;

    @OnClick(R.id.btnSure) void login(){
        isLogin();
    }
    @OnClick(R.id.btnClose) void exit(){
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);
    }

    private void isLogin(){
        if (isMobileNO(mPhone.getText().toString())){
            showLog("login");
        }else {
            showLog("phone 错误");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }
}
