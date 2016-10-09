
package com.ccjeng.weather.model.flickr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flickr {

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    /**
     * 
     * @return
     *     The photos
     */
    public Photos getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    /**
     * 
     * @return
     *     The stat
     */
    public String getStat() {
        return stat;
    }

    /**
     * 
     * @param stat
     *     The stat
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

}
