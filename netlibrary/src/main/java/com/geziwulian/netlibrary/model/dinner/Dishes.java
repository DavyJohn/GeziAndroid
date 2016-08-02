package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-3-30.
 * 菜单
 */
public class Dishes {
    public int id;
    public String title;
    public String price;
    public String image;
    public String score;

    public Dishes(int id, String title, String price, String image, String score) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
