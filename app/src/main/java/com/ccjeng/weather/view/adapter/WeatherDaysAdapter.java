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
public class WeatherDaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private City city;

    private static final int SUMMARY = 0;
    private static final int DAYS = 1;

    public WeatherDaysAdapter(City city) {
        this.city = city;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == WeatherDaysAdapter.SUMMARY) {
            return WeatherDaysAdapter.SUMMARY;
        }
        //   if (position == WeatherDaysAdapter.DAYS) {
        //       return WeatherDaysAdapter.DAYS;
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
            //   case DAYS:
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
            //   case DAYS:
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
                icon.setIcon(city.getCityWeather().getDaily().getIconImage(context));
                icon.setColor(city.getCityWeather().getDaily().getIconColor(context));
                summary.setText(city.getCityWeather().getDaily().getSummary());

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    class DaysViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.cardView)
        //CardView cardView;

        public DaysViewHolder(View itemView) {
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
