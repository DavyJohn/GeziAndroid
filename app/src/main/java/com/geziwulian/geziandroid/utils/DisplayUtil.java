package com.geziwulian.geziandroid.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by 志浩 on 2016/8/2.
 */
public class DisplayUtil {
    public static int getScreenWidthPx(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    public static int getScreentWidthDp(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metric);
        int pxValue = metric.widthPixels;
        float scale = metric.density;
        return (int)(pxValue/scale + 0.5f);
    }

    public static int getScreenHeightDp(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metric);
        int pxValue = metric.heightPixels;
        float scale = metric.density;
        return (int)(pxValue/scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
