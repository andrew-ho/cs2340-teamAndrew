package model;

/**
 * @author Andrew Ho
 * @version 1.0
 * Created by teamAndrew on 7/11/2017.
 */

public class LocationItems {
    private double latitude;
    private double longitude;

    /**
     * Default Constructor.
     */
    public LocationItems() {

    }

    /**
     * gets item latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * sets item latitude
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * get item longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * set item longitude
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * constructor for the locationItem
     * @param latitude the latitude of the item location
     * @param longitude the longitude of the item location
     */
    public LocationItems(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
