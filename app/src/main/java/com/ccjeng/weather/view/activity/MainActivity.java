package com.ccjeng.weather.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ccjeng.weather.R;
import com.ccjeng.weather.view.GoogleApiClientProvider;
import com.ccjeng.weather.view.base.BaseActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , GoogleApiClientProvider {

    private final String TAG = this.getClass().getSimpleName();
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment, new CitiesFragment(), null);
            ft.commit();
        }

        buildGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
        super.onStop();
    }


    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, 0, this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Connection started");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
    }

    @Override
    public GoogleApiClient getApiClient() {
        return googleApiClient;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        if (result.getErrorMessage() != null) {
            Log.e(TAG, result.getErrorMessage());
        }
    }


}
