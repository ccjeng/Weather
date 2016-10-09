
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visibility {

    @SerializedName("ispublic")
    @Expose
    private Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    private Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    private Integer isfamily;

    /**
     * 
     * @return
     *     The ispublic
     */
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     * 
     * @param ispublic
     *     The ispublic
     */
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * 
     * @return
     *     The isfriend
     */
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     * 
     * @param isfriend
     *     The isfriend
     */
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     * 
     * @return
     *     The isfamily
     */
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     * 
     * @param isfamily
     *     The isfamily
     */
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

}
