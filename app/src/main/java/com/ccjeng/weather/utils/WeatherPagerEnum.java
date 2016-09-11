package com.ccjeng.weather.utils;

import com.ccjeng.weather.R;

/**
 * Created by andycheng on 2016/7/29.
 */
public enum WeatherPagerEnum {

    CURRENT(R.string.tab_current, R.layout.view_weather_current),
    HOUR(R.string.tab_hours, R.layout.view_weather_hours),
    DAY(R.string.tab_days, R.layout.view_weather_days);

    private int mTitleResId;
    private int mLayoutResId;

    WeatherPagerEnum(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
