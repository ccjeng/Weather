package com.ccjeng.weather.presenter;

import com.ccjeng.weather.model.City;

/**
 * Created by andycheng on 2016/9/7.
 */
public interface SearchView {
    void setAutocompleteView();
    void onCitySuggestionSelected(City city);
}
