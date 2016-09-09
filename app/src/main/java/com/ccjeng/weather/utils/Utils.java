package com.ccjeng.weather.utils;

import java.util.UUID;

/**
 * Created by andycheng on 2016/9/9.
 */
public class Utils {

    public static String getUniqueID() {
        return UUID.randomUUID().toString();
    }
}
