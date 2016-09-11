package com.ccjeng.weather.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.presenter.CitiesView;
import com.ccjeng.weather.presenter.base.BasePresenter;
import com.ccjeng.weather.repository.ICityRepository;
import com.ccjeng.weather.repository.impl.CacheRepository;
import com.ccjeng.weather.repository.impl.CityRepository;
import com.ccjeng.weather.repository.impl.Repository;
import com.ccjeng.weather.view.activity.WeatherActivity;
import com.ccjeng.weather.view.adapter.CitiesAdapter;
import com.ccjeng.weather.view.adapter.RecyclerItemClickListener;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by andycheng on 2016/9/5.
 */
public class CitiesPresenter extends BasePresenter<CitiesView>
        implements RecyclerItemClickListener.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private CitiesView citiesView;
    private GoogleApiClient googleApiClient;
    private CityRepository cityRepository;
    private Repository repository;
    private CitiesAdapter citiesAdapter;
    private CompositeSubscription subscriptions;

    public CitiesPresenter(CitiesView citiesView, Context context) {
        this.citiesView = citiesView;
        this.context = context;
        cityRepository = new CityRepository();
        repository = new Repository();
        this.subscriptions = new CompositeSubscription();
    }

    public void onClickAddCity() {
        citiesView.onAddCityButtonSelected();
    }

    public void addNewCity(City city) {
        /* no need to do anything */
    }

    public void reloadCities() {

        Log.d(TAG, "reloadCities");

        subscriptions.add(cityRepository.getCities()
                .doOnNext(new Action1<List<City>>() {
                    @Override
                    public void call(List<City> cities) {
                        Log.d(TAG, "cities.size = " + cities.size());
                        citiesView.addCities(cities);
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
                        return repository.getWeatherData(city, true);
                    }
                }).subscribe(new Subscriber<City>() {
                    @Override
                    public void onCompleted() {
                        subscriptions.remove(this);
                        this.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "reloadCities = " + e.getMessage());
                    }

                    @Override
                    public void onNext(City city) {
                        citiesView.updateCity(city);
                    }
                }));


    }

    public void onRemoveCity(final City city) {

        ICityRepository.onDeleteCallback callback = new ICityRepository.onDeleteCallback() {
            @Override
            public void onSuccess(String cityName) {
                CacheRepository cache = new CacheRepository();
                cache.removeCache(city);

                Toast.makeText(context, cityName + " " + context.getString(R.string.msg_deleted), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String message) {
                Log.e(TAG, message);
            }
        };

        cityRepository.removeCity(city, callback);
    }

    public void setAdapter(CitiesAdapter adapter) {
        this.citiesAdapter = adapter;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    @Override
    public void onDestroy() {
        subscriptions.unsubscribe();
    }

    @Override
    public void onRefresh() {
        reloadCities();
    }

    @Override
    public void onItemClick(View view, int position) {

        City city = citiesAdapter.getCities().get(position);
        if (city != null && city.getCityWeather().getCurrently() != null) {

            Intent i = new Intent(context, WeatherActivity.class);
            i.putExtra(WeatherActivity.ARG_CITY, city);
            context.startActivity(i);
        }

    }
}
