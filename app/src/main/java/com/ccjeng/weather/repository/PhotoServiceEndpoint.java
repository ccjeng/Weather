package com.ccjeng.weather.repository;

import com.ccjeng.weather.model.flickr.Flickr;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by andycheng on 2016/10/9.
 */

public interface PhotoServiceEndpoint {

    /** https://www.flickr.com/services/api/flickr.photos.search.html
     *
     * **/
//https://api.flickr.com/services/rest/?method=flickr.photos.search&group_id=1463451@N25&tag_mode=all&format=json&nojsoncallback=1&sort=random&api_key=dc7cb6cc8e546dd0fb6d3a1ba5bfa971

    @GET("?method=flickr.photos.search&group_id=1463451@N25&tag_mode=all&format=json&nojsoncallback=1&sort=random")
    Observable<Flickr> getPhotos(@Query("api_key") String APIKEY
            , @Query("lat") Double lat
            , @Query("lon") Double lon);
}
