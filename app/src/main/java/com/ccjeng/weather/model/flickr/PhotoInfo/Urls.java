
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Urls {

    @SerializedName("url")
    @Expose
    private List<Url> url = new ArrayList<Url>();

    /**
     * 
     * @return
     *     The url
     */
    public List<Url> getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(List<Url> url) {
        this.url = url;
    }

}
