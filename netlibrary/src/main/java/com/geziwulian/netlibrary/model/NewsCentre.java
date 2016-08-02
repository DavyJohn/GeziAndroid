package com.geziwulian.netlibrary.model;

import java.util.List;

/**
 * Created by zzh on 16-4-25.
 */
public class NewsCentre {
    public int current_page;
    public String next_page_url;
    public List<NewCentreData> data;

    public NewsCentre(int current_page, String next_page_url, List<NewCentreData> data) {
        this.current_page = current_page;
        this.next_page_url = next_page_url;
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsCentre{" +
                "current_page=" + current_page +
                ", next_page_url='" + next_page_url + '\'' +
                ", data=" + data +
                '}';
    }
}
