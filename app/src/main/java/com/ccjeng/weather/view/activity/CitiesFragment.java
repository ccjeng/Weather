package com.ccjeng.weather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.presenter.CitiesView;
import com.ccjeng.weather.presenter.impl.CitiesPresenter;
import com.ccjeng.weather.view.GoogleApiClientProvider;
import com.ccjeng.weather.view.adapter.CitiesAdapter;
import com.ccjeng.weather.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/7.
 */
public class CitiesFragment extends BaseFragment<CitiesView, CitiesPresenter> implements CitiesView {

    @BindView(R.id.add_city_fab)
    FloatingActionButton mAddCityFab;

    @BindView(R.id.recyclerview)
    RecyclerView mCitiesRecyclerView;

    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefresh;

    private CitiesAdapter mAdapter;
    private GoogleApiClientProvider googleApiClientProvider;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new CitiesAdapter(getActivity());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mCitiesRecyclerView.setLayoutManager(layoutManager);
        mCitiesRecyclerView.setAdapter(mAdapter);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mPresenter.onRemoveCity(mAdapter.getCities().get(viewHolder.getAdapterPosition()));
                mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
                if (mAdapter.getCities().isEmpty()) {
                    mCitiesRecyclerView.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mCitiesRecyclerView);

        mPresenter.reloadCities();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        googleApiClientProvider = (GoogleApiClientProvider) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        mPresenter.setAdapter(mAdapter);
        mPresenter.setGoogleApiClient(googleApiClientProvider.getApiClient());
        mAddCityFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickAddCity();
            }
        });
        mSwipeRefresh.setOnRefreshListener(mPresenter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected CitiesPresenter createPresenter() {
        return new CitiesPresenter(this, getActivity());
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
        mAdapter.addCity(city);
        mCitiesRecyclerView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void addCities(List<City> cities) {
        mAdapter.setCities((ArrayList) cities);
        if (cities != null && !cities.isEmpty()) {
            mCitiesRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateCity(City city) {
        mSwipeRefresh.setRefreshing(false);
        mAdapter.updateCity(city);
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
