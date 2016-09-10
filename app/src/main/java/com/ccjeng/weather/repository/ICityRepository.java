package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import java.util.List;
import rx.Observable;

/**
 * Created by andycheng on 2016/9/8.
 */
public interface ICityRepository extends IBaseRepository{

    interface onSearchSaveCallback {
        void onSuccess(City city);
        void onError(String message);
    }

    void addCity(City city, onSearchSaveCallback callback);

    void removeCity(City city, onDeleteCallback callback);

    Observable<List<City>> getCities();


}
