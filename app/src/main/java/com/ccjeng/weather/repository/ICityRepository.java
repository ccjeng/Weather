package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import java.util.List;
import rx.Observable;

/**
 * Created by andycheng on 2016/9/8.
 */
public interface ICityRepository extends IBaseRepository{

    void addCity(City city, onSaveCallback callback);

    void removeCity(City city, onDeleteCallback callback);

    Observable<List<City>> getCities();


}
