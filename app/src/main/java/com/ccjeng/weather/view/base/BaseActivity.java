package com.ccjeng.weather.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ccjeng.weather.presenter.base.BasePresenter;

/**
 * Created by andycheng on 2016/9/5.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
        protected T mPresenter;


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

}
