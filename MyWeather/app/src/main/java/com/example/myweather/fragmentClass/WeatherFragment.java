package com.example.myweather.fragmentClass;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myweather.R;
import com.example.myweather.Utils.IntnetTool;
import com.example.myweather.gson.BaiDuLocat;
import com.example.myweather.gson.BaiDuWeather;
import com.example.myweather.adapter.forecastAdapter;
import com.example.myweather.dataLib.*;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class WeatherFragment extends Fragment {

    private DrawerLayout drawerLayout;
    //private BaiDuLocat myLocation;
    public BaiDuWeather myWeather;
    private Toolbar toolbar;
    private TextView temperatureText;
    private TextView weatherText;
    private TextView addressText;
    private TextView addressName;
    private RecyclerView forecasts;
    private Button homeButton;


    public void setMyWeather(BaiDuWeather weather){
        this.myWeather = weather;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d("test","onCreate");

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.more);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        if(myWeather != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refreshWeather();
                }
            });
        }
        Log.d("test","onViewCreated");
        toolbar.inflateMenu(R.menu.toolbarmenu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_share:
                        Toast.makeText(getContext(),"share",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_feedback:
                        Toast.makeText(getContext(),"feedback",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(getContext(),"setting",Toast.LENGTH_SHORT).show();
                        break;
                    //case android.R.id.home:
                    //
                    //    break;
                    default:
                        break;
                }
                return true;
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        temperatureText = view.findViewById(R.id.temperature_text);
        addressName = view.findViewById(R.id.address_name);
        weatherText = view.findViewById(R.id.weather_text);
        addressText = view.findViewById(R.id.address_text);
        forecasts = view.findViewById(R.id.weather_forecast);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        homeButton = view.findViewById(R.id.HomeButton);
        Log.d("test","onCreateView");
        return view;
    }


    //@Override
    //public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    //    Log.d("test",myWeather.result.location.name+"onCreateOptionsMenu");
    //    inflater.inflate(R.menu.toolbarmenu,menu);
    //}



    public void refreshWeather(){
        temperatureText.setText(String.valueOf(myWeather.result.now.temp));
        weatherText.setText(String.valueOf(myWeather.result.now.text));
        addressText.setText(myWeather.result.location.province + "||" +
                    myWeather.result.location.city);
        addressName.setText(myWeather.result.location.name);
        ArrayList<BaiDuWeather.forecasts> arrayList = new ArrayList<>();
        for(BaiDuWeather.forecasts item:myWeather.result.forecasts){
            arrayList.add(item);
        }
        forecastAdapter adapter = new forecastAdapter(arrayList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        forecasts.setLayoutManager(manager);
        forecasts.setAdapter(adapter);
    }

    public void onPause(){
        super.onPause();
        Log.d("test","Pause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test","Destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("test","Detach");
    }
}