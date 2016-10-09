package com.ccjeng.weather.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.flickr.Photo;
import com.ccjeng.weather.repository.FlickrDataService;
import com.ccjeng.weather.utils.Utils;
import com.ccjeng.weather.view.adapter.TabsPagerAdapter;
import com.ccjeng.weather.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class WeatherActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();
    public static final String ARG_CITY = "city";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.main_bg)
    ImageView imageView;

    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        city = (City) intent.getExtras().getSerializable(ARG_CITY);

        // primary sections of the activity.
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), this, city);

        // Set up the ViewPager with the sections adapter.
        viewPager.setAdapter(tabsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        getSupportActionBar().setTitle(city.getName());

        if (Utils.isNetworkConnected(this)) {
            getPhoto();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_map:
                String mapUrl = Utils.getEarthURL(city.getLon()+ ","+ city.getLat());
                Log.d(TAG, mapUrl);
                Intent ie = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl));
                startActivity(ie);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getPhoto() {

        FlickrDataService flickr = new FlickrDataService();
        flickr.getPhotoData(city)
               .subscribe(new Subscriber<Photo>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onNext(Photo photo) {

                String imageUrl = Utils.getFlickrImageURL(photo.getFarm().toString()
                        , photo.getServer()
                        , photo.getId()
                        , photo.getSecret());

                Log.d(TAG, imageUrl);

                //Toast.makeText(WeatherActivity.this, imageUrl, Toast.LENGTH_LONG).show();

                Glide.with(WeatherActivity.this)
                        .load(imageUrl)
                        .into(imageView);

            }
        });
    }

}
