package com.ccjeng.weather.utils;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

        return DoubleToStringP1(value);
    }


    public static String formatTimeToString(Integer value, Context context) {
        return DateFormat.getTimeFormat(context).format(new Date(value * 1000L));
    }

    public static String convertTime(Integer time, String timeZone){
        DateTimeZone zone = DateTimeZone.forID(timeZone);
        DateTime date = new DateTime(time * 1000L, zone);
        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");
        return date.toString(format);
    }

    public static String getCurrentTimeByTimeZone(String timeZone) {
        DateTimeZone zone = DateTimeZone.forID(timeZone);

        DateTime nowLocal = new DateTime();
        DateTime nowUTC = nowLocal.withZone(DateTimeZone.UTC).toDateTime();
        DateTime nowPlace = nowUTC.withZone(zone).toDateTime();
        Log.d("getLocateTime", nowPlace.toString());

        //DateTime date = new DateTime(nowUTC, zone);
        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");
        return nowPlace.toString(format);
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
        String s = String.valueOf(new DecimalFormat("0.0").format(value));
        if (s.equals("0.0")) {
            s = "0";
        }
        return s.replace(".0","");
    }

    public static String DoubleToStringP1(Double value) {
        return  String.valueOf(new DecimalFormat("0.0").format(value));
    }

    public static String getWeekNameLocate(Integer timeStamp) {

        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date(timeStamp * 1000L));

    }

    public static String getWeekNameEnglish(Integer timeStamp) {

        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new Date(timeStamp * 1000L)).substring(0,3).toUpperCase();

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
