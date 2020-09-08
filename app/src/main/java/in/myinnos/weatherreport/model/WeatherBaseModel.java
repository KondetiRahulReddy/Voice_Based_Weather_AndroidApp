package in.myinnos.weatherreport.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by myinnos on 26/04/2020.
 */

public class WeatherBaseModel {

   /* @SerializedName("coord")
    WeatherCoordModel weatherCoordModel;*/
    @SerializedName("main")
    WeatherMainModel weatherMainModel;
    @SerializedName("weather")
    List<WeatherWeatherModel> weatherWeatherModel;
    @SerializedName("wind")
    WeatherWindModel weatherWindModel;

    public WeatherBaseModel(WeatherMainModel weatherMainModel,
                            List<WeatherWeatherModel> weatherWeatherModel, WeatherWindModel weatherWindModel) {
        this.weatherMainModel = weatherMainModel;
        this.weatherWeatherModel = weatherWeatherModel;
        this.weatherWindModel = weatherWindModel;
    }

    public WeatherMainModel getWeatherMainModel() {
        return weatherMainModel;
    }

    public void setWeatherMainModel(WeatherMainModel weatherMainModel) {
        this.weatherMainModel = weatherMainModel;
    }

    public List<WeatherWeatherModel> getWeatherWeatherModel() {
        return weatherWeatherModel;
    }

    public void setWeatherWeatherModel(List<WeatherWeatherModel> weatherWeatherModel) {
        this.weatherWeatherModel = weatherWeatherModel;
    }

    public WeatherWindModel getWeatherWindModel() {
        return weatherWindModel;
    }

    public void setWeatherWindModel(WeatherWindModel weatherWindModel) {
        this.weatherWindModel = weatherWindModel;
    }
}
