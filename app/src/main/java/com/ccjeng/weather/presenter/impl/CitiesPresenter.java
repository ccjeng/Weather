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
import com.ccjeng.weather.view.adapter.CitiesAdapter;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import rx.functions.Action1;

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
        mCityRepository.getCities().subscribe(new Action1<List<City>>() {
            @Override
            public void call(List<City> cities) {
                mCitiesView.addCities(cities);
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
