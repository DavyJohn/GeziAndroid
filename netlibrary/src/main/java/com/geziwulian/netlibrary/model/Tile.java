package com.geziwulian.netlibrary.model;

/**
 * Created by zzh on 16-4-7.
 */
public class Tile {
    public int id;
    public String title;
    public String image_url;
    public int weight;
    public ActionData action;
    public String deleted_at;

    public Tile(String deleted_at, int id, String title, String image_url, int weight, ActionData action) {
        this.deleted_at = deleted_at;
        this.id = id;
        this.title = title;
        this.image_url = image_url;
        this.weight = weight;
        this.action = action;
    }

    public ActionData getAction() {
        return action;
    }

    public void setAction(ActionData action) {
        this.action = action;
    }
}
