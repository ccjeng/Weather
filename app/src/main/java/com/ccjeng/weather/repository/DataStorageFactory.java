package com.ccjeng.weather.repository;

import android.util.Log;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.repository.impl.CacheRepository;

/**
 * Created by andycheng on 2016/9/10.
 */
public class DataStorageFactory {
    private final String TAG = this.getClass().getSimpleName();

    private CacheRepository cache;

    public DataStorageFactory(CacheRepository cache) {
        this.cache = cache;
    }

    public DataSource create(City city, boolean fetchIfExpired) {
        DataSource dataSource;

        if (this.cache.isExpired(city) && fetchIfExpired) {
            Log.d(TAG, "from network");
            dataSource = new NetworkDataSource();
        } else {
            Log.d(TAG, "from disk");
            dataSource = new DiskDataSource(cache);
        }

        return dataSource;
    }
}
