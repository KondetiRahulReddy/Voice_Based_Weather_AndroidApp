package in.myinnos.weatherreport;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {


    public static final String TAG = BaseApplication.class.getSimpleName();

    private static BaseApplication mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //logUser();
        mInstance = this;
    }
}
