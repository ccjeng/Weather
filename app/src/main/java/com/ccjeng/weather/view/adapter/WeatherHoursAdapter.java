package com.ccjeng.weather.view.adapter;

import android.content.Context;
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
import com.ccjeng.weather.model.forecastio.Hour;
import com.ccjeng.weather.utils.Formatter;
import com.ccjeng.weather.utils.Settings;
import com.github.mikephil.charting.charts.LineChart;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andycheng on 2016/9/12.
 */
public class WeatherHoursAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = "WeatherHoursAdapter";
    private Context context;
    private City city;

    private static final int SUMMARY = 0;
    private static final int HOURS = 1;
    private boolean celsius = true;

    public WeatherHoursAdapter(City city) {
        this.city = city;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WeatherHoursAdapter.SUMMARY) {
            return WeatherHoursAdapter.SUMMARY;
        }
        if (position == WeatherHoursAdapter.HOURS) {
            return WeatherHoursAdapter.HOURS;
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
            case HOURS:
                return new HoursViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_hours, parent, false));
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
            case HOURS:
                ((HoursViewHolder) holder).bind(city);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return city != null ? 2 : 0;
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
                cardView.getBackground().setAlpha(100);

                icon.setIcon(city.getCityWeather().getHourly().getIconImage(context));
                icon.setColor(city.getCityWeather().getHourly().getIconColor(context));
                summary.setText(city.getCityWeather().getHourly().getSummary());

                mChart.setVisibility(View.GONE);

                //chart
                /*
                List<Hour> hours = city.getCityWeather().getHourly().getHour();

                mChart.setDrawGridBackground(false);
                mChart.setDrawBorders(false);

                mChart.getAxisLeft().setEnabled(false);
                mChart.getAxisRight().setEnabled(false);
                mChart.getXAxis().setDrawAxisLine(false);
                mChart.getXAxis().setDrawGridLines(false);
                mChart.setDescription("Temperature");
                mChart.setTouchEnabled(false);
                mChart.setAutoScaleMinMaxEnabled(true);
                Legend l = mChart.getLegend();
                l.setEnabled(false);

                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

                //Line Temperature
                ArrayList<Entry> maxTempValues = new ArrayList<Entry>();
                for (int i = 0; i < hours.size(); i++) {
                    String maxTemp = Formatter.formatTemperature(hours.get(i).getApparentTemperature(), true);
                    maxTempValues.add(new Entry(i, Float.valueOf(maxTemp)));
                }

                LineDataSet avgTemp = new LineDataSet(maxTempValues, "");
                avgTemp.setLineWidth(2.5f);
                avgTemp.setDrawCircles(false);
                //avgTemp.setCircleRadius(4f);
                avgTemp.setValueTextSize(0f);
                dataSets.add(avgTemp);

                XAxis xAxis = mChart.getXAxis();
                xAxis.setEnabled(false);

                LineData data = new LineData(dataSets);
                mChart.setData(data);
                mChart.invalidate();

*/
            } catch (Exception e) {
                Log.e(TAG, "bind = " + e.toString());
            }
        }
    }

    class HoursViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardView)
        CardView cardView;
        private List<Hour> hour = city.getCityWeather().getHourly().getHour();
        private LinearLayout hourLinear;
        private TextView[] time = new TextView[hour.size()];
        private TextView[] temp = new TextView[hour.size()];
        private TextView[] rain = new TextView[hour.size()];
        private IconicsImageView[] icon = new IconicsImageView[hour.size()];

        public HoursViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            hourLinear = (LinearLayout) itemView.findViewById(R.id.linear);
            for (int i = 0; i < hour.size(); i++) {
                View view = View.inflate(context, R.layout.item_hours_line, null);
                time[i] = (TextView) view.findViewById(R.id.one_clock);
                rain[i] = (TextView) view.findViewById(R.id.one_precipitation);
                temp[i] = (TextView) view.findViewById(R.id.one_temp);
                icon[i] = (IconicsImageView) view.findViewById(R.id.icon);
                hourLinear.addView(view);
            }

        }

        public void bind(City city) {

            cardView.getBackground().setAlpha(100);

            try {
                List<Hour> hour = city.getCityWeather().getHourly().getHour();
                for(int i = 0; i < hour.size(); i++) {
                    time[i].setText(Formatter.formatTimeToString(hour.get(i).getTime(), context));
                    rain[i].setText(Formatter.DoubleToString(hour.get(i).getPrecipProbability()*100) + " %");
                    temp[i].setText(Formatter.formatTemperature(hour.get(i).getApparentTemperature(),celsius) + " °");
                    icon[i].setIcon(hour.get(i).getIconImage(context));
                    icon[i].setColor(hour.get(i).getIconColor(context));
                }

            } catch (Exception e) {
                Log.e(TAG, "HoursViewHolder bind = " + e.toString());
            }
        }
    }
}
