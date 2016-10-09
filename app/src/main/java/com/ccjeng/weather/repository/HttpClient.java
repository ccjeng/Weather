package com.ccjeng.weather.repository;

import com.ccjeng.weather.view.base.BaseApplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andycheng on 2016/10/9.
 */

public class HttpClient {

    static volatile Retrofit retrofit = null;

    private HttpClient(){
    }

    public static Retrofit getClient(String url) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BaseApplication.APPDEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        if (retrofit == null) {
            synchronized (NetworkDataSource.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okhttpClient)
                            .build();
                }
            }
        }
        return retrofit;

    }

}
