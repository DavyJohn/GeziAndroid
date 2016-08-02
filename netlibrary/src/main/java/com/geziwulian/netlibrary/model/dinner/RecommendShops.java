package com.geziwulian.netlibrary.model.dinner;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zzh on 16-3-29.
 */
public class RecommendShops extends RealmObject{
    @PrimaryKey
    public int id;
    public String title;
    public String default_image_url;
    public int category_id;
    public int area_id;
    public String address;
    public String longtitude;
    public String latitude;
    public String phone;
    public Double score;
    public Integer consumption;
    public String hours;
    @Ignore
    public List<String> images;

    public RecommendShops(int id, String title, String default_image_url, int category_id, int area_id, String address, String longtitude, String latitude, String phone, Double score, Integer consumption, String hours, List<String> images) {
        this.id = id;
        this.title = title;
        this.default_image_url = default_image_url;
        this.category_id = category_id;
        this.area_id = area_id;
        this.address = address;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.phone = phone;
        this.score = score;
        this.consumption = consumption;
        this.hours = hours;
        this.images = images;
    }

    public RecommendShops() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefault_image_url() {
        return default_image_url;
    }

    public void setDefault_image_url(String default_image_url) {
        this.default_image_url = default_image_url;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "RecommendShops{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", default_image_url='" + default_image_url + '\'' +
                ", category_id=" + category_id +
                ", area_id=" + area_id +
                ", address='" + address + '\'' +
                ", longtitude='" + longtitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                ", consumption=" + consumption +
                ", hours='" + hours + '\'' +
                ", images=" + images +
                '}';
    }
}
