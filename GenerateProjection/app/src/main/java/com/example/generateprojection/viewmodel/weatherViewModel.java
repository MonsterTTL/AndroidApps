package com.example.generateprojection.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class weatherViewModel extends ViewModel {

    private static final String TAG = "weatherViewModel";
    private MutableLiveData<Boolean> flag = new MutableLiveData<>();

    private static final String SunnyWeather_Key = "rLrw3QR1WolaqQyG/";
    private static final String SunnyWeather_End = "/weather?alert=true&dailysteps=1&hourlysteps=24";
    private static final String SunnyWeather_Head = "https://api.caiyunapp.com/v2.6/";
    private static final String SunnyWeather_Test = "116.3176,39.9760";
    private static String BD_URL = "https://api.map.baidu.com/weather/v1/?district_id=222405&data_type=all&ak=LmYvC3j2GGiVN2FjGcqFpoCFekgfPtRc";
    public synchronized void requireData(){
        Request.Builder builder = new Request.Builder().url(SunnyWeather_Head+SunnyWeather_Key+SunnyWeather_Test+SunnyWeather_End);
        builder.method("GET",null);
        OkHttpClient client = new OkHttpClient();
        Call mCall = client.newCall(builder.build());
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: "+response.body().string());
            }
        });

    }

}
