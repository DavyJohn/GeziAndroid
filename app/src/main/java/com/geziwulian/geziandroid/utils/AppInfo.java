package com.geziwulian.geziandroid.utils;

import android.graphics.drawable.Drawable;

/**
 * Created by 志浩 on 2016/8/3.
 */
public class AppInfo {
    private String appName;
    private String packageName;
    private String versionName;
    private int versionCode;
    private Drawable icon;

    public AppInfo() {
    }

    public AppInfo(String appName, Drawable icon, int versionCode, String versionName, String packageName) {
        this.appName = appName;
        this.icon = icon;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
