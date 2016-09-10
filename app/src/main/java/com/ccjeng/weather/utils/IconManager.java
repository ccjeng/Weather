package com.ccjeng.weather.utils;

import android.content.Context;

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
}
