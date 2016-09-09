package com.ccjeng.weather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.presenter.SearchView;
import com.ccjeng.weather.presenter.impl.SearchPresenter;
import com.ccjeng.weather.view.GoogleApiClientProvider;
import com.ccjeng.weather.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/7.
 */
public class SearchFragment extends BaseFragment<SearchView, SearchPresenter> implements SearchView {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.autocomplete)
    AutoCompleteTextView autocompleteView;

    private GoogleApiClientProvider googleApiClientProvider;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        mPresenter.setGoogleApiClient(googleApiClientProvider.getApiClient());
        mPresenter.setAutocompleteView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        googleApiClientProvider = (GoogleApiClientProvider) context;

    }


    @Override
    public void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this, getActivity());
    }

    @Override
    public void setAutocompleteView() {
        autocompleteView.setOnItemClickListener(mPresenter);
        mPresenter.setPlaceAutocompleteAdapter();
        autocompleteView.setAdapter(mPresenter.getPlaceAutocompleteAdapter());
    }

    @Override
    public void onCitySuggestionSelected() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, new CitiesFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
