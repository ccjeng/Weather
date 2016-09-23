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
import com.ccjeng.weather.utils.Formatter;
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
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case TEMPERATURE:
                return new TemperatureViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_current_temperature, parent, false));
            case CURRENT:
                return new CurrentViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_current, parent, false));
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
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return city != null ? 2 : 0;
    }


    class TemperatureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.todayIcon)
        IconicsImageView todayIcon;
        @BindView(R.id.todaySummary)
        TextView todaySummary;
        @BindView(R.id.todayTemperature)
        TextView todayTemperature;
        @BindView(R.id.cardView)
        CardView cardView;

        public TemperatureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                todayIcon.setIcon(city.getCityWeather().getCurrently().getIconImage(context));
                todayIcon.setColor(city.getCityWeather().getCurrently().getIconColor(context));

                todayTemperature.setText(Formatter.formatTemperature(city.getCityWeather().getCurrently().getTemperature(), true) + " °" );
                todaySummary.setText(city.getCityWeather().getCurrently().getSummary());

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
                todayFeelLike.setText(Formatter.formatTemperature(city.getCityWeather().getCurrently().getApparentTemperature(), true) + " °");

                todayWind.setText(city.getCityWeather().getCurrently().getWindSpeed() + " kmh "
                        + Formatter.getWindBearingString(city.getCityWeather().getCurrently().getWindBearing()));

                todayPressure.setText(Formatter.DoubleToString(city.getCityWeather().getCurrently().getPressure()) + " hPa");

                todayHumidity.setText(Formatter.DoubleToString(city.getCityWeather().getCurrently().getHumidity()*100) + " %");

                todayCloudCover.setText(Formatter.DoubleToString(city.getCityWeather().getCurrently().getCloudCover()*100) + " %");

                todayPrecipitation.setText(Formatter.DoubleToString(city.getCityWeather().getCurrently().getPrecipProbability()*100) + " % ( "
                        + Formatter.DoubleToString(city.getCityWeather().getCurrently().getPrecipIntensity()*100) + " cm)");

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
