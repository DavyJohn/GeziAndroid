package com.geziwulian.geziandroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import com.geziwulian.netlibrary.HttpClient;
import com.squareup.picasso.Transformation;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.ButterKnife;

import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static android.graphics.Bitmap.createBitmap;


/**
 * Created by yyx on 16/1/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * LOG打印标签
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    /**
     * 全局的Context
     */
    protected Activity mContext;

    protected BaseApplication baseApplication;

    private MaterialDialog progressDialog;

    private Unbinder unbinder;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) this.getApplication();
        mContext = this;
        baseApplication.addActivity(this);
        mCompositeSubscription = new CompositeSubscription();
        AppManager.getAppManager().addActivity(this);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 创建观察者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Subscriber newSubscriber(final Action1<? super T> onNext) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof HttpClient.APIException) {
                    HttpClient.APIException exception = (HttpClient.APIException) e;
                    showToast(exception.message);
                } else if (e instanceof SocketTimeoutException) {
                    showToast("连接超时,请检查网络!");
                } else if (e instanceof ConnectException) {
                    showToast("网络连接有问题!");
                } else if (e instanceof UnknownHostException) {
                    showToast("请检查您的网络连接!");
                }
                dismissProgressDialog();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.call(t);
                }
            }

        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    /**
     * 获取全局的Context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Debug输出Log信息
     *
     * @param msg
     */
    protected void debugLog(String msg) {
        Log.e(TAG, "++++++++++++++++++++++++:" + msg);
    }

    /**
     * Error输出Log信息
     *
     * @param msg
     */
    protected void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    /**
     * Info输出Log信息
     *
     * @param msg
     */
    protected void showLog(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 长时间显示Toast提示(来自String)
     *
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     *
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    protected void showDialog(String title, String msg) {
        new MaterialDialog.Builder(this)
                .title(title)
                .content(msg)
                .positiveText(R.string.dialog_ok)
                .show();
    }

    /**
     * 消息提示对话框
     *
     * @param title
     * @param msg
     */
    protected void showProgressDialog(String title, String msg) {
        if (progressDialog != null) {
            progressDialog.show();
        } else {
            progressDialog = new MaterialDialog.Builder(this)
                    .title(title)
                    .content(msg)
                    .progress(true, 2)
                    .build();
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected static String getTagName(Class<?> cls) {
        return cls.getPackage().getName() + "|" + cls.getSimpleName();
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 带有右进右出动画的退出
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 默认退出
     */
    public void defaultFinish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) progressDialog.dismiss();
        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
        mCompositeSubscription.unsubscribe();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(mContext);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(mContext);
    }

    @TargetApi(19)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        // Translucent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // Translucent navigation bar
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

}
