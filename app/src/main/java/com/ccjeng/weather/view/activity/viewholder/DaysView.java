package com.ccjeng.weather.view.activity.viewholder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ccjeng.weather.R;
import com.ccjeng.weather.model.City;
import com.ccjeng.weather.view.adapter.WeatherDaysAdapter;

/**
 * Created by andycheng on 2016/9/12.
 */
public class DaysView {
    private final String TAG = this.getClass().getSimpleName();

    private View view;
    private Context context;
    private City city;
    private RecyclerView recyclerView;

    public DaysView(View view, Context context, City city) {
        this.view = view;
        this.context = context;
        this.city = city;

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setData() {
        WeatherDaysAdapter adapter = new WeatherDaysAdapter(city);
        recyclerView.setAdapter(adapter);
    }
}
