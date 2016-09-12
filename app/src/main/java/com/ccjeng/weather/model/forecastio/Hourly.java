
package com.ccjeng.weather.model.forecastio;

import android.content.Context;

import com.ccjeng.weather.utils.IconManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hourly implements Serializable {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    private List<Hour> data = new ArrayList<Hour>();

    /**
     * 
     * @return
     *     The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 
     * @param summary
     *     The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    public IconicsDrawable getIconImage(Context context) {
        return IconManager.getIconResource(icon, context);
    }

    public int getIconColor(Context context) {
        return IconManager.getIconColor(icon, context);
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<Hour> getHour() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setHour(List<Hour> data) {
        this.data = data;
    }

}
