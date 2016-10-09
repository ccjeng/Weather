package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.forecastio.CityWeather;
import com.ccjeng.weather.repository.impl.CacheRepository;
import com.ccjeng.weather.utils.Constant;

import java.util.Locale;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by andycheng on 2016/9/9.
 */
public class NetworkDataSource implements DataSource {
    private final String TAG = this.getClass().getSimpleName();

    public Observable<City> getWeatherData(final City city) {

        WeatherServiceEndpoint service = HttpClient.getClient(Constant.FORECASTIO_ENDPOINT)
                .create(WeatherServiceEndpoint.class);

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
