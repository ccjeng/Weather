package com.ccjeng.weather.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.ccjeng.weather.R;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.weather_icons_typeface_library.WeatherIcons;

/**
 * Created by andycheng on 2016/9/10.
 */
public class IconManager {

    public static IconicsDrawable getIconResource(String icon, Context context) {

        switch (icon) {
            case "rain":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_rain);
            case "clear-day":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_day_sunny);
            case "clear-night":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_night_clear);
            case "snow":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_snow);
            case "sleet":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_sleet);
            case "wind":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_cloudy_windy);
            case "fog":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_fog);
            case "cloudy":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_cloud);
            case "partly-cloudy-day":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_day_cloudy);
            case "partly-cloudy-night":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_night_cloudy);
            case "hail":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_hail);
            case "thunderstorm":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_thunderstorm);
            case "tornado":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_tornado);
            default:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_day_sunny);
        }
    }


    public static int getIconColor(String icon, Context context) {


        switch (icon) {
            case "rain":
                return ContextCompat.getColor(context, R.color.rain);
            case "clear-day":
                return ContextCompat.getColor(context, R.color.clear_day);
            case "clear-night":
                return ContextCompat.getColor(context, R.color.clear_night);
            case "snow":
                return ContextCompat.getColor(context, R.color.snow);
            case "sleet":
                return ContextCompat.getColor(context, R.color.sleet);
            case "wind":
                return ContextCompat.getColor(context, R.color.wind);
            case "fog":
                return ContextCompat.getColor(context, R.color.fog);
            case "cloudy":
                return ContextCompat.getColor(context, R.color.cloudy);
            case "partly-cloudy-day":
                return ContextCompat.getColor(context, R.color.partly_cloudy_day);
            case "partly-cloudy-night":
                return ContextCompat.getColor(context, R.color.partly_cloudy_night);
            case "hail":
                return ContextCompat.getColor(context, R.color.hail);
            case "thunderstorm":
                return ContextCompat.getColor(context, R.color.thunderstorm);
            case "tornado":
                return ContextCompat.getColor(context, R.color.tornado);
            default:
                return ContextCompat.getColor(context, R.color.clear_day);
        }
    }
}
