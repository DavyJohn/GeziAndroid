package com.geziwulian.netlibrary.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yyx on 16/5/5.
 */
public class SearchHistory extends RealmObject {
    public int id;
//    @PrimaryKey
    public String title;
    @Ignore
    public Date date;

    public SearchHistory(int id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public SearchHistory() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
