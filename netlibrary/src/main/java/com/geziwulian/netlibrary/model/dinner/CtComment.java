package com.geziwulian.netlibrary.model.dinner;

import java.util.List;

/**
 * Created by zzh on 16-4-5.
 */
public class CtComment {
        public List<CtCommentDet> data;
        public int total;
        public int per_page;
        public int current_page;
        public int last_page;
        public String next_page_url;
        public String prev_page_url;
        public int from;
        public int to;

        public CtComment(List<CtCommentDet> data, int total, int per_page, int current_page, int last_page, String next_page_url, String prev_page_url, int from, int to) {
                this.data = data;
                this.total = total;
                this.per_page = per_page;
                this.current_page = current_page;
                this.last_page = last_page;
                this.next_page_url = next_page_url;
                this.prev_page_url = prev_page_url;
                this.from = from;
                this.to = to;
        }

        @Override
        public String toString() {
                return "CtComment{" +
                        "data=" + data +
                        ", total=" + total +
                        ", per_page=" + per_page +
                        ", current_page=" + current_page +
                        ", last_page=" + last_page +
                        ", next_page_url='" + next_page_url + '\'' +
                        ", prev_page_url='" + prev_page_url + '\'' +
                        ", from=" + from +
                        ", to=" + to +
                        '}';
        }
}
