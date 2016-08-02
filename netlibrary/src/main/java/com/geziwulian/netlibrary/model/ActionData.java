package com.geziwulian.netlibrary.model;

/**
 * Created by zzh on 16-4-7.
 */
public class ActionData {

    public String type;
    public String target;

    public ActionData(String type, String target) {
        this.type = type;
        this.target = target;
    }

    @Override
    public String toString() {
        return "ActionData{" +
                "type='" + type + '\'' +
                ", target=" + target +
                '}';
    }
}
