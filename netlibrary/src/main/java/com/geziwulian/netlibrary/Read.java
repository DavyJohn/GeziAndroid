package com.geziwulian.netlibrary;

/**
 * Created by zzh on 16-4-28.
 */
public class Read {

    public int id;
    public String body;

    public Read(int id, String body) {
        this.id = id;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Read{" +
                "id=" + id +
                ", body='" + body + '\'' +
                '}';
    }
}
