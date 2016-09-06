package com.geziwulian.geziandroid.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geziwulian.geziandroid.BaseFragment;
import com.geziwulian.geziandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/6.
 */
public class MineOrderPageRightFragment extends BaseFragment {

    @BindView(R.id.mine_order_title)
    TextView mTitle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_order_page_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mTitle.setText("right");
    }

    @Override
    protected void lazyLoad() {

    }
}
