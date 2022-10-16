package com.example.myweather.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myweather.MyApplication;
import com.example.myweather.R;
import com.example.myweather.gson.BaiDuWeather;

import java.util.List;

public class forecastAdapter extends RecyclerView.Adapter<forecastAdapter.ViewHolder> {
    private List<BaiDuWeather.forecasts> mList;



    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView weatherImg;
        TextView single_weather;
        TextView single_temperature;
        TextView time;

        public ViewHolder(View view){
            super(view);
            weatherImg = view.findViewById(R.id.single_forecastIMG);
            single_temperature = view.findViewById(R.id.single_temperature);
            single_weather = view.findViewById(R.id.single_weather);
            time = view.findViewById(R.id.data_time);
        }

    }

    public forecastAdapter(List<BaiDuWeather.forecasts> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_weatherlayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        BaiDuWeather.forecasts myWeather = mList.get(position);
        String weather = myWeather.text_day;
        String temperature_low = String.valueOf(myWeather.low)+" ~ "+String.valueOf(myWeather.high);
        String dailyTime = myWeather.date + "|||" + myWeather.week;
        int picId = R.drawable.lieri;
        switch (weather){
            case "阴":picId = R.drawable.yintian;
                    break;
            case "多云":picId = R.drawable.nongyun;
                    break;
            case "晴": picId = R.drawable.lieri;
                break;
            default:
                break;
        }
        holder.time.setText(dailyTime);
        holder.single_weather.setText(weather);
        holder.single_temperature.setText(temperature_low + "℃");
        Glide.with(MyApplication.getContext()).load(picId).into(holder.weatherImg);

    }

    public int getItemCount(){
        return mList.size();
    }
}
