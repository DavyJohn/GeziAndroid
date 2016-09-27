package com.geziwulian.geziandroid.fragment.home;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.home.activity.HomeAddresseeActivity;
import com.geziwulian.geziandroid.fragment.home.activity.HomeConfirmInfoActivtiy;
import com.geziwulian.geziandroid.fragment.home.activity.HomeSenderActivity;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeAdapter;
import com.geziwulian.geziandroid.fragment.home.adapter.HomeSenderAdapter;
import com.geziwulian.geziandroid.utils.AddressData;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.DividerDecoration;
import com.geziwulian.geziandroid.utils.MyContentProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/8/1.
 */
public class HomeFragment extends BaseFragment {

    private boolean isPrepared;//初始化标志位
    private Unbinder unbinder;
    private HomeAdapter adapter;
    @BindView(R.id.home_fragment_text_ji)
    TextView mTextJi;
    @BindView(R.id.home_fragment_recyclerview)
    RecyclerView mRecycler;
    @BindView(R.id.home_fragment_clause_text)
    TextView  mTextClause;
    @BindView(R.id.home_fragment_relative_ji)
    RelativeLayout mRelative;
    @BindView(R.id.home_fragment_ji_name)
    TextView mTextJiName;
    @BindView(R.id.home_fragment_ji_phone)
    TextView mTextJiPhone;
    @BindView(R.id.home_fragment_ji_address)
    TextView mTextJiAddress;
    @OnClick(R.id.home_fragment_clause_text) void clickClause(){
        showToast("条款");
    }
    @OnClick(R.id.home_fragment_Mailing) void clickMail(){
        startActivity(HomeSenderActivity.class);
    }

    @OnClick(R.id.home_fragment_consignee) void clickConsignee(){
        startActivity(HomeAddresseeActivity.class);
    }
    @OnClick(R.id.home_fragment_express_money) void clickExpress(){
        showToast("快递运费");
    }

    @OnClick(R.id.home_fragment_standard) void clickStandard(){
        showToast("计件标准");
    }
    @OnClick(R.id.home_fragment_payfor_style) void clickPay(){
        showToast("付款方式");
    }
    @OnClick(R.id.home_fragment_goods_style) void clickGoodsStyle(){
        showToast("物品类型");
    }
    @OnClick(R.id.home_fragment_btn_next) void clickNext(){
        startActivity(HomeConfirmInfoActivtiy.class);
    }

    private String [] cpName = {"顺丰速运","申通快递","中通快递","EMS","韵达快递","圆通快递","德邦快递"};
    private Integer [] cpLogo = {R.mipmap.shunfen,R.mipmap.shengtong,R.mipmap.zhongtong,R.mipmap.ems
            ,R.mipmap.yunda,R.mipmap.yuantong,R.mipmap.debang};

    private List<String> listName = new ArrayList<>();
    private List<Integer> listIcon = new ArrayList<>();
    private List<AddressData> data = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);
        initView();
        isPrepared = true;
        lazyLoad();
    }

    private void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.setHasFixedSize(true);
        adapter = new HomeAdapter(mContext);
        String title1 = "<font color=\"#262626\">我同意</font>";
        String title2 = "<font color=\"#00bb09\">《鸽子物联服务条款》</font>";
        mTextClause.setText(Html.fromHtml(title1+title2));
    }

    private void initData(){
        if (Constant.HOME_SENDER_SIGN == -1){
            //没有地址情况下
            mRelative.setVisibility(View.GONE);
            mTextJi.setVisibility(View.VISIBLE);
        }else {
            //有地址的情况下显示地址
            mTextJi.setVisibility(View.GONE);
            mRelative.setVisibility(View.VISIBLE);
            Cursor cursor = mContext.getContentResolver().query(MyContentProvider.URI,null,null,null,null);
            data.clear();
            while (cursor.moveToNext()){
                AddressData addressData = new AddressData();
                String name = cursor.getString(cursor.getColumnIndex("userName"));
                String phone = cursor.getString(cursor.getColumnIndex("userPhone"));
                String address = cursor.getString(cursor.getColumnIndex("userAAddress"));
                addressData.setName(name);
                addressData.setAddress(address);
                addressData.setPhone(phone);
                data.add(addressData);
            }
            mTextJiAddress.setText(data.get(Constant.HOME_SENDER_SIGN).getAddress());
            mTextJiName.setText(data.get(Constant.HOME_SENDER_SIGN).getName());
            mTextJiPhone.setText(data.get(Constant.HOME_SENDER_SIGN).getPhone());
        }
        listIcon.clear();
        listName.clear();
        for (int i=0;i<cpName.length;i++){
            listName.add(cpName[i]);
            listIcon.add(cpLogo[i]);
        }
        mRecycler.setAdapter(adapter);
        adapter.addData(listName,listIcon);
        //快递公司图标
        adapter.setOnClickItemListener(new HomeAdapter.OnClickItemListener() {
            @Override
            public void OnClicItem(View view, int postion) {
                adapter.inputPostion(postion);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared ||!isVisible){
        }else {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
