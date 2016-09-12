package com.ccjeng.weather.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.utils.Formatter;
import com.ccjeng.weather.view.adapter.TabsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    public static final String ARG_CITY = "city";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      //  LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        City city = (City) intent.getExtras().getSerializable(ARG_CITY);

        // primary sections of the activity.
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), this, city);

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        getSupportActionBar().setTitle(city.getName());
        getSupportActionBar().setSubtitle(getString(R.string.last_update,
                Formatter.formatTimeWithDayIfNotToday(this, city.getCityWeather().getFetchTime())));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
