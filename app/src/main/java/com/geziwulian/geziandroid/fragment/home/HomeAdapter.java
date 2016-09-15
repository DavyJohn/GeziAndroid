package com.geziwulian.geziandroid.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.geziwulian.geziandroid.utils.DisplayUtil;

/**
 * Created by 志浩 on 2016/8/2.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private int SCRRENT_WIDTH;

    private Context context;
    public HomeAdapter(Context context){
        this.context = context;
        SCRRENT_WIDTH = DisplayUtil.getScreenWidthPx(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
