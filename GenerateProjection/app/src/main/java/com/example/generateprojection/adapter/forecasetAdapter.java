package com.example.generateprojection.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generateprojection.R;
import com.example.generateprojection.helper.SunnyWeatherDB;

public class forecasetAdapter extends RecyclerView.Adapter<forecasetAdapter.ViewHolder> {

    private SunnyWeatherDB db ;

    public forecasetAdapter(SunnyWeatherDB db){
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecaset_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String skycon = parseSkycon(db.getResult().getDaily().getSkycon().get(position).getValue());
        holder.skycon.setText(skycon);
        String date = db.getResult().getDaily().getSkycon().get(position).getDate().substring(0,10);
        holder.date.setText(date);
        int sourceId = R.drawable.qing;
        if(skycon.equals("晴朗")){
            sourceId = R.drawable.qing;
        }else if(skycon.equals("浮尘") || skycon.equals("沙尘")){
            sourceId = R.drawable.dust_weather;
        }else if(skycon.equals("多云")){
            sourceId = R.drawable.duoyun;
        }else if(skycon.equals("小雨")){
            sourceId = R.drawable.xiaoyu;
        }else if(skycon.equals("中雨")){
            sourceId = R.drawable.zhongyu;
        }else if(skycon.equals("大雨")){
            sourceId = R.drawable.dayu;
        }else if(skycon.equals("小雪")){
            sourceId = R.drawable.xiaoxue;
        }else if(skycon.equals("中雪")){
            sourceId = R.drawable.zhongxue;
        }else if(skycon.equals("大雪") || skycon.equals("暴雪")){
            sourceId = R.drawable.daxue;
        }else if(skycon.equals("阴")){
            sourceId = R.drawable.duoyun1;
        }else if(skycon.endsWith("雾霾")){
            sourceId = R.drawable.wu;
        }else if(skycon.equals("雾")){
            sourceId = R.drawable.wu;
        }
        holder.icon.setImageResource(sourceId);
    }

    @Override
    public int getItemCount() {
        return db.getResult().getDaily().getAstro().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        TextView skycon;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.forecaset_date);
            skycon = itemView.findViewById(R.id.forecaset_skycon);
            icon = itemView.findViewById(R.id.forecast_icon);
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
}
