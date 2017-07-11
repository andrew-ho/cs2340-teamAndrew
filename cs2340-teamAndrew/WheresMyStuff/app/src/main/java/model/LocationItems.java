package model;

/**
 * @author Andrew Ho
 * @version 1.0
 * Created by teamAndrew on 7/11/2017.
 */

public class LocationItems {
    private double latitude;
    private double longitude;
    public LocationItems() {

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocationItems(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
