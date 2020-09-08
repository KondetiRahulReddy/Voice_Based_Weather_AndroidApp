package in.myinnos.weatherreport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.algolia.instantsearch.voice.VoiceSpeechRecognizer;
import com.algolia.instantsearch.voice.ui.Voice;
import com.algolia.instantsearch.voice.ui.VoiceInputDialogFragment;
import com.algolia.instantsearch.voice.ui.VoicePermissionDialogFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.myinnos.weatherreport.apiInterface.ApiClient;
import in.myinnos.weatherreport.apiInterface.ApiInterface;
import in.myinnos.weatherreport.model.WeatherBaseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements VoiceSpeechRecognizer.ResultsListener,
        TextToSpeech.OnInitListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.liMainLayout)
    LinearLayout liMainLayout;

    @BindView(R.id.txCityName)
    TextView txCityName;
    @BindView(R.id.txWeather)
    TextView txWeather;
    @BindView(R.id.txWind)
    TextView txWind;
    @BindView(R.id.txCloudiness)
    TextView txCloudiness;
    @BindView(R.id.txPressure)
    TextView txPressure;
    @BindView(R.id.txHumidity)
    TextView txHumidity;

    @BindView(R.id.txSpeak)
    TextView txSpeak;

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tts = new TextToSpeech(this, this);
        getWeatherData("Hyderabad");
    }

    private void getWeatherData(String location) {

        String string_edited = location;
        if (location.toLowerCase().contains("weather")) {
            string_edited = string_edited.replace("weather", "");
            string_edited = string_edited.replace("in", "");
        }
        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService =
                ApiClient.getClient(true).create(ApiInterface.class);

        Call<WeatherBaseModel> weatherBaseModelCall =
                apiService.getWeatherData("e972b01b092d6945d0effe3014ff9ec9", string_edited, "metric");
        String url = String.valueOf(weatherBaseModelCall.request().url());
        Log.d("TAG_WEATHER", url);

        String finalString_edited = string_edited;
        weatherBaseModelCall.enqueue(new Callback<WeatherBaseModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherBaseModel> call, Response<WeatherBaseModel> response) {
                ////////////////////////////////// content empty view

                progressBar.setVisibility(View.GONE);

                try {
                    liMainLayout.setVisibility(View.VISIBLE);
                    txCityName.setText(finalString_edited.toUpperCase());
                    txWeather.setText(response.body().getWeatherMainModel().getTemp() + " \u2103");

                    txWind.setVisibility(View.VISIBLE);
                    txCloudiness.setVisibility(View.VISIBLE);
                    txPressure.setVisibility(View.VISIBLE);
                    txHumidity.setVisibility(View.VISIBLE);
                    txWind.setText(response.body().getWeatherWindModel().getSpeed() + " m/s speed");
                    txCloudiness.setText(response.body().getWeatherWeatherModel().get(0).getDescription());
                    txPressure.setText(response.body().getWeatherMainModel().getPressure() + " hpa pressure");
                    txHumidity.setText(response.body().getWeatherMainModel().getHumidity() + "% humidity");

                    speakOut(response.body().getWeatherMainModel().getTemp() + " celsius and "
                            + response.body().getWeatherWeatherModel().get(0).getDescription());

                    Log.d("TAG_WEATHER", String.valueOf(response.body().getWeatherMainModel().getHumidity()));

                } catch (Exception ignored) {
                    liMainLayout.setVisibility(View.GONE);
                    Toast toast = Toast.makeText(getApplicationContext(), "something went wrong! Please try with city name", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<WeatherBaseModel> call, Throwable t) {
                liMainLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Log.d("TAG_WEATHER", String.valueOf(t.getMessage()));
            }
        });
    }

    @OnClick(R.id.txSpeak)
    void setTxSpeak() {

        if (!Voice.isRecordAudioPermissionGranted(getApplicationContext())) {
            new VoicePermissionDialogFragment().show(getSupportFragmentManager(), "DIALOG_PERMISSION");
        } else {
            VoiceInputDialogFragment voiceInputDialogFragment = new VoiceInputDialogFragment();
            voiceInputDialogFragment.setSuggestions(
                    "London",
                    "New York",
                    "Hyderabad"
            );
            voiceInputDialogFragment.show(getSupportFragmentManager(), "DIALOG_INPUT");
            voiceInputDialogFragment.setAutoStart(true);
        }
    }

    @Override
    public void onResults(String[] strings) {
        getWeatherData(strings[0]);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut("HI!");
            }
        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
