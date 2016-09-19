package com.geziwulian.geziandroid.fragment.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.customs.CustomImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/9/16.
 */
public class MineIntegralMallAdapter extends RecyclerView.Adapter<MineIntegralMallAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> data = new ArrayList<>();
    public MineIntegralMallAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<String> list){
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public MineIntegralMallAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mine_integral_mall_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MineIntegralMallAdapter.ViewHolder holder, final int position) {
        if (onClickItemListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListener.OnClickItem(holder.itemView,position);
                }
            });
        }

        if (onClickBtnListener != null){
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickBtnListener.OnClickBtn(holder.mButton,position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mine_integral_mall_goods_btn)
        Button mButton;
        @BindView(R.id.mine_integral_mall_goods_number)
        TextView mTextGoodsNumber;
        @BindView(R.id.mine_integral_mall_goods_title)
        TextView mTextGoodsTitle;
        @BindView(R.id.mine_integral_mall_goods_image)
        CustomImageView mImageGoods;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnClickItemListener{
        void OnClickItem(View view,int postion);
    }

    public OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }

    public interface OnClickBtnListener{
        void OnClickBtn(View view,int postion);
    }

    public OnClickBtnListener onClickBtnListener;

    public void setOnClickBtnListener(OnClickBtnListener onClickBtnListener){
        this.onClickBtnListener = onClickBtnListener;
    }
}
