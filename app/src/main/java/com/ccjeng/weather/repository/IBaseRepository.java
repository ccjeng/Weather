package com.ccjeng.weather.repository;

/**
 * Created by andycheng on 2016/9/8.
 */
public interface IBaseRepository {

    interface onSaveCallback {
        void onSuccess();
        void onError(String message);
    }

    interface onDeleteCallback {
        void onSuccess();
        void onError(String message);
    }

    interface onUpdateCallback {
        void onSuccess();
        void onError(String message);
    }
}
