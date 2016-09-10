package com.ccjeng.weather.repository.impl;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.repository.DataSource;
import com.ccjeng.weather.repository.DataStorageFactory;

import rx.Observable;

/**
 * Created by andycheng on 2016/9/10.
 */
public class Repository {
    private DataStorageFactory factory;

    public Repository() {
        CacheRepository cache = new CacheRepository();
        this.factory = new DataStorageFactory(cache);
    }

    public Observable<City> getWeatherData(City city, boolean fetchIfExpired) {
        final DataSource dataSource = this.factory.create(city, fetchIfExpired);
        return dataSource.getWeatherData(city);
    }
}
