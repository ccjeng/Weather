package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;

import rx.Observable;

/**
 * Created by andycheng on 2016/9/10.
 */
public interface DataSource {

    Observable<City> getWeatherData(City city);
}
