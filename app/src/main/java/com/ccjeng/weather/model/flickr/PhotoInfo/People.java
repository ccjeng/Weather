
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class People {

    @SerializedName("haspeople")
    @Expose
    private Integer haspeople;

    /**
     * 
     * @return
     *     The haspeople
     */
    public Integer getHaspeople() {
        return haspeople;
    }

    /**
     * 
     * @param haspeople
     *     The haspeople
     */
    public void setHaspeople(Integer haspeople) {
        this.haspeople = haspeople;
    }

}
