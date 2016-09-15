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
 * Created by 志浩 on 2016/9/15.
 */
public class MIneExpressFinishedFragment extends BaseFragment {
    @BindView(R.id.mine_express_handle_text)
    TextView mText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_express_handle_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mText.setText("2");
    }

    @Override
    protected void lazyLoad() {

    }
}
