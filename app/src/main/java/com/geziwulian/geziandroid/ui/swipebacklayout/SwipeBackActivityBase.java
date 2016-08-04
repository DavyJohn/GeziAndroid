package com.geziwulian.geziandroid.ui.swipebacklayout;

/**
 * Created by 志浩 on 2016/8/4.
 */
public interface SwipeBackActivityBase {

    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
