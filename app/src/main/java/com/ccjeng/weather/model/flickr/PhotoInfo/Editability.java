
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Editability {

    @SerializedName("cancomment")
    @Expose
    private Integer cancomment;
    @SerializedName("canaddmeta")
    @Expose
    private Integer canaddmeta;

    /**
     * 
     * @return
     *     The cancomment
     */
    public Integer getCancomment() {
        return cancomment;
    }

    /**
     * 
     * @param cancomment
     *     The cancomment
     */
    public void setCancomment(Integer cancomment) {
        this.cancomment = cancomment;
    }

    /**
     * 
     * @return
     *     The canaddmeta
     */
    public Integer getCanaddmeta() {
        return canaddmeta;
    }

    /**
     * 
     * @param canaddmeta
     *     The canaddmeta
     */
    public void setCanaddmeta(Integer canaddmeta) {
        this.canaddmeta = canaddmeta;
    }

}
