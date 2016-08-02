package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-5.
 */
public class CtCommentDet {
    public int id;
    public int user_id;
    public int merchant_id;
    public int order_id;
    public int ambiance;
    public int service;
    public int flavor;
    public String message;
    public String created_at;
    public String updated_at;
    public User user;

    public CtCommentDet(int id, int user_id, int merchant_id, int order_id, int ambiance, int service, int flavor, String message, String created_at, String updated_at, User user) {
        this.id = id;
        this.user_id = user_id;
        this.merchant_id = merchant_id;
        this.order_id = order_id;
        this.ambiance = ambiance;
        this.service = service;
        this.flavor = flavor;
        this.message = message;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
    }

    @Override
    public String toString() {
        return "CtCommentDet{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", merchant_id=" + merchant_id +
                ", order_id=" + order_id +
                ", ambiance=" + ambiance +
                ", service=" + service +
                ", flavor=" + flavor +
                ", message='" + message + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user=" + user +
                '}';
    }



    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
