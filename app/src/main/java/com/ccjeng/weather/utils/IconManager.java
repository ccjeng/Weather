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

    /**
     *
     wi-forecast-io-clear-day: day-sunny
     wi-forecast-io-clear-night: night-clear
     wi-forecast-io-rain: rain
     wi-forecast-io-snow: snow
     wi-forecast-io-sleet: sleet
     wi-forecast-io-wind: strong-wind
     wi-forecast-io-fog: fog
     wi-forecast-io-cloudy: cloudy
     wi-forecast-io-partly-cloudy-day: day-cloudy
     wi-forecast-io-partly-cloudy-night: night-cloudy
     wi-forecast-io-hail: hail
     wi-forecast-io-thunderstorm: thunderstorm
     wi-forecast-io-tornado: tornado
     *
     * */

    public static IconicsDrawable getIconResource(String icon, Context context) {

        switch (icon) {
            case "rain":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_rain);
            case "clear-day":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_clear_day);
            case "clear-night":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_clear_night);
            case "snow":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_snow);
            case "sleet":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_sleet);
            case "wind":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_wind);
            case "fog":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_fog);
            case "cloudy":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_cloudy);
            case "partly-cloudy-day":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_partly_cloudy_day);
            case "partly-cloudy-night":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_partly_cloudy_night);
            case "hail":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_hail);
            case "thunderstorm":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_thunderstorm);
            case "tornado":
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_tornado);
            default:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_forecast_io_clear_day);
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
