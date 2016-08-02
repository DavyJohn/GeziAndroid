package com.geziwulian.netlibrary.model.dinner;

import java.util.List;

/**
 * Created by zzh on 16-4-21.
 */
public class AllCollection {

    public List<AllCollectionData>  data;
    public String next_page_url;
    public int last_page;
    public String prev_page_url;
    public int current_page;
    public int total;
    public int from;
    public int to;

    public AllCollection(List<AllCollectionData> data, String next_page_url, int last_page, String prev_page_url, int current_page, int total, int from, int to) {
        this.data = data;
        this.next_page_url = next_page_url;
        this.last_page = last_page;
        this.prev_page_url = prev_page_url;
        this.current_page = current_page;
        this.total = total;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "AllCollection{" +
                "data=" + data +
                ", next_page_url='" + next_page_url + '\'' +
                ", last_page=" + last_page +
                ", prev_page_url='" + prev_page_url + '\'' +
                ", current_page=" + current_page +
                ", total=" + total +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
