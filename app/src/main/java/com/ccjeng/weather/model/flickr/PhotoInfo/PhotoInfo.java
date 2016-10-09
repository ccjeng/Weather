
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoInfo {

    @SerializedName("photo")
    @Expose
    private com.ccjeng.weather.model.flickr.Photo photo;
    @SerializedName("stat")
    @Expose
    private String stat;

    /**
     * 
     * @return
     *     The photo
     */
    public com.ccjeng.weather.model.flickr.Photo getPhoto() {
        return photo;
    }

    /**
     * 
     * @param photo
     *     The photo
     */
    public void setPhoto(com.ccjeng.weather.model.flickr.Photo photo) {
        this.photo = photo;
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
