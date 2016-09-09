package com.ccjeng.weather.repository;

/**
 * Created by andycheng on 2016/9/8.
 */
public interface IBaseRepository {

    interface onSaveCallback {
        void onSuccess(String cityName);
        void onError(String message);
    }

    interface onDeleteCallback {
        void onSuccess(String cityName);
        void onError(String message);
    }

    interface onUpdateCallback {
        void onSuccess(String cityName);
        void onError(String message);
    }
}
