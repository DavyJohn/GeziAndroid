package com.geziwulian.geziandroid.fragment.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.order.activity.OrderSearchResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/9/18.
 * 查询快递
 */
public class OrderSearchFragment extends BaseFragment {
    private Unbinder unbinder;
    @BindView(R.id.order_search_et_city)
    EditText mTextCity;
    @BindView(R.id.order_search_et_code)
    EditText mTextCode;
    @BindView(R.id.order_search_btn_search)
    ImageButton mBtn;
    @OnClick(R.id.order_search_btn_search) void clickSearch(){
        if (mTextCode.getText().toString().length() == 0||mTextCity.getText().toString().length() ==0){
            showToast("查询信息不完整");
        }else {
            startActivity(OrderSearchResultActivity.class);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_search_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        unbinder = ButterKnife.bind(this,view);
    }

    @Override
    protected void lazyLoad() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
