package com.ccjeng.weather.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.mikepenz.iconics.view.IconicsImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/12.
 */
public class WeatherHoursAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private City city;

    private static final int SUMMARY = 0;
    private static final int HOURS = 1;

    public WeatherHoursAdapter(City city) {
        this.city = city;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WeatherHoursAdapter.SUMMARY) {
            return WeatherHoursAdapter.SUMMARY;
        }
     //   if (position == WeatherHoursAdapter.HOURS) {
     //       return WeatherHoursAdapter.HOURS;
     //   }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case SUMMARY:
                return new SummaryViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_summary, parent, false));
         //   case HOURS:
         //       return new HoursViewHolder(
         //               LayoutInflater.from(context).inflate(R.layout.item_hours, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case SUMMARY:
                ((SummaryViewHolder) holder).bind(city);
                break;
         //   case HOURS:
         //       ((HoursViewHolder) holder).bind(city);
         //       break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return city != null ? 1 : 0;
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        IconicsImageView icon;
        @BindView(R.id.summary)
        TextView summary;
        //@BindView(R.id.cardView)
        //CardView cardView;

        public SummaryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                icon.setIcon(city.getCityWeather().getHourly().getIconImage(context));
                icon.setColor(city.getCityWeather().getHourly().getIconColor(context));
                summary.setText(city.getCityWeather().getHourly().getSummary());

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    class HoursViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.cardView)
        //CardView cardView;

        public HoursViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {


            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }
}
