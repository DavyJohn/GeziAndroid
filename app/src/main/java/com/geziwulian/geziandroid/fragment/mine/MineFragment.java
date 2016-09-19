package com.geziwulian.geziandroid.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.activity.ChangeHeadActivity;
import com.geziwulian.geziandroid.activity.LoginActivity;
import com.geziwulian.geziandroid.activity.LoginDemoActivity;
import com.geziwulian.geziandroid.activity.MineOrderActivity;
import com.geziwulian.geziandroid.activity.SettingActivity;
import com.geziwulian.geziandroid.fragment.mine.activity.MineAddressMengerActivity;
import com.geziwulian.geziandroid.fragment.mine.activity.MineCouponActivity;
import com.geziwulian.geziandroid.fragment.mine.activity.MineExpressActivity;
import com.geziwulian.geziandroid.fragment.mine.activity.MineIntegralActivity;
import com.geziwulian.geziandroid.fragment.mine.activity.MineIntegralMallActivity;
import com.geziwulian.geziandroid.ui.PullToZoomScrollView;
import com.geziwulian.geziandroid.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/8/2.
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.mine_scrollview)
    PullToZoomScrollView scrollView;
    private Unbinder unbinder;
    private ImageView headerBg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_fargment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.member_head_view, null);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.member_zoom_view, null);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.member_content_view, null);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
        headerBg = (ImageView) zoomView.findViewById(R.id.iv_zoom);
        headerBg.setImageResource(R.drawable.backimage);//给图片设置src

        //测试
        headerBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"g个那个改头像",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ChangeHeadActivity.class));
            }
        });
        ImageView headImage = (ImageView) headView.findViewById(R.id.iv_user_head);
        Picasso.with(getActivity()).load(R.mipmap.ic_launcher).transform(new CircleTransform()).into(headImage);
        headView.findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO 优惠券
            startActivity(MineCouponActivity.class);
            }
        });

        headView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 积分
            startActivity(MineIntegralActivity.class);
            }
        });

        /**
         *  scrollView.getPullRootView() 获取contentView 控件
         * */
        scrollView.getPullRootView().findViewById(R.id.member_content_address_menger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MineAddressMengerActivity.class);
            }
        });

        scrollView.getPullRootView().findViewById(R.id.member_content_express).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 我的快递
                startActivity(MineExpressActivity.class);
            }
        });

        scrollView.getPullRootView().findViewById(R.id.member_content_integral_mall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 积分商城
                startActivity(MineIntegralMallActivity.class);
            }
        });

        scrollView.getPullRootView().findViewById(R.id.member_content_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 分享有礼
            }
        });

        scrollView.getPullRootView().findViewById(R.id.member_content_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity.class);

            }
        });

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
