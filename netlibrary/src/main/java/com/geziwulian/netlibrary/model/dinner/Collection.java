package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-1.
 */
public class Collection {

    public int merchant_id;
    public int user_id;

    public Collection(int merchant_id, int user_id) {
        this.merchant_id = merchant_id;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "merchant_id=" + merchant_id +
                ", user_id=" + user_id +
                '}';
    }
}
