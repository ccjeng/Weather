package com.ccjeng.weather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.presenter.CitiesView;
import com.ccjeng.weather.presenter.impl.CitiesPresenter;
import com.ccjeng.weather.view.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/7.
 */
public class CitiesFragment extends BaseFragment<CitiesView, CitiesPresenter> implements CitiesView {

    @BindView(R.id.add_city_fab)
    FloatingActionButton addCityFab;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {

        addCityFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickAddCity();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected CitiesPresenter createPresenter() {
        return new CitiesPresenter(this);
    }


    @Override
    public void onAddCityButtonSelected() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        SearchFragment fragment = new SearchFragment();
        ft.add(R.id.fragment, fragment, null);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void addCity(City city) {

    }

    @Override
    public void addCities(List<City> city) {

    }

    @Override
    public void updateCity(City city) {

    }

    @Override
    public void showTemperatureInCelsius() {

    }

    @Override
    public void showTemperatureInFahrenheit() {

    }

    @Override
    public void setSetColumns(int columns) {

    }
}
