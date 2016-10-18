package com.geziwulian.geziandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.BindView;

/**
 * Created by 志浩 on 2016/9/22.
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.home_add_address_info_toolbar)
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
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
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.setWebChromeClient(new WebChromeClient(){});
        mWebView.addJavascriptInterface(new JsInteration(),"interactive");
        try {
            //设置打开的页面地址
            mWebView.loadUrl("file:///android_asset/AddAddress.html");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void interactive(WebView webview){
        String call = "javascript:infoMessage()";
        webview.loadUrl(call);
    }
    private class JsInteration{
        @JavascriptInterface
        public void message(String name,String phone,String city,String address){
            if (name.length() == 0||phone.length() == 0 ||city.length() == 0||address.length() == 0){
                showToast("填写数据不完整");
            }else {
                showToast("姓名："+name+"手机"+phone+"城市"+city+"地址"+address);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save){
            interactive(mWebView);
        }
        return super.onOptionsItemSelected(item);
    }


}
