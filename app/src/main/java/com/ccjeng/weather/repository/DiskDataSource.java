package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.repository.impl.CacheRepository;

import rx.Observable;

/**
 * Created by andycheng on 2016/9/10.
 */
public class DiskDataSource implements DataSource {

    private CacheRepository cache;

    public DiskDataSource(CacheRepository cache) {
        this.cache = cache;
    }

    @Override
    public Observable<City> getWeatherData(City city) {
        return Observable.just(city);
    }
}
