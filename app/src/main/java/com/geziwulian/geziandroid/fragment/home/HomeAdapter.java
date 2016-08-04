package com.geziwulian.geziandroid.fragment.home;

import android.content.Context;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.geziwulian.geziandroid.R;
import com.geziwulian.geziandroid.utils.Constant;
import com.geziwulian.geziandroid.utils.DisplayUtil;

import com.geziwulian.netlibrary.model.ActionData;
import com.geziwulian.netlibrary.model.dinner.BannerData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 志浩 on 2016/8/2.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private int SCRRENT_WIDTH;
    private static final int HEADER_ITEM_FLAG = -1;
    private static final int NORMAL_ITEM_FLAG = 1;
    private Context context;
    public HomeAdapter(Context context){
        this.context = context;
        SCRRENT_WIDTH = DisplayUtil.getScreenWidthPx(context);
    }

    private List<BannerData> data = new ArrayList<>();

    private List<String> listData = new ArrayList<>();

    /**轮播*/
    public void addBannerDat(List<BannerData> list){
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return HEADER_ITEM_FLAG;
        }else {
            return NORMAL_ITEM_FLAG;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == HEADER_ITEM_FLAG)
        {
            ConvenientBanner headerBanner = new ConvenientBanner(parent.getContext());
            headerBanner.setId(R.id.headerbanner);
            headerBanner.setCanLoop(true);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(SCRRENT_WIDTH, 9*SCRRENT_WIDTH/16);
            headerBanner.setLayoutParams(params);
            holder = new ViewHolder(headerBanner,HEADER_ITEM_FLAG);
        }else if (viewType == NORMAL_ITEM_FLAG){

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if ( position == 0){
            holder.headerBanner.setPages(
                    new CBViewHolderCreator<LocalImageHolderView>() {
                        @Override
                        public LocalImageHolderView createHolder() {
                            return new LocalImageHolderView();
                        }
                    },data)
                    .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ConvenientBanner headerBanner;
        public ViewHolder(View itemView,int type) {
            super(itemView);
            if (type == HEADER_ITEM_FLAG){
                headerBanner = (ConvenientBanner) itemView.findViewById(R.id.headerbanner);
                if (Constant.IS_CANLOOP == 1){
                    headerBanner.startTurning(3000);
                }
                if (Constant.IS_CANLOOP == 0){
                    headerBanner.stopTurning();
                }

            }
        }

        @Override
        public void onClick(View v) {
            clickBanner((BannerData) v.getTag());
        }
    }

    class LocalImageHolderView implements Holder<BannerData>,View.OnClickListener{
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,SCRRENT_WIDTH*9/16));
            imageView.setOnClickListener(this);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerData data) {
            imageView.setTag(data);
            Picasso.with(context).load(data.image_url).config(Bitmap.Config.RGB_565).resize(SCRRENT_WIDTH,SCRRENT_WIDTH*9/16).into(imageView);
        }

        @Override
        public void onClick(View v) {
            clickBanner((BannerData) v.getTag());
        }
    }

    private void clickBanner(BannerData data){
        ActionData actionData = data.getAction();
        Toast.makeText(context,actionData.type,Toast.LENGTH_SHORT).show();
    }
}
