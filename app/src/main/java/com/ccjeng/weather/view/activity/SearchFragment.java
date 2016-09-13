package com.ccjeng.weather.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
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
        autocompleteView.requestFocus();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {
        presenter.setGoogleApiClient(googleApiClientProvider.getApiClient());
        presenter.setAutocompleteView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        googleApiClientProvider = (GoogleApiClientProvider) context;
    }


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this, getActivity());
    }

    @Override
    public void setAutocompleteView() {
        autocompleteView.setOnItemClickListener(presenter);
        presenter.setPlaceAutocompleteAdapter();
        autocompleteView.setAdapter(presenter.getPlaceAutocompleteAdapter());
    }

    @Override
    public void onCitySuggestionSelected(City city) {

        //Hide soft keyboard
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        getFragmentManager().popBackStack();
        getFragmentManager().executePendingTransactions();
        Log.d(TAG, "onCitySuggestionSelected = " + city.getName());
        ((CitiesFragment) getFragmentManager().findFragmentById(R.id.fragment)).presenter.addNewCity(city);

    }
}
