package com.geziwulian.netlibrary.model;

/**
 * Created by yyx on 16/3/4.
 */
public class Demo {

    public String id;

    public String name;

    public String age;

    public Demo(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
