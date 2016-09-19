package com.geziwulian.geziandroid.fragment.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/9/19.
 */
public class HomeSenderAdapter extends RecyclerView.Adapter<HomeSenderAdapter.ViewHolder> {
    private List<String> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private int num =-1;
    public HomeSenderAdapter(Context context){
        this.context= context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<String> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void inputPostion(int postion){
        num = -1;
        num = postion;
        notifyDataSetChanged();
    }
    @Override
    public HomeSenderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_sender_address_menger_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HomeSenderAdapter.ViewHolder holder, final int position) {
        if (onClickItemListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListener.onClickItem(holder.itemView,position);
                }
            });
        }

        if (position == num){
            holder.mTextSelected.setVisibility(View.VISIBLE);
        }else {
            holder.mTextSelected.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_sender_address_menger_listitem_username)
        TextView mTextUserName;
        @BindView(R.id.home_sender_address_menger_listitem_phone)
        TextView mTextPhone;
        @BindView(R.id.home_sender_address_menger_listitem_address)
        TextView mTextAddressInfo;
        @BindView(R.id.home_sender_address_menger_listitem_edit)
        TextView mTextEdit;
        @BindView(R.id.home_sender_address_menger_listitem_delete)
        TextView mTextDelete;
        @BindView(R.id.home_sender_address_menger_selected)
        TextView mTextSelected;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnClickItemListeren{
         void onClickItem(View view,int postion);
    }

    public OnClickItemListeren onClickItemListener;

    public void setOnClickItemListener (OnClickItemListeren onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }
}
