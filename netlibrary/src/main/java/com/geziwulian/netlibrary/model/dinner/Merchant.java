package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-15.
 */
public class Merchant {
    public int id;
    public String title;
    //    public String images;
    public Double score;
    public String default_image_url;

    public Merchant(int id, String title, Double score, String default_image_url) {
        this.id = id;
        this.title = title;

        this.score = score;
        this.default_image_url = default_image_url;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", default_image_url='" + default_image_url + '\'' +
                '}';
    }


}
