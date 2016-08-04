package com.geziwulian.geziandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;


import com.geziwulian.netlibrary.HttpClient;
import com.squareup.picasso.Transformation;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static android.graphics.Bitmap.createBitmap;

/**
 * Created by yyx on 16/3/17.
 */
public class BaseFragment extends Fragment {

    /**
     * LOG打印标签
     */
    private static final String TAG = BaseFragment.class.getSimpleName();

    private MaterialDialog progressDialog;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mCompositeSubscription = new CompositeSubscription();
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
                }else if (e instanceof UnknownHostException){
                    showToast("请检查您的网络连接!");
                }
                dismissProgressDialog();
                onNext.call(null);
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.call(t);
                }
            }

        };
    }

    /**
     * Debug输出Log信息
     * @param msg
     */
    protected void debugLog(String msg) {
        Log.d(TAG, "++++++++++++++++++++++++:" + msg);
    }

    /**
     * Error输出Log信息
     * @param msg
     */
    protected void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    /**
     * Info输出Log信息
     * @param msg
     */
    protected void showLog(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 长时间显示Toast提示(来自String)
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     * @param resId
     */
    protected void showToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    protected void showDialog(String title,String msg){
        new MaterialDialog.Builder(getContext())
                .title(title)
                .content(msg)
                .positiveText(R.string.dialog_ok)
                .show();
    }


    /**
     * 消息提示对话框
     * @param msg
     */
    protected void showProgressDialog(String title,String msg){
        if (progressDialog != null){
            progressDialog.show();
        }else {
            progressDialog = new MaterialDialog.Builder(getContext())
                    .title(title)
                    .content(msg)
                    .progress(true,2)
                    .build();
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog(){
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected static String getTagName(Class<?> cls){
        return cls.getPackage().getName()+"|"+cls.getSimpleName();
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
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
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
        if (progressDialog !=  null)progressDialog.dismiss();
    }

}
