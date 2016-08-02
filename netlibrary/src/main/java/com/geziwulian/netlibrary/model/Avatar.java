package com.geziwulian.netlibrary.model;

/**
 * Created by zzh on 16-4-8.
 */
public class Avatar {
    private int id;
    private String avatar;

    public Avatar(int id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
