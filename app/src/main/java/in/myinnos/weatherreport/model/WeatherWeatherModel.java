package in.myinnos.weatherreport.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by myinnos on 26/04/2020.
 */

public class WeatherWeatherModel {

    @SerializedName("description")
    String description;
    @SerializedName("main")
    String main;

    public WeatherWeatherModel(String description, String main) {
        this.description = description;
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
