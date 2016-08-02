package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-13.
 */
public class OrderDetail {

    public int id;
    public int status;
    public String name;
    public String mobile;
    public int merchant_id;
    public int people_number;
    public String dinner_time;
    public boolean need_box;
    public boolean box_hall;
    public String message;

    public OrderDetail(int id, int status, String name, String mobile, int merchant_id, int people_number, String dinner_time, boolean need_box, boolean box_hall, String message) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.mobile = mobile;
        this.merchant_id = merchant_id;
        this.people_number = people_number;
        this.dinner_time = dinner_time;
        this.need_box = need_box;
        this.box_hall = box_hall;
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", merchant_id=" + merchant_id +
                ", people_number=" + people_number +
                ", dinner_time='" + dinner_time + '\'' +
                ", need_box=" + need_box +
                ", box_hall=" + box_hall +
                ", message='" + message + '\'' +
                '}';
    }
}
