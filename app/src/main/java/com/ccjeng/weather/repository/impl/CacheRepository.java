package com.ccjeng.weather.repository.impl;

import android.util.Log;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.WeatherCurrently;
import com.ccjeng.weather.model.WeatherDaily;
import com.ccjeng.weather.model.WeatherHourly;
import com.ccjeng.weather.model.forecastio.CityWeather;
import com.ccjeng.weather.model.forecastio.Currently;
import com.ccjeng.weather.model.forecastio.Daily;
import com.ccjeng.weather.model.forecastio.Day;
import com.ccjeng.weather.model.forecastio.Hour;
import com.ccjeng.weather.model.forecastio.Hourly;
import com.ccjeng.weather.realm.RealmTable;
import com.ccjeng.weather.utils.Utils;
import com.ccjeng.weather.view.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by andycheng on 2016/9/9.
 */
public class CacheRepository {

    private final String TAG = this.getClass().getSimpleName();
    public static final long EXPIRATION_TIME = 20 * 60 * 1000; //20 mins

    public boolean isExpired(City city) {
        if (city == null || city.getCityWeather() == null) {
            return true;
        }

        //return (System.currentTimeMillis() - city.getCityWeather().getFetchTime() > EXPIRATION_TIME);

        return true;
    }

    public void removeCache(final City city) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WeatherCurrently old = realm.where(WeatherCurrently.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findFirst();
                if (old != null)
                    old.deleteFromRealm();

                realm.where(WeatherDaily.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findAll()
                        .deleteAllFromRealm();

                realm.where(WeatherHourly.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findAll()
                        .deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "removeCache success");
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

    public void putWeatherCurrently(final City city) {
        final Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WeatherCurrently old = realm.where(WeatherCurrently.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findFirst();
                if (old != null)
                    old.deleteFromRealm();

                WeatherCurrently c = realm.createObject(WeatherCurrently.class);
                c.setCityId(city.getId());
                c.setId(Utils.getUniqueID());
                c.setTimezone(city.getCityWeather().getTimezone());
                c.setOffset(city.getCityWeather().getOffset());
                c.setFetchtime(city.getCityWeather().getFetchTime());
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

        for (Day day : city.getCityWeather().getDaily().getDay()) {
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
                RealmResults<WeatherDaily> old = realm.where(WeatherDaily.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findAll();
                old.deleteAllFromRealm();

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

        for (Hour hour : city.getCityWeather().getHourly().getHour()) {
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

                RealmResults<WeatherHourly> old = realm.where(WeatherHourly.class)
                        .equalTo(RealmTable.CITYID, city.getId()).findAll();
                old.deleteAllFromRealm();

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


    public CityWeather getCityWeatherFromCityId(String cityId) {
        CityWeather cityWeather = new CityWeather();

        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        WeatherCurrently currentlyTable = realm.where(WeatherCurrently.class).equalTo(RealmTable.CITYID, cityId).findFirst();

        if (currentlyTable != null) {

            cityWeather.setTimezone(currentlyTable.getTimezone());
            cityWeather.setFetchTime(currentlyTable.getFetchtime());
            cityWeather.setOffset(currentlyTable.getOffset());

            Currently currently = new Currently();
            currently.setTime(currentlyTable.getTime());
            currently.setSummary(currentlyTable.getSummary());
            currently.setIcon(currentlyTable.getIcon());
            currently.setPrecipIntensity(currentlyTable.getPrecipIntensity());
            currently.setPrecipProbability(currentlyTable.getPrecipProbability());
            //     currently.setPrecipType(currentlyTable.get);
            currently.setTemperature(currentlyTable.getTemperature());
            currently.setApparentTemperature(currentlyTable.getApparentTemperature());
            currently.setDewPoint(currentlyTable.getDewPoint());
            currently.setHumidity(currentlyTable.getHumidity());
            currently.setWindSpeed(currentlyTable.getWindSpeed());
            currently.setWindBearing(currentlyTable.getWindBearing());
            currently.setCloudCover(currentlyTable.getCloudCover());
            currently.setPressure(currentlyTable.getPressure());
            currently.setOzone(currentlyTable.getOzone());

            Daily daily = new Daily();
            daily.setSummary(currentlyTable.getDailySummary());
            daily.setIcon(currentlyTable.getDailyIcon());
            cityWeather.setDaily(daily);

            Hourly hourly = new Hourly();
            hourly.setSummary(currentlyTable.getHourlySummary());
            hourly.setIcon(currentlyTable.getHourlyIcon());
            cityWeather.setHourly(hourly);

            cityWeather.setCurrently(currently);

        }

        if (cityWeather != null) {
            List<Day> days = getDaysFromCityId(cityId);
            if (days.size() != 0) {
                cityWeather.getDaily().setDay(days);
            }

            List<Hour> hours = getHourlyFromCityId(cityId);
            if (hours.size() != 0) {
                cityWeather.getHourly().setHour(hours);
            }
        }
        return cityWeather;
    }

    private List<Day> getDaysFromCityId(String cityId) {
        ArrayList<Day> days = new ArrayList<>();

        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        RealmResults<WeatherDaily> dailyResults = realm.where(WeatherDaily.class).equalTo(RealmTable.CITYID, cityId).findAll();

        for (WeatherDaily dailyTable : dailyResults) {
            Day day = new Day();
            day.setTime(dailyTable.getTime());
            day.setSummary(dailyTable.getSummary());
            day.setIcon(dailyTable.getIcon());
            day.setSunriseTime(dailyTable.getSunriseTime());
            day.setSunsetTime(dailyTable.getSunsetTime());
            day.setMoonPhase(dailyTable.getMoonPhase());
            day.setPrecipIntensity(dailyTable.getPrecipIntensity());
            day.setPrecipIntensityMax(dailyTable.getPrecipIntensityMax());
            day.setPrecipIntensityMaxTime(dailyTable.getPrecipIntensityMaxTime());
            day.setPrecipProbability(dailyTable.getPrecipProbability());
            day.setPrecipType(dailyTable.getPrecipType());
            day.setTemperatureMax(dailyTable.getTemperatureMax());
            day.setTemperatureMaxTime(dailyTable.getTemperatureMaxTime());
            day.setTemperatureMin(dailyTable.getTemperatureMin());
            day.setTemperatureMinTime(dailyTable.getTemperatureMinTime());
            day.setApparentTemperatureMax(dailyTable.getApparentTemperatureMax());
            day.setApparentTemperatureMaxTime(dailyTable.getApparentTemperatureMaxTime());
            day.setApparentTemperatureMin(dailyTable.getApparentTemperatureMin());
            day.setApparentTemperatureMinTime(dailyTable.getApparentTemperatureMinTime());
            day.setDewPoint(dailyTable.getDewPoint());
            day.setHumidity(dailyTable.getHumidity());
            day.setWindSpeed(dailyTable.getWindSpeed());
            day.setWindBearing(dailyTable.getWindBearing());
            day.setCloudCover(dailyTable.getCloudCover());
            day.setPressure(dailyTable.getPressure());
            day.setOzone(dailyTable.getOzone());
            days.add(day);
        }

        return days;
    }

    private List<Hour> getHourlyFromCityId(String cityId) {

        ArrayList<Hour> hours = new ArrayList<>();

        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);

        RealmResults<WeatherHourly> hourlyResults = realm.where(WeatherHourly.class).equalTo(RealmTable.CITYID, cityId).findAll();

        for (WeatherHourly hourlyTable : hourlyResults) {
            Hour hour = new Hour();
            //0 is id
            hour.setTime(hourlyTable.getTime());
            hour.setSummary(hourlyTable.getSummary());
            hour.setIcon(hourlyTable.getIcon());
            hour.setPrecipIntensity(hourlyTable.getPrecipIntensity());
            hour.setPrecipProbability(hourlyTable.getPrecipProbability());
           // hour.setPrecipType(cursor.getString(6));
            hour.setTemperature(hourlyTable.getTemperature());
            hour.setApparentTemperature(hourlyTable.getApparentTemperature());
            hour.setDewPoint(hourlyTable.getDewPoint());
            hour.setHumidity(hourlyTable.getHumidity());
            hour.setWindSpeed(hourlyTable.getWindSpeed());
            hour.setWindBearing(hourlyTable.getWindBearing());
            hour.setCloudCover(hourlyTable.getCloudCover());
            hour.setPressure(hourlyTable.getPressure());
            hour.setOzone(hourlyTable.getOzone());
            hours.add(hour);
        }

        return hours;
    }
}