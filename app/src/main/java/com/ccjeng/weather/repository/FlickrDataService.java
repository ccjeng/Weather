package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.flickr.Flickr;
import com.ccjeng.weather.model.flickr.Photo;
import com.ccjeng.weather.model.flickr.PhotoInfo.PhotoInfo;
import com.ccjeng.weather.utils.Constant;
import com.ccjeng.weather.view.base.BaseApplication;

import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by andycheng on 2016/10/9.
 */

public class FlickrDataService {

    static volatile Retrofit retrofit = null;

    public FlickrDataService(){
    }

    public static Retrofit getClient() {
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
                            .baseUrl(Constant.FLICKR_ENDPOINT)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okhttpClient)
                            .build();
                }
            }
        }
        return retrofit;

    }


    public Observable<Photo> getPhotoData(City city) {

        PhotoServiceEndpoint service = getClient().create(PhotoServiceEndpoint.class);

        return service.getPhotos(Constant.FLICKR_APIKEY, city.getLat().toString(), city.getLon().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Flickr, Photo>() {
                    @Override
                    public Photo call(Flickr flickr) {

                        int size = flickr.getPhotos().getPhoto().size();
                        int index;

                        if (size > 0) {
                            index = new Random().nextInt(size);
                        } else {
                            index = 0;
                        }

                        return flickr.getPhotos().getPhoto().get(index);
                    }
                });

    }

    public Observable<PhotoInfo> getPhotoInfo(String photoId) {

        PhotoServiceEndpoint service = getClient().create(PhotoServiceEndpoint.class);

        return service.getInfo(Constant.FLICKR_APIKEY, photoId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<PhotoInfo, PhotoInfo>() {
                    @Override
                    public PhotoInfo call(PhotoInfo photoInfo) {
                        return photoInfo;
                    }
                });

    }
}
