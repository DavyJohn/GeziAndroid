package com.geziwulian.geziandroid.activity;


import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.sms.ReadSmsContent;
import com.geziwulian.geziandroid.utils.CircleTransform;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.netlibrary.ApiWrapper;
import com.geziwulian.netlibrary.model.login.Token;
import com.geziwulian.netlibrary.utils.DeviceUtil;
import com.geziwulian.netlibrary.utils.StringUtil;
import com.geziwulian.netlibrary.utils.TokenUntil;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/2.
 * 登陆界面
 */

public class LoginActivity extends BaseActivity {


    private static final String TAG = getTagName(LoginActivity.class);
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

    private AppCompatActivity activity;
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
        mTimeThread.run();
    }

    private static final int MSG_SET_ALIAS = 1001;
    private static final int MESSAGE_TIMER = 1002 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_layout);
        initView();
        activity = this;
    }

    private void initView(){
        Picasso.with(getApplicationContext()).load(R.mipmap.ic_launcher).transform(new CircleTransform()).into(mImageAppLogo);
    }

    private void isLogin() {
        if (isMobileNO(mPhone.getText().toString())&&mPhoneCode.length() == 6) {
            getToken();
        } else {

        }
    }

    private CountDownTimer timer = new CountDownTimer(10000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            if (!activity.isFinishing()){
                mBtnCode.setClickable(false);
                mBtnCode.setText("(" + millisUntilFinished / 1000 + ")" + "秒后重新发送");
                Log.e(TAG,"(" + millisUntilFinished / 1000 + ")" + "秒后重新发送");
            }else {
                timer.cancel();
            }

        }

        @Override
        public void onFinish() {
            if (!activity.isFinishing()){
                mBtnCode.setText(R.string.get_code_number);
                mBtnCode.setClickable(true);// 重新获得点击
                mBtnCode.setEnabled(true);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (readSmsContent != null){
            this.getContentResolver().unregisterContentObserver(readSmsContent);
        }
        ButterKnife.bind(this).unbind();
        mHandler.removeCallbacksAndMessages(null);
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
    /**
     * 获取TOKEN
     * */
    public void getToken() {
        ApiWrapper  wrapper = new ApiWrapper();
        Subscription subscription = wrapper.login(mPhone.getText().toString().trim(),mPhoneCode.getText().toString().trim())
                .subscribe(newSubscriber(new Action1<Token>() {
                    @Override
                    public void call(Token token) {
                        TokenUntil.saveToken(token.token);
                        Set<String> set = new HashSet<String>();
                        set.add(Constant.Jpush_Tag_1);
                        set.add(Constant.Jpush_Tag_2);
                        set.add(Constant.Jpush_Tag_3);
                        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,set));
                    }
                }));
        mCompositeSubscription.add(subscription);

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
                        readSmsContent = new ReadSmsContent(new Handler(), mContext, mPhoneCode);
                        mContext.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, readSmsContent);
                    }
                }));
        mCompositeSubscription.add(s);
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    showLog(code+"");
                    finish();
                    break;
                case 6002:
                    showLog("6002");
                    break;
                default:
                    showLog(code+"");
                    break;
            }
        }
    };
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_SET_ALIAS:
                    JPushInterface.setAliasAndTags(LoginActivity.this,
                            mPhone.getText().toString().trim(),
                            (Set<String>) msg.obj,
                            mAliasCallback);
                    break;
                case MESSAGE_TIMER:
                    if (isMobileNO(mPhone.getText().toString().trim())){
                        timer.start();
                        getCode();
                    }else {
                        showToast(getString(R.string.is_phonen_umber));
                    }
                    break;
            }
        }
    };

    Thread mTimeThread = new Thread(){
        @Override
        public void run() {
          Message message = new Message();
            message.what = MESSAGE_TIMER;
            mHandler.sendMessage(message);

        }
    };

}
