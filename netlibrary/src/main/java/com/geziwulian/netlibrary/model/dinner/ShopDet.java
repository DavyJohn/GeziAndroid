package com.geziwulian.netlibrary.model.dinner;

import java.util.List;

/**
 * Created by zzh on 16-3-30.
 * 单独商家详情
 */
public class ShopDet {
    public int id;
    public String title;
    public Double score;
    public String default_image_url;
    public int category_id;
    public String address;
    public String phone;
    public int consumption;
    public double ambiance;
    public double service;
    public double flavor;
    public String special_offer;
    public String hours;
    public double longtitude;
    public double latitude;

    //类别
    public Category category;
    //菜单
    public List<Dishes> dishes;

    public ShopDet(int id, String title, Double score, String default_image_url, int category_id, String address, String phone, int consumption, double ambiance, double service, double flavor, String special_offer, String hours, double longtitude, double latitude, Category category, List<Dishes> dishes) {
        this.id = id;
        this.title = title;
        this.score = score;
        this.default_image_url = default_image_url;
        this.category_id = category_id;
        this.address = address;
        this.phone = phone;
        this.consumption = consumption;
        this.ambiance = ambiance;
        this.service = service;
        this.flavor = flavor;
        this.special_offer = special_offer;
        this.hours = hours;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.category = category;
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "ShopDet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", default_image_url='" + default_image_url + '\'' +
                ", category_id=" + category_id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", consumption=" + consumption +
                ", ambiance=" + ambiance +
                ", service=" + service +
                ", flavor=" + flavor +
                ", special_offer='" + special_offer + '\'' +
                ", hours='" + hours + '\'' +
                ", longtitude=" + longtitude +
                ", latitude=" + latitude +
                ", category=" + category +
                ", dishes=" + dishes +
                '}';
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }
}
