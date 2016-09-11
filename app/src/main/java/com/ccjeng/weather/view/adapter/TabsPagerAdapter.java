package com.ccjeng.weather.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.utils.WeatherPagerEnum;
import com.ccjeng.weather.view.activity.TabFragment;

/**
 * Created by andycheng on 2016/9/11.
 */
public class TabsPagerAdapter  extends FragmentPagerAdapter {

    private Context context;
    private City city;

    public TabsPagerAdapter(FragmentManager fm, Context context, City city) {
        super(fm);
        this.context = context;
        this.city = city;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(position, city);
    }

    @Override
    public int getCount() {
        return WeatherPagerEnum.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        WeatherPagerEnum customPagerEnum = WeatherPagerEnum.values()[position];
        return context.getString(customPagerEnum.getTitleResId());
    }


}