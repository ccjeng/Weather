package com.ccjeng.weather.presenter.impl;

import com.ccjeng.weather.presenter.CitiesView;
import com.ccjeng.weather.presenter.base.BasePresenter;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by andycheng on 2016/9/5.
 */
public class CitiesPresenter extends BasePresenter<CitiesView> {

    private final String TAG = this.getClass().getSimpleName();
    private CitiesView mCitiesView;
    private GoogleApiClient googleApiClient;


    public CitiesPresenter(CitiesView citiesView) {
        this.mCitiesView = citiesView;

    }

    public void onClickAddCity() {
        mCitiesView.onAddCityButtonSelected();
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }


}
