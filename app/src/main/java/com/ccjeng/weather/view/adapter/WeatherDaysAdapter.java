package com.ccjeng.weather.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.forecastio.Day;
import com.ccjeng.weather.utils.Constant;
import com.ccjeng.weather.utils.Formatter;
import com.ccjeng.weather.utils.Settings;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/12.
 */
public class WeatherDaysAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "WeatherDaysAdapter";
    private Context context;
    private City city;

    private static final int SUMMARY = 0;
    private static final int DAYS = 1;
    private static final int UPDATED = 2;

    private boolean celsius = true;

    public WeatherDaysAdapter(City city) {
        this.city = city;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WeatherDaysAdapter.SUMMARY) {
            return WeatherDaysAdapter.SUMMARY;
        }
        if (position == WeatherDaysAdapter.DAYS) {
            return WeatherDaysAdapter.DAYS;
        }
        if (position == WeatherDaysAdapter.UPDATED) {
            return WeatherDaysAdapter.UPDATED;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        celsius = Settings.isCelsiusUnit(context);

        switch (viewType) {
            case SUMMARY:
                return new SummaryViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_summary, parent, false));
            case DAYS:
                return new DaysViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_days, parent, false));
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
            case SUMMARY:
                ((SummaryViewHolder) holder).bind(city);
                break;
            case DAYS:
                ((DaysViewHolder) holder).bind(city);
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

    class SummaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.icon)
        IconicsImageView icon;
        @BindView(R.id.summary)
        TextView summary;
        @BindView(R.id.chart)
        LineChart mChart;

        public SummaryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                cardView.getBackground().setAlpha(Constant.ALPHA_VALUE);

                icon.setIcon(city.getCityWeather().getDaily().getIconImage(context));
                icon.setColor(city.getCityWeather().getDaily().getIconColor(context));
                summary.setText(city.getCityWeather().getDaily().getSummary());

                //Set Chart
                List<Day> day = city.getCityWeather().getDaily().getDay();

                mChart.setDrawGridBackground(false);
                mChart.setDrawBorders(false);

                mChart.getAxisLeft().setEnabled(false);
                mChart.getAxisRight().setEnabled(false);
                mChart.getXAxis().setDrawAxisLine(false);
                mChart.getXAxis().setDrawGridLines(false);
                mChart.getDescription().setEnabled(false);
                mChart.setTouchEnabled(false);
                mChart.setAutoScaleMinMaxEnabled(true);
                Legend l = mChart.getLegend();
                l.setEnabled(false);

                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

                //Line Max Temperature
                ArrayList<Entry> maxTempValues = new ArrayList<Entry>();
                for (int i = 0; i < day.size(); i++) {
                    String maxTemp = Formatter.formatTemperature(day.get(i).getTemperatureMax(), celsius);
                    maxTempValues.add(new Entry(i, Float.valueOf(maxTemp)));
                }

                LineDataSet max = new LineDataSet(maxTempValues, "");
                max.setLineWidth(2.5f);
                max.setCircleRadius(4f);
                max.setValueTextSize(11f);
                dataSets.add(max);

                //Line Min Temperature
                ArrayList<Entry> minTempValues = new ArrayList<Entry>();
                for (int i = 0; i < day.size(); i++) {
                    String minTemp = Formatter.formatTemperature(day.get(i).getTemperatureMin(), celsius);
                    minTempValues.add(new Entry(i, Float.valueOf(minTemp)));
                }

                LineDataSet min = new LineDataSet(minTempValues, "");
                min.setLineWidth(2.5f);
                min.setCircleRadius(4f);
                min.setValueTextSize(11f);
                dataSets.add(min);

                XAxis xAxis = mChart.getXAxis();
                xAxis.setEnabled(false);

                LineData data = new LineData(dataSets);
                data.setValueTextColor(Color.rgb(129, 212, 250));
                mChart.setData(data);
                mChart.invalidate();


            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }


    }

    class DaysViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        CardView cardView;
        private List<Day> day = city.getCityWeather().getDaily().getDay();
        private LinearLayout dayLinear;
        private TextView[] dayName = new TextView[day.size()];
        private TextView[] rainPre = new TextView[day.size()];
        private TextView[] tempMax = new TextView[day.size()];
        private TextView[] tempMin = new TextView[day.size()];
        private TextView[] summary = new TextView[day.size()];
        private IconicsImageView[] icon = new IconicsImageView[day.size()];

        public DaysViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            dayLinear = (LinearLayout) itemView.findViewById(R.id.linear);
            for (int i = 0; i < day.size(); i++) {
                View view = View.inflate(context, R.layout.item_days_line, null);
                dayName[i] = (TextView) view.findViewById(R.id.day);
                rainPre[i] = (TextView) view.findViewById(R.id.rain);
                tempMax[i] = (TextView) view.findViewById(R.id.tempMax);
                tempMin[i] = (TextView) view.findViewById(R.id.tempMin);
                summary[i] = (TextView) view.findViewById(R.id.summary);
                icon[i] = (IconicsImageView) view.findViewById(R.id.icon);
                dayLinear.addView(view);
            }

        }

        public void bind(City city) {

            cardView.getBackground().setAlpha(Constant.ALPHA_VALUE);

            List<Day> day = city.getCityWeather().getDaily().getDay();
            try {
               // dayName[0].setText("Today");
               // dayName[1].setText("Tomorrow");
                for(int i = 0; i < day.size(); i++) {
                   // if(i > 1) {
                    dayName[i].setText(Formatter.getWeekNameEnglish(day.get(i).getTime()));
                    //}
                    tempMax[i].setText(Formatter.formatTemperature(day.get(i).getTemperatureMax(), celsius) + " °");
                    tempMin[i].setText(Formatter.formatTemperature(day.get(i).getTemperatureMin(), celsius) + " °");
                    icon[i].setIcon(day.get(i).getIconImage(context));
                    icon[i].setColor(day.get(i).getIconColor(context));
                    summary[i].setText(day.get(i).getSummary());
                    rainPre[i].setText(Formatter.DoubleToString(day.get(i).getPrecipProbability()*100) + " %");
                }
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
