package com.geziwulian.netlibrary.model.dinner;

import java.util.LinkedHashMap;

/**
 * Created by zzh on 16-4-4.
 */
public class Px {

    public String key;
    public String title;
    public LinkedHashMap<String,String> values;

    public Px(String key, String title, LinkedHashMap<String,String> values) {
        this.key = key;
        this.title = title;
        this.values = values;
    }

    @Override
    public String toString() {
        return "Px{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", values=" + values +
                '}';
    }
}
