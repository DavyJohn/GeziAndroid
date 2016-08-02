package com.geziwulian.netlibrary.model.dinner;

/**
 * Created by zzh on 16-4-13.
 */
public class OrderListData {
    public String next_page_url;
    public int id;
    public int status;
    public String name;
    public int user_id;
    public String type;
    public String message;
    public String created_at;
    public String status_text;
    public int merchant_id;
    public String merchant_name;
    public String mobile;
    public boolean need_box;
    public boolean box_hall;
    public int people_number;
    public String dinner_time;
    public Merchant merchant;

    public OrderListData(String next_page_url, int id, int status, String name, int user_id, String type, String message, String created_at, String status_text, int merchant_id, String merchant_name, String mobile, boolean need_box, boolean box_hall, int people_number, String dinner_time, Merchant merchant) {
        this.next_page_url = next_page_url;
        this.id = id;
        this.status = status;
        this.name = name;
        this.user_id = user_id;
        this.type = type;
        this.message = message;
        this.created_at = created_at;
        this.status_text = status_text;
        this.merchant_id = merchant_id;
        this.merchant_name = merchant_name;
        this.mobile = mobile;
        this.need_box = need_box;
        this.box_hall = box_hall;
        this.people_number = people_number;
        this.dinner_time = dinner_time;
        this.merchant = merchant;
    }

    @Override
    public String toString() {
        return "OrderListData{" +
                "next_page_url='" + next_page_url + '\'' +
                ", id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", created_at='" + created_at + '\'' +
                ", status_text='" + status_text + '\'' +
                ", merchant_id=" + merchant_id +
                ", merchant_name='" + merchant_name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", need_box=" + need_box +
                ", box_hall=" + box_hall +
                ", people_number=" + people_number +
                ", dinner_time='" + dinner_time + '\'' +
                ", merchant=" + merchant +
                '}';
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }


}
