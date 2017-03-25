package com.ccjeng.weather.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.forecastio.Currently;
import com.ccjeng.weather.utils.Constant;
import com.ccjeng.weather.utils.Formatter;
import com.ccjeng.weather.utils.Settings;
import com.mikepenz.iconics.view.IconicsImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/12.
 */
public class WeatherCurrentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = "WeatherCurrentAdapter";
    private Context context;
    private City city;

    private static final int TEMPERATURE = 0;
    private static final int CURRENT = 1;
    private static final int UPDATED = 2;

    private boolean celsius = true;

    public WeatherCurrentAdapter(City city) {
        this.city = city;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WeatherCurrentAdapter.TEMPERATURE) {
            return WeatherCurrentAdapter.TEMPERATURE;
        }
        if (position == WeatherCurrentAdapter.CURRENT) {
            return WeatherCurrentAdapter.CURRENT;
        }
        if (position == WeatherCurrentAdapter.UPDATED) {
            return WeatherCurrentAdapter.UPDATED;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        celsius = Settings.isCelsiusUnit(context);

        switch (viewType) {
            case TEMPERATURE:
                return new TemperatureViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_current_temperature, parent, false));
            case CURRENT:
                return new CurrentViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_current, parent, false));
            case UPDATED:
                return new UpdatedViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_update, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TEMPERATURE:
                ((TemperatureViewHolder) holder).bind(city);
                break;
            case CURRENT:
                ((CurrentViewHolder) holder).bind(city);
                break;
            case UPDATED:
                ((UpdatedViewHolder) holder).bind(city);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return city != null ? 3 : 0;
    }


    class TemperatureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.todayIcon)
        IconicsImageView todayIcon;
        @BindView(R.id.todaySummary)
        TextView todaySummary;
        @BindView(R.id.todayTemperature)
        TextView todayTemperature;
        @BindView(R.id.sunrisetime)
        TextView sunriseTime;
        @BindView(R.id.sunsettime)
        TextView sunsetTime;
        @BindView(R.id.currenttime)
        TextView currentTime;
        @BindView(R.id.cardView)
        CardView cardView;

        public TemperatureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                cardView.getBackground().setAlpha(Constant.ALPHA_VALUE);

                todayIcon.setIcon(city.getCityWeather().getCurrently().getIconImage(context));
                todayIcon.setColor(city.getCityWeather().getCurrently().getIconColor(context));

                todayTemperature.setText(Formatter.formatTemperature(city.getCityWeather().getCurrently().getTemperature(), celsius) + " °" );
                todaySummary.setText(city.getCityWeather().getCurrently().getSummary());

                sunriseTime.setText(context.getString(R.string.sunrise,
                        Formatter.convertTimeByTimeZone(city.getCityWeather().getDaily().getDay().get(0).getSunriseTime(), city.getCityWeather().getTimezone())));
                sunsetTime.setText(context.getString(R.string.sunset,
                        Formatter.convertTimeByTimeZone(city.getCityWeather().getDaily().getDay().get(0).getSunsetTime(), city.getCityWeather().getTimezone())));
                currentTime.setText(context.getString(R.string.current,
                        Formatter.getCurrentTimeByTimeZone(city.getCityWeather().getTimezone())));


            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    class CurrentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.todayFeelLike)
        TextView todayFeelLike;
        @BindView(R.id.todayWind)
        TextView todayWind;
        @BindView(R.id.todayWindDirection)
        IconicsImageView todayWindDirection;
        @BindView(R.id.todayPressure)
        TextView todayPressure;
        @BindView(R.id.todayHumidity)
        TextView todayHumidity;
        @BindView(R.id.todayCloudCover)
        TextView todayCloudCover;
        @BindView(R.id.todayPrecipitation)
        TextView todayPrecipitation;

        @BindView(R.id.cardView)
        CardView cardView;

        public CurrentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                cardView.getBackground().setAlpha(Constant.ALPHA_VALUE);

                Currently currently = city.getCityWeather().getCurrently();
                todayFeelLike.setText(Formatter.formatTemperature(currently.getApparentTemperature(), celsius) + " °");

                todayWind.setText(currently.getWindSpeed() + " kmh ");
                todayWindDirection.setIcon(currently.getWindDirectionIcon(context));

                todayPressure.setText(Formatter.DoubleToString(currently.getPressure()) + " hPa");

                todayHumidity.setText(Formatter.DoubleToString(currently.getHumidity()*100) + " %");

                todayCloudCover.setText(Formatter.DoubleToString(currently.getCloudCover()*100) + " %");

                todayPrecipitation.setText(Formatter.DoubleToString(currently.getPrecipProbability()*100) + " %");

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    class UpdatedViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.update)
        TextView update;


        public UpdatedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                update.setText(context.getString(R.string.last_update,
                                  Formatter.formatTimeWithDayIfNotToday(context, city.getCityWeather().getFetchTime()))+ "\n\n\n\n");

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
