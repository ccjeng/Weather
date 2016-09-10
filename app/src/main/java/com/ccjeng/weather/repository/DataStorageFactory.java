package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.repository.impl.CacheRepository;

/**
 * Created by andycheng on 2016/9/10.
 */
public class DataStorageFactory {

    private CacheRepository cache;

    public DataStorageFactory(CacheRepository cache) {
        this.cache = cache;
    }

    public DataSource create(City city, boolean fetchIfExpired) {
        DataSource dataSource;

        if (this.cache.isExpired(city) && fetchIfExpired) {
            dataSource = new NetworkDataSource();
        } else {
            dataSource = new DiskDataSource(cache);
        }

        return dataSource;
    }
}
