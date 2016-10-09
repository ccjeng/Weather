
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class County {

    @SerializedName("_content")
    @Expose
    private String content;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("woeid")
    @Expose
    private String woeid;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The _content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The woeid
     */
    public String getWoeid() {
        return woeid;
    }

    /**
     * 
     * @param woeid
     *     The woeid
     */
    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

}
