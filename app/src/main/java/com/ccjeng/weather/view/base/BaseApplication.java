package com.ccjeng.weather.view.base;

import android.app.Application;

import com.ccjeng.weather.BuildConfig;

import io.realm.RealmConfiguration;

/**
 * Created by andycheng on 2016/9/8.
 */
public class BaseApplication extends Application {
    public static final boolean APPDEBUG = BuildConfig.DEBUG;
    public static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        //Realm.setDefaultConfiguration(realmConfiguration);
    }
}
