package com.geziwulian.geziandroid.fragment.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.AddressData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/9/20.
 */
public class HomeAddresseeAdapter extends RecyclerView.Adapter<HomeAddresseeAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<AddressData> list = new ArrayList<>();
    private int num =-1;
    public HomeAddresseeAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<AddressData> data, int postion){
        list.clear();
        list.addAll(data);
        num = -1;
        num = postion;
        notifyDataSetChanged();
    }
    public void inputPostion(int postion){
        num = -1;
        num = postion;
        notifyDataSetChanged();
    }
    //home_sender_address_menger_item_layout 跟HomeSenderAdapter公用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_sender_address_menger_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextAddressInfo.setText(list.get(position).getAddress());
        holder.mTextPhone.setText(list.get(position).getPhone());
        holder.mTextUserName.setText(list.get(position).getName());
        if (onClickItemListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListener.onClickItem(holder.itemView,position);
                }
            });
        }

        if (onClickEditListener != null){
            holder.mTextEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickEditListener.onClickEdit(holder.mTextEdit,position);
                }
            });
        }

        if (onClickDeteleListener != null){
            holder.mTextDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickDeteleListener.onClickDetele(holder.mTextDelete,position);
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

    //编辑地址
    public interface OnClickEditListener{
        void onClickEdit(View view ,int postion);
    }

    public OnClickEditListener onClickEditListener;

    public void setOnClickEditListener (OnClickEditListener onClickEditListener){
        this.onClickEditListener = onClickEditListener;
    }
    //地址删除

    public interface OnClickDeteleListener{
        void onClickDetele(View view,int postion);
    }

    public OnClickDeteleListener onClickDeteleListener;

    public void setOnClickDeteleListener(OnClickDeteleListener onClickDeteleListener){
        this.onClickDeteleListener = onClickDeteleListener;
    }
}
