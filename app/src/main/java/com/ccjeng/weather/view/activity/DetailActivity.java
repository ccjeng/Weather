package com.ccjeng.weather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    public static final String ARG_CITY = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        City city = (City) intent.getExtras().getSerializable(ARG_CITY);

        Log.d(TAG, city.getName());
    }
}
