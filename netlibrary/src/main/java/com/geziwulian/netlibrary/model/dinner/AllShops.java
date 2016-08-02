package com.geziwulian.netlibrary.model.dinner;

import java.util.List;

/**
 * Created by zzh on 16-3-30.
 * 所有商店
 */
public class AllShops {
    public int per_page;//每页多少
    public int current_page;//当前页
    public int last_page;//最后一页
    public String next_page_url;//下一页
    public int from;
    public int to;
    private String prev_page_url;
    public List<AllshopsData> data;

    public AllShops(int per_page, int current_page, int last_page, String next_page_url, int from, int to, String prev_page_url, List<AllshopsData> data) {
        this.per_page = per_page;
        this.current_page = current_page;
        this.last_page = last_page;
        this.next_page_url = next_page_url;
        this.from = from;
        this.to = to;
        this.prev_page_url = prev_page_url;
        this.data = data;
    }

    @Override
    public String toString() {
        return "AllShops{" +
                "per_page=" + per_page +
                ", current_page=" + current_page +
                ", last_page=" + last_page +
                ", next_page_url='" + next_page_url + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", prev_page_url='" + prev_page_url + '\'' +
                ", data=" + data +
                '}';
    }
}
