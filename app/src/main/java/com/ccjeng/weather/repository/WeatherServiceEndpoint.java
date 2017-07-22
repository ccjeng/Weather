package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.forecastio.CityWeather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by andycheng on 2016/9/9.
 */
public interface WeatherServiceEndpoint {

    //Example call https://api.forecast.io/forecast/xxxxxxxxxxxxxxx/-31.4286,-61.9143
    @GET("forecast/{apiKey}/{latLon}")
    Observable<CityWeather> getForecast(@Path(value="latLon", encoded=true) String latLong
            , @Path("apiKey") String APIKEY
            , @Query("units") String units
            , @Query("lang") String lang);
}
