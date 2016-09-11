package com.ccjeng.weather.view.activity.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.utils.Formatter;
import com.mikepenz.iconics.view.IconicsImageView;

/**
 * Created by andycheng on 2016/9/11.
 */
public class CurrentView {

    private final String TAG = this.getClass().getSimpleName();

    private View view;
    private Context context;
    private City city;

    private TextView todayTemperature;
    private TextView todaySummary;
    private TextView todayWind;
    private TextView todayPressure;
    private TextView todayHumidity;
    private TextView todayCloudCover;
    private TextView todayPrecipitation;
    private IconicsImageView todayIcon;
    private TextView todayFeelLike;
    private TextView lastUpdate;
    private boolean celsius = true;

    public CurrentView(View view, Context context, City city) {
        this.view = view;
        this.context = context;
        this.city = city;

        todayTemperature = (TextView) view.findViewById(R.id.todayTemperature);
        todaySummary = (TextView) view.findViewById(R.id.todaySummary);
        todayWind = (TextView) view.findViewById(R.id.todayWind);
        todayPressure = (TextView) view.findViewById(R.id.todayPressure);
        todayHumidity = (TextView) view.findViewById(R.id.todayHumidity);
        todayCloudCover = (TextView) view.findViewById(R.id.todayCloudCover);
        todayPrecipitation = (TextView) view.findViewById(R.id.todayPrecipitation);
        todayIcon = (IconicsImageView) view.findViewById(R.id.todayIcon);
        todayFeelLike = (TextView) view.findViewById(R.id.todayFeelLike);
        lastUpdate = (TextView) view.findViewById(R.id.lastUpdate);

    }


    public void setData() {
        todayTemperature.setText(Formatter.formatTemperature(city.getCityWeather().getCurrently().getTemperature(), celsius) + " °" );
        todaySummary.setText(city.getCityWeather().getCurrently().getSummary());

        todayIcon.setIcon(city.getCityWeather().getCurrently().getIconImage(context));
        todayIcon.setColor(city.getCityWeather().getCurrently().getIconColor(context));

        todayFeelLike.setText(Formatter.formatTemperature(city.getCityWeather().getCurrently().getApparentTemperature(), celsius) + " °");

        todayWind.setText(city.getCityWeather().getCurrently().getWindSpeed() + "kmh "
                + Formatter.getWindBearingString(city.getCityWeather().getCurrently().getWindBearing()));

        todayPressure.setText(Formatter.DoubleToString(city.getCityWeather().getCurrently().getPressure()) + " hPa");

        todayHumidity.setText(city.getCityWeather().getCurrently().getHumidity() + " %");

        todayCloudCover.setText(city.getCityWeather().getCurrently().getCloudCover()*100 + " %");

        todayPrecipitation.setText(Formatter.DoubleToString(city.getCityWeather().getCurrently().getPrecipProbability()*100) + " % ( "
                + Formatter.DoubleToString(city.getCityWeather().getCurrently().getPrecipIntensity()*100) + " cm)");

        lastUpdate.setText(context.getString(R.string.last_update,
                Formatter.formatTimeWithDayIfNotToday(context, city.getCityWeather().getFetchTime())));


    }
}
