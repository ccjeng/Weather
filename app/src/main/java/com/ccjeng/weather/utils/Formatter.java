package com.ccjeng.weather.utils;

import android.content.Context;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by andycheng on 2016/9/11.
 */
public class Formatter {


    public static String formatTemperature(Double value, boolean celsius) {
        if (!celsius) {
            value = value * 1.8 + 32;
        }
        value = Math.round(value * 100.0) / 100.0;

        return DoubleToString(value);
    }


    public static String formatTimeToString(String value, Context context) {
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(context);
        return timeFormat.format(value);
    }

    public static String formatTimeWithDayIfNotToday(Context context, long timeInMillis) {
        Calendar now = Calendar.getInstance();
        Calendar lastCheckedCal = new GregorianCalendar();
        lastCheckedCal.setTimeInMillis(timeInMillis);
        Date lastCheckedDate = new Date(timeInMillis);
        String timeFormat = android.text.format.DateFormat.getTimeFormat(context).format(lastCheckedDate);
        if (now.get(Calendar.YEAR) == lastCheckedCal.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == lastCheckedCal.get(Calendar.DAY_OF_YEAR)) {
            // Same day, only show time
            return timeFormat;
        } else {
            return android.text.format.DateFormat.getDateFormat(context).format(lastCheckedDate) + " " + timeFormat;
        }
    }


    public static String DoubleToString(Double value) {
        return String.valueOf(new DecimalFormat("#.0").format(value));
    }


    public static String getWeekNameLocate(Integer timeStamp) {

        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date(timeStamp * 1000L));

    }

    public static String getWindBearingString(Double windBearing) {
        if (windBearing == null) {
            return "-";
        }
        if (windBearing <= 45 || windBearing > 315) {
            return "SOUTH";
        } else if (windBearing > 45 && windBearing <= 135) {
            return "WEST";
        } else if (windBearing > 135 && windBearing <= 225) {
            return "NORTH";
        } else if (windBearing > 225 && windBearing <= 315) {
            return "EAST";
        } else {
            return "WIND";
        }
    }
}
