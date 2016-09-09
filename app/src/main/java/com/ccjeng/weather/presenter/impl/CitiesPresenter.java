package com.ccjeng.weather.presenter.impl;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.presenter.CitiesView;
import com.ccjeng.weather.presenter.base.BasePresenter;
import com.ccjeng.weather.repository.ICityRepository;
import com.ccjeng.weather.repository.impl.CityRepository;
import com.ccjeng.weather.repository.impl.WeatherService;
import com.ccjeng.weather.view.adapter.CitiesAdapter;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by andycheng on 2016/9/5.
 */
public class CitiesPresenter extends BasePresenter<CitiesView> implements SwipeRefreshLayout.OnRefreshListener{

    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private CitiesView mCitiesView;
    private GoogleApiClient googleApiClient;
    private CityRepository mCityRepository;
    private CitiesAdapter mCitiesAdapter;

    public CitiesPresenter(CitiesView citiesView, Context context) {
        this.mCitiesView = citiesView;
        this.mContext = context;
        mCityRepository = new CityRepository();
    }

    public void onClickAddCity() {
        mCitiesView.onAddCityButtonSelected();
    }

    public void reloadCities() {

        mCityRepository.getCities().doOnNext(new Action1<List<City>>() {
            @Override
            public void call(List<City> cities) {
                mCitiesView.addCities(cities);
            }
        }).flatMapIterable(new Func1<List<City>, Iterable<City>>() {
            @Override
            public Iterable<City> call(List<City> cities) {
                return cities;
            }
        }).flatMap(new Func1<City, Observable<City>>() {
            @Override
            public Observable<City> call(City city) {
                Log.d(TAG, "city = " + city.getName());
                return WeatherService.getWeatherData(city);
            }
        }).subscribe(new Subscriber<City>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(City city) {

                Log.d(TAG, city.getCityWeather().getCurrently().getSummary());
                mCitiesView.updateCity(city);
            }
        });
    }

    public void onRemoveCity(City city) {

        ICityRepository.onDeleteCallback callback = new ICityRepository.onDeleteCallback() {
            @Override
            public void onSuccess(String cityName) {
                Toast.makeText(mContext, cityName + " " + mContext.getString(R.string.msg_deleted), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String message) {
                Log.e(TAG, message);
            }
        };

        mCityRepository.removeCity(city,callback);

    }

    public void setAdapter(CitiesAdapter adapter) {
        this.mCitiesAdapter = adapter;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onRefresh() {

    }
}
