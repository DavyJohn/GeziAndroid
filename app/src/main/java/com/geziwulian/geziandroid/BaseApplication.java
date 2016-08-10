package com.geziwulian.geziandroid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.geziwulian.netlibrary.HttpClient;
import com.tencent.bugly.crashreport.CrashReport;


import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by yyx on 16/2/24.
 */
public final class BaseApplication extends Application {

    private static Context appContext;
    private static BaseApplication app;
    public BaseApplication(){
        app = this;
    }
    /**
     * Activity集合
     */
    private static ArrayList<BaseActivity> activitys = new ArrayList<BaseActivity>();

    public static synchronized BaseApplication getInstance() {
        if (app == null) {
            app = new BaseApplication();
        }
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(appContext);
        CrashReport.initCrashReport(getAppContext());
        HttpClient.init(appContext);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .name("default.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public Context getAppContext() {
        return appContext;
    }


    /**
     * 添加Activity到ArrayList<Activity>管理集合
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        String className = activity.getClass().getName();
        for (Activity at : activitys) {
            if (className.equals(at.getClass().getName())) {
                activitys.remove(at);
                break;
            }
        }
        activitys.add(activity);
    }

    /**
     * 退出应用程序的时候，手动调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (BaseActivity activity : activitys) {
            activity.defaultFinish();
        }
    }

}
