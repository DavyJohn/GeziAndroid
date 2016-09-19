package com.geziwulian.geziandroid.fragment.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.customs.CustomImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/9/18.
 */
public class MineIntegralAdapter extends RecyclerView.Adapter<MineIntegralAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    public MineIntegralAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private List<String> list = new ArrayList<>();

    public void addData(List<String> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mine_integral_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mine_integral_goods_image)
        CustomImageView mImage;
        @BindView(R.id.mine_integral_goods_title)
        TextView mTextTitle;
        @BindView(R.id.mine_integral_time)
        TextView mTextTime;
        @BindView(R.id.mine_integral_price)
        TextView mTextPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
