package com.geziwulian.netlibrary.model;

/**
 * Created by zzh on 16-4-8.
 */
public class UpdateName {

    private int id;
    private String name;

    public UpdateName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UpdateName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
