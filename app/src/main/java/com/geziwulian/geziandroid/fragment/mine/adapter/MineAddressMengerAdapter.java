package com.geziwulian.geziandroid.fragment.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/9/14.
 */
public class MineAddressMengerAdapter extends RecyclerView.Adapter<MineAddressMengerAdapter.ViewHolder> {
    private List<String> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public MineAddressMengerAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void  addData(List<String> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mine_address_menger_listitem_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (onClickItemListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListener.OnClickItem(holder.itemView,position);
                }
            });
        }

        if (onClickEditListener != null){
            holder.mTextEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickEditListener.OnClickEdit(holder.mTextEdit,position);
                }
            });
        }

        if (onClickDeleteListener != null){
            holder.mTextDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickDeleteListener.OnClickDelete(holder.mTextDelete,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mine_address_menger_listitem_username)
        TextView mTextUser;
        @BindView(R.id.mine_address_menger_listitem_address)
        TextView mTextAddress;
        @BindView(R.id.mine_address_menger_listitem_phone)
        TextView mTextPhone;
        @BindView(R.id.mine_address_menger_listitem_edit)
        TextView mTextEdit;
        @BindView(R.id.mine_address_menger_listitem_delete)
        TextView mTextDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
//对item的监听
    public interface OnClickItemListener{
        void OnClickItem(View view,int postion);
    }

    public OnClickItemListener onClickItemListener;

    public void setOnClickItemListener (OnClickItemListener onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }
//对编辑的监听
    public interface OnClickEditListener{
    void OnClickEdit(View view,int postion);
}

    public OnClickEditListener onClickEditListener;

    public void setOnClickEditListener (OnClickEditListener onClickEditListener){
        this.onClickEditListener = onClickEditListener;
    }
//对删除的监听
public interface OnClickDeleteListener{
    void OnClickDelete(View view,int postion);
}

    public OnClickDeleteListener onClickDeleteListener;

    public void setOnClickDeleteListener (OnClickDeleteListener onClickDeleteListener){
        this.onClickDeleteListener = onClickDeleteListener;
    }
}


