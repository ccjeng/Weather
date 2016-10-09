
package com.ccjeng.weather.model.flickr.PhotoInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("accuracy")
    @Expose
    private String accuracy;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("neighbourhood")
    @Expose
    private Neighbourhood neighbourhood;
    @SerializedName("locality")
    @Expose
    private Locality locality;
    @SerializedName("county")
    @Expose
    private County county;
    @SerializedName("region")
    @Expose
    private Region region;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("woeid")
    @Expose
    private String woeid;

    /**
     * 
     * @return
     *     The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The accuracy
     */
    public String getAccuracy() {
        return accuracy;
    }

    /**
     * 
     * @param accuracy
     *     The accuracy
     */
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * 
     * @return
     *     The context
     */
    public String getContext() {
        return context;
    }

    /**
     * 
     * @param context
     *     The context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * 
     * @return
     *     The neighbourhood
     */
    public Neighbourhood getNeighbourhood() {
        return neighbourhood;
    }

    /**
     * 
     * @param neighbourhood
     *     The neighbourhood
     */
    public void setNeighbourhood(Neighbourhood neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    /**
     * 
     * @return
     *     The locality
     */
    public Locality getLocality() {
        return locality;
    }

    /**
     * 
     * @param locality
     *     The locality
     */
    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    /**
     * 
     * @return
     *     The county
     */
    public County getCounty() {
        return county;
    }

    /**
     * 
     * @param county
     *     The county
     */
    public void setCounty(County county) {
        this.county = county;
    }

    /**
     * 
     * @return
     *     The region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * 
     * @return
     *     The country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(Country country) {
        this.country = country;
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
