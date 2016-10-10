package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.forecastio.CityWeather;
import com.ccjeng.weather.repository.impl.CacheRepository;
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
public class NetworkDataSource implements DataSource {
    private final String TAG = this.getClass().getSimpleName();

    static volatile Retrofit retrofit = null;

    public NetworkDataSource(){
    }

    public static Retrofit getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BaseApplication.APPDEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        if (retrofit == null) {
            synchronized (NetworkDataSource.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.FORECASTIO_ENDPOINT)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okhttpClient)
                            .build();
                }
            }
        }
        return retrofit;

    }

    public Observable<City> getWeatherData(final City city) {

        WeatherServiceEndpoint service = getClient().create(WeatherServiceEndpoint.class);

        String latlong = String.format("%s,%s", city.getLat(), city.getLon());

        return service.getForecast(latlong, Constant.FORECASTIO_APIKEY, "ca", Locale.getDefault().getLanguage())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<CityWeather, City>() {
                    @Override
                    public City call(CityWeather weather) {

                        if (weather != null) {
                            weather.setFetchTime(System.currentTimeMillis());
                            city.setCityWeather(weather);

                            //add to cache
                            CacheRepository cache = new CacheRepository();
                            cache.putWeatherCurrently(city);
                            cache.putWeatherDaily(city);
                            cache.putWeatherHourly(city);
                        }
                        return city;
                    }
                });

    }
}
