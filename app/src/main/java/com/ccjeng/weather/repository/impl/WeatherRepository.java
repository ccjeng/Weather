package com.ccjeng.weather.repository.impl;

import android.util.Log;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.WeatherCurrenly;
import com.ccjeng.weather.model.WeatherDaily;
import com.ccjeng.weather.model.WeatherHourly;
import com.ccjeng.weather.model.forecastio.Day;
import com.ccjeng.weather.model.forecastio.Hour;
import com.ccjeng.weather.realm.RealmTable;
import com.ccjeng.weather.utils.Utils;
import com.ccjeng.weather.view.base.BaseApplication;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by andycheng on 2016/9/9.
 */
public class WeatherRepository {

    private final String TAG = this.getClass().getSimpleName();

    public void removeWeather(final City city) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm0) {
                realm.where(WeatherCurrenly.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findFirst()
                        .deleteFromRealm();

                realm.where(WeatherDaily.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findAll()
                        .deleteAllFromRealm();

                realm.where(WeatherHourly.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findAll()
                        .deleteAllFromRealm();
            }
        });
    }
    public void putWeatherCurrently(final City city) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WeatherCurrenly c = realm.createObject(WeatherCurrenly.class);
                c.setCityId(city.getId());
                c.setId(Utils.getUniqueID());
                c.setTimezone(city.getCityWeather().getTimezone());
                c.setOffset(city.getCityWeather().getOffset());
                c.setFetchtime(city.getCityWeather().getFetchtime());
                c.setTime(city.getCityWeather().getCurrently().getTime());
                c.setSummary(city.getCityWeather().getCurrently().getSummary());
                c.setIcon(city.getCityWeather().getCurrently().getIcon());
                c.setPrecipIntensity(city.getCityWeather().getCurrently().getPrecipIntensity());
                c.setPrecipProbability(city.getCityWeather().getCurrently().getPrecipProbability());
                c.setTemperature(city.getCityWeather().getCurrently().getTemperature());
                c.setDewPoint(city.getCityWeather().getCurrently().getDewPoint());
                c.setHumidity(city.getCityWeather().getCurrently().getHumidity());
                c.setWindSpeed(city.getCityWeather().getCurrently().getWindSpeed());
                c.setWindBearing(city.getCityWeather().getCurrently().getWindBearing());
                c.setCloudCover(city.getCityWeather().getCurrently().getCloudCover());
                c.setPressure(city.getCityWeather().getCurrently().getPressure());
                c.setOzone(city.getCityWeather().getCurrently().getOzone());
                c.setDailySummary(city.getCityWeather().getDaily().getSummary());
                c.setDailyIcon(city.getCityWeather().getDaily().getIcon());
                c.setHourlySummary(city.getCityWeather().getHourly().getSummary());
                c.setHourlyIcon(city.getCityWeather().getHourly().getIcon());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "putWeatherCurrently success");
                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, error.getMessage());
                realm.close();
            }
        });
    }

    public void putWeatherDaily(final City city) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        final ArrayList<WeatherDaily> days = new ArrayList<WeatherDaily>();

        for(Day day: city.getCityWeather().getDaily().getDay()) {
            WeatherDaily d = new WeatherDaily();
            d.setId(Utils.getUniqueID());
            d.setCityId(city.getId());
            d.setTime(day.getTime());
            d.setSummary(day.getSummary());
            d.setIcon(day.getIcon());
            d.setSunriseTime(day.getSunriseTime());
            d.setSunsetTime(day.getSunsetTime());
            d.setPrecipIntensity(day.getPrecipIntensity());
            d.setPrecipIntensityMax(day.getPrecipIntensityMax());
            d.setPrecipProbability(day.getPrecipProbability());
            d.setPrecipIntensityMaxTime(day.getPrecipIntensityMaxTime());
            d.setPrecipType(day.getPrecipType());
            d.setTemperatureMin(day.getTemperatureMin());
            d.setTemperatureMinTime(day.getTemperatureMinTime());
            d.setTemperatureMax(day.getTemperatureMax());
            d.setTemperatureMaxTime(day.getTemperatureMaxTime());
            d.setApparentTemperatureMin(day.getApparentTemperatureMin());
            d.setApparentTemperatureMinTime(day.getApparentTemperatureMinTime());
            d.setApparentTemperatureMax(day.getApparentTemperatureMax());
            d.setApparentTemperatureMaxTime(day.getApparentTemperatureMaxTime());
            d.setDewPoint(day.getDewPoint());
            d.setHumidity(day.getHumidity());
            d.setWindSpeed(day.getWindSpeed());
            d.setWindBearing(day.getWindBearing());
            d.setCloudCover(day.getCloudCover());
            d.setPressure(day.getPressure());
            d.setOzone(day.getOzone());
            days.add(d);
        }

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(days);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "putWeatherDaily success = " + days.size());
                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, error.getMessage());
                realm.close();
            }
        });
    }

    public void putWeatherHourly(final City city) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        final ArrayList<WeatherHourly> hours = new ArrayList<WeatherHourly>();

        for(Hour hour: city.getCityWeather().getHourly().getHour()) {
            WeatherHourly h = new WeatherHourly();
            h.setId(Utils.getUniqueID());
            h.setCityId(city.getId());
            h.setSummary(hour.getSummary());
            h.setIcon(hour.getIcon());
            h.setPrecipIntensity(hour.getPrecipIntensity());
            h.setPrecipProbability(hour.getPrecipProbability());
            h.setTemperature(hour.getTemperature());
            h.setApparentTemperature(hour.getApparentTemperature());
            h.setDewPoint(hour.getDewPoint());
            h.setHumidity(hour.getHumidity());
            h.setWindSpeed(hour.getWindSpeed());
            h.setWindBearing(hour.getWindBearing());
            h.setCloudCover(hour.getCloudCover());
            h.setPressure(hour.getPressure());
            h.setOzone(hour.getOzone());
            hours.add(h);
        }

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(hours);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "putWeatherHourly success = " + hours.size());
                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, error.getMessage());
                realm.close();
            }
        });
    }
}
