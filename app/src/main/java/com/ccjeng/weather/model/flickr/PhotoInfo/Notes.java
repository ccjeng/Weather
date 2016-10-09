
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Notes {

    @SerializedName("note")
    @Expose
    private List<Object> note = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The note
     */
    public List<Object> getNote() {
        return note;
    }

    /**
     * 
     * @param note
     *     The note
     */
    public void setNote(List<Object> note) {
        this.note = note;
    }

}
