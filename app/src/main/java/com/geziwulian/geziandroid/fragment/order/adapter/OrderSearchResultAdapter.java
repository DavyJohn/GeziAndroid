package com.geziwulian.geziandroid.fragment.order.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
 * Created by 志浩 on 2016/9/19.
 */
public class OrderSearchResultAdapter extends RecyclerView.Adapter<OrderSearchResultAdapter.ViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();
    private LayoutInflater inflater;
    public OrderSearchResultAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<String> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public OrderSearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.order_search_result_item_layout,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderSearchResultAdapter.ViewHolder holder, int position) {
        if (list.size() != 0 && position == 0){
            holder.mTextData.setTextColor(ContextCompat.getColor(context,R.color.maincolor));
            holder.mTextTime.setTextColor(ContextCompat.getColor(context,R.color.maincolor));
            holder.view.setBackgroundColor(ContextCompat.getColor(context,R.color.maincolor));
        }else if (position == list.size()){
            //TODO
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_search_result_item_view)
        View view;
        @BindView(R.id.order_search_result_text_data)
        TextView mTextData;
        @BindView(R.id.order_search_result_text_time)
        TextView mTextTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
