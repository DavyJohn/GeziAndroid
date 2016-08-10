package com.geziwulian.geziandroid.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.sms.ReadSmsContent;
import com.geziwulian.geziandroid.utils.CircleTransform;
import com.geziwulian.netlibrary.ApiWrapper;
import com.geziwulian.netlibrary.utils.DeviceUtil;
import com.geziwulian.netlibrary.utils.StringUtil;
import com.squareup.picasso.Picasso;
import com.ta.utdid2.android.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/2.
 */

public class LoginActivity extends BaseActivity {


    private ReadSmsContent readSmsContent;
    @BindView(R.id.app_log)
    ImageView mImageAppLogo;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.code)
    EditText mPhoneCode;
    @BindView(R.id.btnSure)
    Button mBtnSend;
    @BindView(R.id.btnGetCode)
    Button mBtnCode;

    @OnClick(R.id.btnSure)
    void login() {
        isLogin();
    }

    @OnClick(R.id.btnClose)
    void exit() {
        finish();
    }

    @OnClick(R.id.btnGetCode)
    void code() {
        timer.start();
        getCode();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);
        initView();

    }

    private void initView(){
        Picasso.with(getApplicationContext()).load(R.mipmap.ic_launcher).transform(new CircleTransform()).into(mImageAppLogo);
    }

    private void isLogin() {
        if (isMobileNO(mPhone.getText().toString())) {
            showLog("login");
            getToken();
        } else {
            showLog("phone 错误");
        }
    }

    private CountDownTimer timer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            mBtnCode.setClickable(false);
            mBtnCode.setText("(" + millisUntilFinished / 1000 + ")" + "秒后重新发送");
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

    public void getToken() {
        ApiWrapper  w = new ApiWrapper();
    }
    /**
     * 获取验证码
     * */
    private void getCode(){
        final  ApiWrapper wrapper = new ApiWrapper();
        Subscription s = wrapper.getSmsCode(mPhone.getText().toString().trim(),
                DeviceUtil.DEVICE_TYPE,
                DeviceUtil.getDevice(getApplicationContext()), StringUtil.getMD5(mPhone.getText().toString().trim()+DeviceUtil.getDevice(getApplicationContext())))
                .subscribe(newSubscriber(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                        readSmsContent = new ReadSmsContent(new Handler(), mContext, mPhoneCode);
                        mContext.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, readSmsContent);
                    }
                }));
        mCompositeSubscription.add(s);
    }
}
