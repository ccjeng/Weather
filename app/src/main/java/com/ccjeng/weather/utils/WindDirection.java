package com.ccjeng.weather.utils;

import android.content.Context;

import com.ccjeng.weather.R;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.weather_icons_typeface_library.WeatherIcons;

/**
 * Created by andycheng on 2016/9/25.
 */

public enum WindDirection {

    // don't change order
    NORTH, NORTH_NORTH_EAST, NORTH_EAST, EAST_NORTH_EAST,
    EAST, EAST_SOUTH_EAST, SOUTH_EAST, SOUTH_SOUTH_EAST,
    SOUTH, SOUTH_SOUTH_WEST, SOUTH_WEST, WEST_SOUTH_WEST,
    WEST, WEST_NORTH_WEST, NORTH_WEST, NORTH_NORTH_WEST;

    public static WindDirection byDegree(double degree) {
        return byDegree(degree, WindDirection.values().length);
    }

    public static WindDirection byDegree(double degree, int numberOfDirections) {
        WindDirection[] directions = WindDirection.values();
        int availableNumberOfDirections = directions.length;

        int direction = windDirectionDegreeToIndex(degree, numberOfDirections)
                * availableNumberOfDirections / numberOfDirections;

        return directions[direction];
    }

    public String getLocalizedString(Context context) {
        // usage of enum.ordinal() is not recommended, but whatever
        return context.getResources().getStringArray(R.array.windDirections)[ordinal()];
    }

    public static IconicsDrawable getArrow(Context context, WindDirection windDirection) {

        switch(windDirection) {
            case NORTH:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_up);
            case NORTH_NORTH_EAST:
            case NORTH_EAST:
            case EAST_NORTH_EAST:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_up_right);
            case EAST:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_right);
            case EAST_SOUTH_EAST:
            case SOUTH_EAST:
            case SOUTH_SOUTH_EAST:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_down_right);
            case SOUTH:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_down);
            case SOUTH_SOUTH_WEST:
            case SOUTH_WEST:
            case WEST_SOUTH_WEST:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_down_left);
            case WEST:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_left);
            case WEST_NORTH_WEST:
            case NORTH_WEST:
            case NORTH_NORTH_WEST:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_up_left);
            default:
                return new IconicsDrawable(context, WeatherIcons.Icon.wic_direction_up);
        }
    }

    // you may use values like 4, 8, etc. for numberOfDirections
    public static int windDirectionDegreeToIndex(double degree, int numberOfDirections) {
        // to be on the safe side
        degree %= 360;
        if(degree < 0) degree += 360;

        degree += 180 / numberOfDirections; // add offset to make North start from 0

        int direction = (int)Math.floor(degree * numberOfDirections / 360);

        return direction % numberOfDirections;
    }


}
