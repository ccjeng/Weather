package com.ccjeng.weather.view.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.ccjeng.weather.utils.Formatter;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.ArrayList;
import java.util.List;

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
        if (position == WeatherDaysAdapter.DAYS) {
            return WeatherDaysAdapter.DAYS;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        switch (viewType) {
            case SUMMARY:
                return new SummaryViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_summary, parent, false));
            case DAYS:
                return new DaysViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_days, parent, false));
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
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return city != null ? 2 : 0;
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        IconicsImageView icon;
        @BindView(R.id.summary)
        TextView summary;
        @BindView(R.id.chart)
        CombinedChart chart;

        public SummaryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(City city) {
            try {
                icon.setIcon(city.getCityWeather().getDaily().getIconImage(context));
                icon.setColor(city.getCityWeather().getDaily().getIconColor(context));
                summary.setText(city.getCityWeather().getDaily().getSummary());

                //

                List<Day> day = city.getCityWeather().getDaily().getDay();
                ArrayList<String> xVals = new ArrayList<String>();
                for (int i = 0; i < day.size(); i++) {
                    xVals.add(Formatter.getWeekNameLocate(day.get(i).getTime()));
                }
                chart.getAxisLeft().setEnabled(false);
                chart.getAxisRight().setEnabled(true);
                chart.setBackgroundColor(Color.WHITE);
                chart.setDrawBorders(false);
                chart.setDragEnabled(false);
                chart.setTouchEnabled(false);
                chart.setPinchZoom(false);
                chart.setScaleEnabled(false);
                chart.setDrawGridBackground(false);
                chart.setDescription("");

                chart.setDrawOrder(new CombinedChart.DrawOrder[] {
                        CombinedChart.DrawOrder.BAR,  CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE
                });


                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

                YAxis rightAxis = chart.getAxisRight();
                rightAxis.setDrawGridLines(false);
                rightAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

                YAxis leftAxis = chart.getAxisLeft();
                leftAxis.setDrawGridLines(false);

                CombinedData data = new CombinedData(xVals);

                data.setData(generateLineData());
                chart.animateX(1000);
                chart.setData(data);
                chart.invalidate();

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }

        private LineData generateLineData() {

            LineData d = new LineData();
/*
            ArrayList<Entry> entries = new ArrayList<Entry>();

            for (int i = 0; i < stockItems.size(); i++) {
                entries.add(new Entry(Float.valueOf(stockItems.get(i).getClose()), i));
            }

            LineDataSet set = new LineDataSet(entries, currentStock.getName());
            set.setDrawCircles(false);
            //set.setDrawCubic(true);
            set.setDrawFilled(false);
            //historicalDataSet.setFillAlpha(GRAPHIC_FILL_ALPHA);
            set.setCubicIntensity(GRAPHIC_CUBIC_INTENSITY);
            set.setLineWidth(GRAPHIC_LINE_WIDTH);
            set.setColor(context.getResources().getColor(R.color.colorPrimary));

            set.setDrawValues(false);

            set.setAxisDependency(YAxis.AxisDependency.LEFT);

            d.addDataSet(set);
*/
            return d;

        }
    }

    class DaysViewHolder extends RecyclerView.ViewHolder {

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

            List<Day> day = city.getCityWeather().getDaily().getDay();

            try {
                dayName[0].setText("Today");
                dayName[1].setText("Tomorrow");
                for(int i = 0; i < day.size(); i++) {
                    if(i > 1) {
                        dayName[i].setText(Formatter.getWeekNameLocate(day.get(i).getTime()));
                    }
                    tempMax[i].setText(Formatter.formatTemperature(day.get(i).getTemperatureMax(), true) + " °");
                    tempMin[i].setText(Formatter.formatTemperature(day.get(i).getTemperatureMin(), true) + " °");
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
}
