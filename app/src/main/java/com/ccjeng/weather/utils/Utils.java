package com.ccjeng.weather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.UUID;

/**
 * Created by andycheng on 2016/9/9.
 */
public class Utils {

    public static String getUniqueID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isNetworkConnected(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    public static String getFlickrImageURL(String farm, String server, String id, String secret){

        /* Format
            https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
        * */
        return "http://farm" + farm + ".static.flickr.com/" + server + "/" +
                id + "_" + secret + "_" + "c.jpg";


    }

    public static String getEarthURL(String latlng){
        //https://earth.nullschool.net/#current/wind/surface/level/orthographic=122.11,22.48,3000
        return "https://earth.nullschool.net/#current/wind/surface/level/orthographic="+ latlng +",3000";

    }
}
