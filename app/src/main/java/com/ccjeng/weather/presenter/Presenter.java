package com.ccjeng.weather.presenter;

import android.os.Bundle;

/**
 * Created by andycheng on 2016/9/5.
 */
public interface Presenter {

    void onStart();

    void onStop();

    void onPause();

    void attachIncomingArg(Bundle intent);

    void onCreate();

    void onDestroy();
}
