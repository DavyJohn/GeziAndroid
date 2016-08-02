package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-3-30.
 *类别
 */
public class Category  {
        public int id;
        public String title;

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
