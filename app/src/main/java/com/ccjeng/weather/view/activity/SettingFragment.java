package com.ccjeng.weather.view.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.ccjeng.weather.R;

/**
 * Created by andycheng on 2016/10/7.
 */

public class SettingFragment  extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

    }

}