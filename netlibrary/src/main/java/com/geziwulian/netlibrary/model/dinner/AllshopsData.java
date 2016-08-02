package com.geziwulian.netlibrary.model.dinner;

import java.util.ArrayList;

/**
 * Created by zzh on 16-3-30.
 * 所有商店的详情
 */
public class AllshopsData {
    public int id;
    public String title;
    public int category_id;
    public String address;
    public double longtitude;
    public double latitude;
    public String phone;
    public String ambiance;
    public String service;
    public String flavor;
    public int area_id;
    public int consumption;//消费
    public String special_offer;//特别优惠
    public Double score;
    public String default_image_url;
    public String hours;
    public ArrayList<String> images;

    public AllshopsData(int id, String title, int category_id, String address, double longtitude, double latitude, String phone, String ambiance, String service, String flavor, int area_id, int consumption, String special_offer, Double score, String default_image_url, String hours, ArrayList<String> images) {
        this.id = id;
        this.title = title;
        this.category_id = category_id;
        this.address = address;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.phone = phone;
        this.ambiance = ambiance;
        this.service = service;
        this.flavor = flavor;
        this.area_id = area_id;
        this.consumption = consumption;
        this.special_offer = special_offer;
        this.score = score;
        this.default_image_url = default_image_url;
        this.hours = hours;
        this.images = images;
    }

    @Override
    public String toString() {
        return "AllshopsData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category_id=" + category_id +
                ", address='" + address + '\'' +
                ", longtitude=" + longtitude +
                ", latitude=" + latitude +
                ", phone='" + phone + '\'' +
                ", ambiance='" + ambiance + '\'' +
                ", service='" + service + '\'' +
                ", flavor='" + flavor + '\'' +
                ", area_id=" + area_id +
                ", consumption=" + consumption +
                ", special_offer='" + special_offer + '\'' +
                ", score=" + score +
                ", default_image_url='" + default_image_url + '\'' +
                ", hours='" + hours + '\'' +
                ", images=" + images +
                '}';
    }
}
