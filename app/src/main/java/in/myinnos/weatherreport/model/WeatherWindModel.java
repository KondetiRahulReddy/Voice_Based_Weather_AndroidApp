package in.myinnos.weatherreport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myinnos on 26/04/2020.
 */

public class WeatherWindModel {

    @SerializedName("speed")
    Double speed;

    public WeatherWindModel(Double speed) {
        this.speed = speed;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }
}
