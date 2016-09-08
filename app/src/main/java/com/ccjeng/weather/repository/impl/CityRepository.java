package com.ccjeng.weather.repository.impl;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.repository.ICityRepository;
import com.ccjeng.weather.view.base.BaseApplication;

import io.realm.Realm;

/**
 * Created by andycheng on 2016/9/8.
 */
public class CityRepository implements ICityRepository {

    @Override
    public void addCity(City city, onSaveCallback callback) {

        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        realm.beginTransaction();
        City newCity = realm.where(City.class).equalTo("id", city.getId()).findFirst();
        if (newCity == null) {
            City c = realm.createObject(City.class);
            c.setId(city.getId());
            c.setName(city.getName());
            c.setLat(city.getLat());
            c.setLon(city.getLon());
        }
        realm.commitTransaction();


    }
}
