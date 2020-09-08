package in.myinnos.weatherreport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myinnos on 26/04/2020.
 */

public class WeatherMainModel {

    @SerializedName("temp")
    Double temp;
    @SerializedName("pressure")
    int pressure;
    @SerializedName("humidity")
    int humidity;
    @SerializedName("temp_min")
    Double temp_min;
    @SerializedName("temp_max")
    Double temp_max;

    public WeatherMainModel(Double temp, int pressure, int humidity, Double temp_min, Double temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }
}
