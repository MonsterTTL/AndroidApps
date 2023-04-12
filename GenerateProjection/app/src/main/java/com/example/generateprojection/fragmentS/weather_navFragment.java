package com.example.generateprojection.fragmentS;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.generateprojection.MainActivity;
import com.example.generateprojection.R;
import com.example.generateprojection.helper.SunnyWeatherDB;
import com.example.generateprojection.viewmodel.weatherViewModel;

import org.w3c.dom.Text;


public class weather_navFragment extends Fragment {

    private static final String TAG = "weather_navFragment";
    public weatherViewModel model;
    private TextView address;
    private TextView temperature;
    private TextView weather_data;
    private TextView ultraviolet_index;
    private TextView dress_index;
    private TextView comfort_index;
    private TextView coldRisk_index;
    private TextView air_index;
    private TextView pm25_index;

    Toolbar weatherToolbar;

    ProgressBar progressBar;

    public weather_navFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_nav, container, false);
        model = new ViewModelProvider(requireActivity()).get(weatherViewModel.class);
        address = view.findViewById(R.id.address);
        temperature = view.findViewById(R.id.tempreture_value2);
        weather_data = view.findViewById(R.id.weather_data);
        ultraviolet_index = view.findViewById(R.id.ultraviolet_index);
        comfort_index = view.findViewById(R.id.comfort_index);
        dress_index = view.findViewById(R.id.dress_index);
        coldRisk_index = view.findViewById(R.id.coldRisk);
        pm25_index = view.findViewById(R.id.pm25_index);
        air_index = view.findViewById(R.id.air_index);
        weatherToolbar = view.findViewById(R.id.weather_toolbar);
        progressBar = view.findViewById(R.id.weather_progress);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity)requireActivity()).mLocationClient.startLocation();
        weatherToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.RefreshWeather:
                        progressBar.setVisibility(View.VISIBLE);
                        ((MainActivity)requireActivity()).mLocationClient.startLocation();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    public void refreshUI(){//刷新UI
        SunnyWeatherDB db = null;
        if(model != null){
            db = model.getWeatherData();
        }
        if(db != null)//如果数据有效
        {
            address.setText(db.getResult().getAlert().getAdcodes().get(2).getName());
            temperature.setText(String.valueOf(db.getResult().getRealtime().getApparentTemperature())
                    );
            String weather = db.getResult().getRealtime().getSkycon();
            weather_data.setText(parseSkycon(weather));
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "天气信息刷新成功", Toast.LENGTH_SHORT).show();
        }else{
            Log.d(TAG, "refreshUI: "+"实体类无效");
        }
    }

    private String parseSkycon(String skycon){
        String ans = null;
        switch (skycon){
            case "CLEAR_DAY": ans = "晴朗";break;
            case "CLEAR_NIGHT": ans = "晴朗";break;
            case "PARTLY_CLOUDY_DAY": ans = "多云";break;
            case "PARTLY_CLOUDY_NIGHT": ans = "多云";break;
            case "CLOUDY": ans = "阴";break;
            case "LIGHT_HAZE": ans = "轻度雾霾";break;
            case "MODERATE_HAZE": ans = "中度雾霾";break;
            case "HEAVY_HAZE":ans = "重度雾霾";break;
            case "LIGHT_RAIN":ans = "小雨";break;
            case "MODERATE_RAIN":ans = "中雨";break;
            case "HEAVY_RAIN":ans = "大雨";break;
            case "STORM_RAIN":ans = "暴雨";break;
            case "FOG": ans = "雾";break;
            case "LIGHT_SNOW": ans = "小雪";break;
            case "MODERATE_SNOW": ans = "中雪";break;
            case "HEAVY_SNOW": ans = "大雪";break;
            case "STORM_SNOW": ans = "暴雪";break;
            case "DUST": ans = "浮尘";break;
            case "SAND": ans = "沙尘";break;
            case "WIND": ans = "大风";break;
        }
        return ans;
    }

    //详情请见彩云天气的天气指数文档
    private final static int ULTRA_STD = 1;
    private final static int DRESS_STD = 2;
    private final static int COLDRISK_STD = 3;
    private final static int PM25_STD = 4;
    private final static int COMFORT_STD = 5;
    private final static int AIR_STD = 6;

    private String parseLifeIndex(int std,int data){
        switch (std){
            case ULTRA_STD:
                break;
            case DRESS_STD:
                break;
            case COLDRISK_STD:
                break;
            case PM25_STD:
                break;
            case COMFORT_STD:

        }

        return null;
    }
}