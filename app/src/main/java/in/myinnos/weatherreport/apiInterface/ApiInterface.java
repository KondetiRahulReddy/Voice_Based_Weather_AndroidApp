package in.myinnos.weatherreport.apiInterface;

import in.myinnos.weatherreport.model.WeatherBaseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    ////////////////////////////////////////////////////////////////////////////////

    @GET("weather")
    Call<WeatherBaseModel> getWeatherData(
            @Query("appid") String appId,
            @Query("q") String query,
            @Query("units") String units
    );
}