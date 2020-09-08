package in.myinnos.weatherreport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myinnos on 26/04/2020.
 */

public class WeatherCoordModel {

    @SerializedName("lon")
    Long lon;
    @SerializedName("lat")
    Long lat;

    public WeatherCoordModel(Long lon, Long lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }
}
