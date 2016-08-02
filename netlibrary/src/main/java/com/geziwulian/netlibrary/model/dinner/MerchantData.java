package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-21.
 */
public class MerchantData {

    public int id;
    public String title;
    public String category_id;
    public String area_id ;
    public String address;
    public double longtitude;
    public double latitude;
    public String phone;
    public String default_image_url;
    public String score;
    public int consumption;

    public MerchantData(int id, String title, String category_id, String area_id, String address, double longtitude, double latitude, String phone, String default_image_url, String score, int consumption) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
        this.area_id = area_id;
        this.address = address;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.phone = phone;
        this.default_image_url = default_image_url;
        this.score = score;
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "MerchantData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category_id='" + category_id + '\'' +
                ", area_id='" + area_id + '\'' +
                ", address='" + address + '\'' +
                ", longtitude=" + longtitude +
                ", latitude=" + latitude +
                ", phone='" + phone + '\'' +
                ", default_image_url='" + default_image_url + '\'' +
                ", score='" + score + '\'' +
                ", consumption=" + consumption +
                '}';
    }
}
