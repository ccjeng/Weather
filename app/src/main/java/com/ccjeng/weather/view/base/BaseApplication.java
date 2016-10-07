package com.ccjeng.weather.view.base;

import android.app.Application;

import com.ccjeng.weather.BuildConfig;
import com.ccjeng.weather.R;

import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by andycheng on 2016/9/8.
 */
public class BaseApplication extends Application {
    public static final boolean APPDEBUG = BuildConfig.DEBUG;
    public static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        //Realm.setDefaultConfiguration(realmConfiguration);
    }
}
