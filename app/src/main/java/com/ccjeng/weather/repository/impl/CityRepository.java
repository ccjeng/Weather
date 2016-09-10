package com.ccjeng.weather.repository.impl;

import android.util.Log;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.realm.RealmTable;
import com.ccjeng.weather.repository.ICityRepository;
import com.ccjeng.weather.view.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by andycheng on 2016/9/8.
 */
public class CityRepository implements ICityRepository {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void addCity(final City city, final onSearchSaveCallback callback) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                City newCity = realm.where(City.class)
                        .equalTo(RealmTable.ID, city.getId()).findFirst();
                if (newCity == null) {
                    City c = realm.createObject(City.class);
                    c.setId(city.getId());
                    c.setName(city.getName());
                    c.setLat(city.getLat());
                    c.setLon(city.getLon());
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
                callback.onSuccess(city);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, error.getMessage());
                realm.close();
                callback.onError(error.getMessage());
            }
        });


    }

    @Override
    public Observable<List<City>> getCities() {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        return realm.where(City.class).findAll()
                .asObservable()
                .map(new Func1<RealmResults<City>, List<City>>() {
                    @Override
                    public List<City> call(RealmResults<City> cities) {
                        //Set cityWeather from diskcache
                        ArrayList<City> citiesNew = new ArrayList<>();

                        for (City city:cities) {
                            City cityNew = new City();
                            cityNew.setId(city.getId());
                            cityNew.setName(city.getName());
                            cityNew.setLat(city.getLat());
                            cityNew.setLon(city.getLon());
                            CacheRepository cache = new CacheRepository();
                            cityNew.setCityWeather(cache.getCityWeatherFromCityId(city.getId()));
                            citiesNew.add(cityNew);
                        }

                        return citiesNew;

                    }
                }).doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        realm.close();
                    }
                });
    }

    @Override
    public void removeCity(final City city, final onDeleteCallback callback) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(City.class)
                        .equalTo(RealmTable.ID, city.getId()).findFirst()
                        .deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realm.close();
                callback.onSuccess(city.getName());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, error.getMessage());
                realm.close();
                callback.onError(error.getMessage());
            }
        });
    }
}
