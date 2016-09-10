package com.ccjeng.weather.presenter.impl;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.presenter.SearchView;
import com.ccjeng.weather.presenter.base.BasePresenter;
import com.ccjeng.weather.repository.ICityRepository;
import com.ccjeng.weather.repository.impl.CityRepository;
import com.ccjeng.weather.view.adapter.PlaceAutocompleteAdapter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;

/**
 * Created by andycheng on 2016/9/7.
 */
public class SearchPresenter extends BasePresenter<SearchView> implements AdapterView.OnItemClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private SearchView searchView;
    private Context context;
    private GoogleApiClient googleApiClient;
    private PlaceAutocompleteAdapter adapter;
    private CityRepository cityRepository;

    public SearchPresenter(SearchView view, Context context) {
        this.searchView = view;
        this.context = context;
        cityRepository = new CityRepository();
    }

    public void setAutocompleteView() {
        searchView.setAutocompleteView();
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public void setPlaceAutocompleteAdapter() {

        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        this.adapter = new PlaceAutocompleteAdapter(context, googleApiClient, null, filter);
    }

    public PlaceAutocompleteAdapter getPlaceAutocompleteAdapter() {
        return adapter;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onItemClick(AdapterView<?> view, View view1, int position, long l) {
             /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
                */
        final AutocompletePrediction item = adapter.getItem(position);
        String placeId = item.getPlaceId();
        final CharSequence primaryText = item.getPrimaryText(null);

        Log.i(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(googleApiClient, placeId);
        placeResult.setResultCallback(updatePlaceDetailsCallback);

        Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
    }

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> updatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            Place place = places.get(0);

            //Add City
            ICityRepository.onSearchSaveCallback onSaveCallback = new ICityRepository.onSearchSaveCallback() {
                @Override
                public void onSuccess(City city) {
                    Toast.makeText(context, city.getName() + " " + context.getString(R.string.msg_added), Toast.LENGTH_LONG).show();
                    //Back to CityFragment
                    Log.d(TAG, "Back to CityFragment = " + city.getName());
                    searchView.onCitySuggestionSelected(city);
                }

                @Override
                public void onError(String message) {
                    Log.e(TAG, message);
                }
            };
            City city = new City();
            city.setId(place.getId());
            city.setName(place.getName().toString());
            city.setLon(place.getLatLng().longitude);
            city.setLat(place.getLatLng().latitude);
            cityRepository.addCity(city, onSaveCallback);


            Log.i(TAG, "Place details received: " + place.getName());

            places.release();
        }
    };


}
