package com.geziwulian.geziandroid.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.activity.LoginActivity;
import com.geziwulian.geziandroid.activity.LoginDemoActivity;
import com.geziwulian.geziandroid.activity.MineOrderActivity;
import com.geziwulian.geziandroid.activity.SettingActivity;
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
        headerBg.setImageResource(R.mipmap.ic_img_profile_bg);//给图片设置src

        ImageView headImage = (ImageView) headView.findViewById(R.id.iv_user_head);
        Picasso.with(getActivity()).load(R.mipmap.ic_launcher).transform(new CircleTransform()).into(headImage);
        headView.findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click", Toast.LENGTH_SHORT).show();
            }
        });

        headView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.class);
            }
        });

        /**
         *  scrollView.getPullRootView() 获取contentView 控件
         * */
        scrollView.getPullRootView().findViewById(R.id.orderRecord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MineOrderActivity.class);
            }
        });

        scrollView.getPullRootView().findViewById(R.id.textSetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity.class);

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
