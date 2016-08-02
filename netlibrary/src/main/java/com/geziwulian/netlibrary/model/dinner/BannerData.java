package com.geziwulian.netlibrary.model.dinner;

import com.geziwulian.netlibrary.model.ActionData;

/**
 * Created by zzh on 16-3-29.
 * 轮播图
 */
public class BannerData {
    public  int id;
    public  String image;
    public  String title;
    public  String image_url;
    public  ActionData action;
    public  String weight;

    public BannerData(int id, String image, String title, String image_url, ActionData action, String weight) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.image_url = image_url;
        this.action = action;
        this.weight = weight;
    }

    public ActionData getAction() {
        return action;
    }

    public void setAction(ActionData action) {
        this.action = action;
    }
}
