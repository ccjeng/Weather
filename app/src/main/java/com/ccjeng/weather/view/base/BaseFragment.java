package com.ccjeng.weather.view.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ccjeng.weather.presenter.base.BasePresenter;

/**
 * Created by andycheng on 2016/9/7.
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    public T presenter;


    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    protected abstract T createPresenter();

}
