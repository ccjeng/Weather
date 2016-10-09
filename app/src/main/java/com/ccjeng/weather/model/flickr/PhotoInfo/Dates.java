
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dates {

    @SerializedName("posted")
    @Expose
    private String posted;
    @SerializedName("taken")
    @Expose
    private String taken;
    @SerializedName("takengranularity")
    @Expose
    private String takengranularity;
    @SerializedName("takenunknown")
    @Expose
    private Integer takenunknown;
    @SerializedName("lastupdate")
    @Expose
    private String lastupdate;

    /**
     * 
     * @return
     *     The posted
     */
    public String getPosted() {
        return posted;
    }

    /**
     * 
     * @param posted
     *     The posted
     */
    public void setPosted(String posted) {
        this.posted = posted;
    }

    /**
     * 
     * @return
     *     The taken
     */
    public String getTaken() {
        return taken;
    }

    /**
     * 
     * @param taken
     *     The taken
     */
    public void setTaken(String taken) {
        this.taken = taken;
    }

    /**
     * 
     * @return
     *     The takengranularity
     */
    public String getTakengranularity() {
        return takengranularity;
    }

    /**
     * 
     * @param takengranularity
     *     The takengranularity
     */
    public void setTakengranularity(String takengranularity) {
        this.takengranularity = takengranularity;
    }

    /**
     * 
     * @return
     *     The takenunknown
     */
    public Integer getTakenunknown() {
        return takenunknown;
    }

    /**
     * 
     * @param takenunknown
     *     The takenunknown
     */
    public void setTakenunknown(Integer takenunknown) {
        this.takenunknown = takenunknown;
    }

    /**
     * 
     * @return
     *     The lastupdate
     */
    public String getLastupdate() {
        return lastupdate;
    }

    /**
     * 
     * @param lastupdate
     *     The lastupdate
     */
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

}
