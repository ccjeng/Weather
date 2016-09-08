package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;

/**
 * Created by andycheng on 2016/9/8.
 */
public interface ICityRepository extends IBaseRepository{

    void addCity(City city, onSaveCallback callback);

}
