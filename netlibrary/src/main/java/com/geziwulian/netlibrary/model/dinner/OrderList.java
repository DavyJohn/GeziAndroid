package com.geziwulian.netlibrary.model.dinner;

import java.util.List;

/**
 * Created by zzh on 16-4-13.
 */
public class OrderList {
    public int current_page;
    public List<OrderListData> data;
    public String next_page_url;

    public OrderList(int current_page, List<OrderListData> data, String next_page_url) {
        this.current_page = current_page;
        this.data = data;
        this.next_page_url = next_page_url;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "current_page=" + current_page +
                ", data=" + data +
                ", next_page_url='" + next_page_url + '\'' +
                '}';
    }
}
