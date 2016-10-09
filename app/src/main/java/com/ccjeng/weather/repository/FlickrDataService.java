package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.City;
import com.ccjeng.weather.model.flickr.Flickr;
import com.ccjeng.weather.model.flickr.Photo;
import com.ccjeng.weather.utils.Constant;

import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by andycheng on 2016/10/9.
 */

public class FlickrDataService {

    public Observable<Photo> getPhotoData(City city) {

        PhotoServiceEndpoint service = HttpClient.getClient(Constant.FLICKR_ENDPOINT)
                .create(PhotoServiceEndpoint.class);

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
}
