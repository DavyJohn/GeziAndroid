package com.geziwulian.geziandroid.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 志浩 on 2016/9/15.
 */
public class MIneExpressCancelFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.mine_express_handle_relative_zip_code)
    RelativeLayout mRelativeZip;
    @BindView(R.id.mine_express_handle_setting)
    RelativeLayout mRelativeSetting;
    @BindView(R.id.mine_express_handle_cancle_setting)
    RelativeLayout mRelativeCancleSetting;
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
        mRelativeCancleSetting.setOnClickListener(this);
        mRelativeZip.setVisibility(View.GONE);
        mRelativeSetting.setVisibility(View.GONE);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.mine_express_handle_cancle_setting){
            showToast("删除");
        }
    }
}
