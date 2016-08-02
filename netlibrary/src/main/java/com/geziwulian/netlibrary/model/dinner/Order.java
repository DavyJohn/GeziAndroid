package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-5.
 */
public class Order {

    public int id;
    public int status;
    public String  name;
    public String mobile;
    public String message;
    public String created_at;
    public boolean need_box;
    public boolean box_hall;
    public int peop_number;
    public String dinner_time;

    public Order(int id, int status, String name, String mobile, String message, String created_at, boolean need_box, boolean box_hall, int peop_number, String dinner_time) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.mobile = mobile;
        this.message = message;
        this.created_at = created_at;
        this.need_box = need_box;
        this.box_hall = box_hall;
        this.peop_number = peop_number;
        this.dinner_time = dinner_time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", message='" + message + '\'' +
                ", created_at='" + created_at + '\'' +
                ", need_box=" + need_box +
                ", box_hall=" + box_hall +
                ", peop_number=" + peop_number +
                ", dinner_time='" + dinner_time + '\'' +
                '}';
    }
}
