package com.geziwulian.geziandroid.fragment.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.DisplayUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/2.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> listName = new ArrayList<>();
    private List<Integer> listIcon = new ArrayList<>();
    private int num = -1 ;
    public HomeAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void  addData(List<String> data1 ,List<Integer> data2){
        listIcon.clear();
        listName.clear();
        listIcon.addAll(data2);
        listName.addAll(data1);
        notifyDataSetChanged();
    }

    public void inputPostion(int postion){
        num = -1;//初始化
        num = postion;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.home_fargment_company_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (onClickItemListener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemListener.OnClicItem(holder.itemView,position);
                }
            });
        }
        Picasso.with(context).load(listIcon.get(position)).into(holder.mImage);
        holder.mTextName.setText(listName.get(position));
        if (position == num){
            holder.mCb.setChecked(true);
        }else {
            holder.mCb.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_fragment_company_checkbox)
        CheckBox mCb;
        @BindView(R.id.home_fragment_company_name)
        TextView mTextName;
        @BindView(R.id.home_fragment_company_image)
        ImageView mImage;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnClickItemListener{
        void OnClicItem(View view,int postion);
    }

    public OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }
}
