package com.geziwulian.geziandroid.utils;

/**
 * Created by 志浩 on 2016/8/3.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
