package com.geziwulian.netlibrary.model;

/**
 * Created by zzh on 16-4-8.
 */
public class Me {

    public int id;
    public String username;
    public String name;
    public String avatar;

    public Me(int id, String username, String name, String avatar) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Me{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
