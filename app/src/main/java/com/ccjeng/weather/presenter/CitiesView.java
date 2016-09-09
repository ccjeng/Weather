package com.ccjeng.weather.presenter;

import com.ccjeng.weather.model.City;

import java.util.List;

/**
 * Created by andycheng on 2016/9/5.
 */
public interface CitiesView {
    void onAddCityButtonSelected();
    void addCity(City city);
    void addCities(List<City> cities);
    void updateCity(City city);
    void showTemperatureInCelsius();
    void showTemperatureInFahrenheit();
    void setSetColumns(int columns);
}
