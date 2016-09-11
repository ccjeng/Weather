package com.ccjeng.weather.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.utils.WeatherPagerEnum;
import com.ccjeng.weather.view.activity.viewholder.CurrentView;

/**
 * Created by andycheng on 2016/9/11.
 */
public class TabFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_CITY = "city";
    private City city;

    public TabFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabFragment newInstance(int sectionNumber, City city) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putSerializable(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        WeatherPagerEnum customPagerEnum = WeatherPagerEnum.values()[getArguments().getInt(ARG_SECTION_NUMBER)];
        City city = (City) getArguments().getSerializable(ARG_CITY);

        View rootView = inflater.inflate(customPagerEnum.getLayoutResId(), container, false);

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
            case 0:
                CurrentView currentView = new CurrentView(rootView, getActivity(), city);
                currentView.setData();
                break;

            case 1:

                break;

            case 2:

                break;
        }

        return rootView;
    }
}