package com.ccjeng.weather.model;

import com.ccjeng.weather.model.forecastio.CityWeather;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Fernando on 5/11/2015.
 */
public class City extends RealmObject implements Serializable {

    @PrimaryKey
    private String id;
    private String name;
    private Double lat;
    private Double lon;

    @Ignore
    private CityWeather cityWeather;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityWeather getCityWeather() {
        return cityWeather;
    }

    public void setCityWeather(CityWeather cityWeather) {
        this.cityWeather = cityWeather;
    }
}