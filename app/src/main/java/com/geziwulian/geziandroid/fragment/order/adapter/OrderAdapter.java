package com.geziwulian.geziandroid.fragment.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/3.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<String> items = new ArrayList<String>();
    private Context context;
    private LayoutInflater inflater;
    public OrderAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addItem(List<String> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItemTitle.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mItemTitle;
        @BindView(R.id.image_delete)
        public ImageView iSchedule;

        public View vBackground; // 背景
        public View vItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemTitle = (TextView) itemView.findViewById(R.id.item_title);
            vBackground = itemView.findViewById(R.id.linear_background);
            vItem = itemView.findViewById(R.id.content);
            iSchedule.setImageResource(R.drawable.delete_icon);
        }
    }
}
