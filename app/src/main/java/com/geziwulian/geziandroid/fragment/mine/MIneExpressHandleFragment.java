package com.geziwulian.geziandroid.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.fragment.mine.activity.MineOrderDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/9/15.
 * 快递处理中
 */
public class MIneExpressHandleFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.mine_express_handle_cancle_setting)
    RelativeLayout mRelativeCancleSetting;
    @BindView(R.id.mine_express_handle_reasult)
    LinearLayout mLinearReasult;
    @BindView(R.id.mine_express_handle_cancle)
    TextView mTextCancle;
    @BindView(R.id.mine_express_handle_details)
    TextView mTextDetails;
    private boolean isPrepared;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_express_handle_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        unbinder = ButterKnife.bind(this,view);
        mLinearReasult.setVisibility(View.GONE);
        mTextCancle.setOnClickListener(this);
        mTextDetails.setOnClickListener(this);
        mRelativeCancleSetting.setVisibility(View.GONE);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            //TODO不执行
        }else {
            //TODO 执行
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mine_express_handle_cancle:
                showToast("取消");
                break;
            case R.id.mine_express_handle_details:
                showToast("详情");
                startActivity(MineOrderDetailsActivity.class);
                break;
        }
    }
}
