package com.geziwulian.geziandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 志浩 on 2016/8/2.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.app_log)
    ImageView mImageAppLogo;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.btnSure)
    Button mBtnSend;
    @BindView(R.id.btnGetCode)
    Button mBtnCode;
    @OnClick(R.id.btnSure) void login(){
        isLogin();
    }
    @OnClick(R.id.btnClose) void exit(){
        finish();
    }
    @OnClick(R.id.btnGetCode) void code(){
        timer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);

        Picasso.with(getApplicationContext()).load(R.mipmap.ic_launcher).transform(new CircleTransform()).into(mImageAppLogo);
    }

    private void isLogin(){
        if (isMobileNO(mPhone.getText().toString())){
            showLog("login");
        }else {
            showLog("phone 错误");
        }
    }

    private CountDownTimer timer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            mBtnCode.setClickable(false);// 设置不能点击
            mBtnCode.setText(millisUntilFinished / 1000 + "秒后重新发送");// 设置倒计时时间
            Spannable span = new SpannableString(mBtnCode.getText()
                    .toString());// 获取按钮的文字
            span.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(),R.color.red)), 0, 2,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);// 讲倒计时时间显示为红色
            mBtnCode.setText(span);
        }

        @Override
        public void onFinish() {
            mBtnCode.setText("获取验证码");
            mBtnCode.setClickable(true);// 重新获得点击
        }
    };


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
