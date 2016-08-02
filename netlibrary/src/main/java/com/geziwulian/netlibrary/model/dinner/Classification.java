package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-3-30.
 */
public class Classification {

    public int id;
    public String title;

    public Classification(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
