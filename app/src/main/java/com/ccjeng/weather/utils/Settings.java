package com.ccjeng.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ccjeng.weather.view.activity.SettingsActivity;

/**
 * Created by andycheng on 2016/10/7.
 */

public class Settings {

    public static boolean isCelsiusUnit(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String syncConnPref = sharedPref.getString(SettingsActivity.PREF_UNIT, "celsius");
        if (syncConnPref.equals("celsius")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBackgroundPhoto(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(SettingsActivity.BACKGROUND_PHOTO, false);
    }
}
