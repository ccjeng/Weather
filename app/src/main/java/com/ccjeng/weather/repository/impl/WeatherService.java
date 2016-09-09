package com.ccjeng.weather.repository.impl;

import android.content.Context;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.forecastio.CityWeather;
import com.ccjeng.weather.repository.WeatherServiceEndpoint;
import com.ccjeng.weather.utils.Constant;
import com.ccjeng.weather.view.base.BaseApplication;

import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by andycheng on 2016/9/9.
 */
public class WeatherService {

    private Context mContext;

    private OkHttpClient okhttpClient;
    public WeatherService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BaseApplication.APPDEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

    }

    public Observable<City> getWeatherData(final City city) {
//// TODO: 2016/9/9 singleton

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.FORECASTIO_ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build();

        WeatherServiceEndpoint service = retrofit.create(WeatherServiceEndpoint.class);

        String latlong = String.format("%s,%s", city.getLat(), city.getLon());

        return service.getForecast(latlong, Constant.FORECASTIO_APIKEY, "ca", Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CityWeather, City>() {
                    @Override
                    public City call(CityWeather weather) {
                        if (weather != null) {
                            city.setCityWeather(weather);
                        }
                        return city;
                    }
                });

    }
}
