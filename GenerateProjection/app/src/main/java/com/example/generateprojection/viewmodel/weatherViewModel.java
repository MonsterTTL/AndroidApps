package com.example.generateprojection.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.generateprojection.helper.SunnyWeatherDB;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class weatherViewModel extends ViewModel {

    private static final String TAG = "weatherViewModel";
    public MutableLiveData<Boolean> flag = new MutableLiveData<>();
    private double jingdu = -1;//经度
    private double weidu = -1;//纬度


    //和彩云天气相关的字段
    private static final String SunnyWeather_Key = "rLrw3QR1WolaqQyG/";
    private static final String SunnyWeather_End = "/weather?alert=true&dailysteps=1&hourlysteps=24";
    private static final String SunnyWeather_Head = "https://api.caiyunapp.com/v2.6/";
    private static final String SunnyWeather_Test = "116.3176,39.9760";

    private SunnyWeatherDB[] weatherDBS = new SunnyWeatherDB[20];//实体类的数组
    private int SunnyCur = 0;

    private static String BD_URL = "https://api.map.baidu.com/weather/v1/?district_id=222405&data_type=all&ak=LmYvC3j2GGiVN2FjGcqFpoCFekgfPtRc";
    public synchronized void requireData(){
        if(jingdu == -1 && weidu == -1){
            return ;
        }else{
            Log.d(TAG, "requireData: "+jingdu+","+weidu);
        }
        Request.Builder builder = new Request.Builder().url(SunnyWeather_Head+SunnyWeather_Key+weidu+","+jingdu+SunnyWeather_End);
        builder.method("GET",null);
        OkHttpClient client = new OkHttpClient();
        Call mCall = client.newCall(builder.build());
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //Log.d(TAG, "onResponse: "+response.body().string());
                SunnyWeatherDB db = new Gson().fromJson(response.body().string(),SunnyWeatherDB.class);
                if(SunnyCur < weatherDBS.length){
                    weatherDBS[SunnyCur++] = db;
                }else{
                    Log.d(TAG, "weatherDBS"+"信息队列已满");
                }
                flag.postValue(true);
            }
        });

    }

    public void setLocationXY(double jingdu,double weidu){
        this.jingdu = jingdu;
        this.weidu = weidu;
        Log.d(TAG, "setLocationXY: "+"传递成功");
    }

    public SunnyWeatherDB getWeatherData(int position){
        if(position <= SunnyCur){
            return weatherDBS[position];
        }
        else{
            Log.d(TAG, "getWeatherData: "+"访问下标越界");
            return null;
        }
    }

}
