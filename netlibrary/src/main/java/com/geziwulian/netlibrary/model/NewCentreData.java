package com.geziwulian.netlibrary.model;

/**
 * Created by zzh on 16-4-25.
 */
public class NewCentreData {

    public int id;
    public String body;
    public String read_at;
    public ActionData detail;

    public NewCentreData(ActionData detail, String read_at, String body, int id) {
        this.detail = detail;
        this.read_at = read_at;
        this.body = body;
        this.id = id;
    }

    public ActionData getDetail() {
        return detail;
    }

    public void setDetail(ActionData detail) {
        this.detail = detail;
    }
}
