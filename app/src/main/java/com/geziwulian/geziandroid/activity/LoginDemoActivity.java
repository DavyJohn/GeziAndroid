package com.geziwulian.geziandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.geziwulian.geziandroid.BaseActivity;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.main.MainActivity;
import com.geziwulian.geziandroid.ui.swipebacklayout.SwipeBackActivity;
import com.geziwulian.geziandroid.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/4.
 */
public class LoginDemoActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = getTagName(LoginDemoActivity.class);
    @BindView(R.id.phone_inputlayout)
    TextInputLayout phoneLayout;
    @BindView(R.id.code_inputlayout)
    TextInputLayout codeLayout;
    @BindView(R.id.app_logo)
    ImageView appLogo;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.phone)
    EditText mUserName;
    @BindView(R.id.code)
    EditText mCode;
    private String phone,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_demo_layout);
        initView();
    }

    private void initView() {
        mBtnLogin.setOnClickListener(this);
        Picasso.with(this).load(R.mipmap.app_logo).transform(new CircleTransform()).into(appLogo);
    }

    @Override
    public void onClick(View view) {
        hideKeyboard();
        phone = mUserName.getText().toString();
        code = mUserName.getText().toString();
        switch (view.getId()){
            case R.id.btn_login:
                if (isMobileNO(phone)){
                    phoneLayout.setErrorEnabled(false);
                    startActivity(MainActivity.class);//测试
                }else{
                    phoneLayout.setError("用户名错误");
                    phoneLayout.setErrorEnabled(true);
                }
                break;
        }
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();

    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
