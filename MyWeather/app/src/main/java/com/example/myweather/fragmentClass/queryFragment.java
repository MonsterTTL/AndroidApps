package com.example.myweather.fragmentClass;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myweather.MainActivity;
import com.example.myweather.MyApplication;
import com.example.myweather.R;
import com.example.myweather.Utils.IntnetTool;
import com.example.myweather.gson.*;
import com.example.myweather.adapter.*;
import com.example.myweather.fragmentClass.*;

import java.util.ArrayList;
import java.util.Timer;

public class queryFragment extends Fragment {


    Looper mainLooper = Looper.getMainLooper();
    TextView queryInfo;
    private boolean isQuery = false;
    private boolean shouldChange = false;
    private static final int REFRESH = 1;
    private static final int RELOAD = 2;
    private String lastInfo = "";
    private WeatherFragment newFragment;



    private Handler handler = new Handler(mainLooper){

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case REFRESH:
                    if(shouldChange){
                        LinearLayoutManager lm = new LinearLayoutManager(getContext());
                        lm.setOrientation(LinearLayoutManager.VERTICAL);
                        myRecycle.setLayoutManager(lm);
                        myRecycle.setAdapter(myAdapter);
                    }
                    break;
                case RELOAD:
                    if(newFragment.myWeather != null)
                        ((MainActivity)getActivity()).replaceFragment(newFragment,data);
                    break;
                default:
                    break;
            }
        }
    };
    private String data;
    private queryLocation myQuery;
    private RecyclerView myRecycle;
    private queryAdapter myAdapter;
    private FragmentManager myManager;
    public queryFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        queryInfo = view.findViewById(R.id.searchPlaceEdit);
        myRecycle = view.findViewById(R.id.queryRecycleView);
        //
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecycle.setLayoutManager(lm);
        myRecycle.setAdapter(myAdapter);
        //
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myAdapter = new queryAdapter(new ArrayList<>());
        myAdapter.setMyInterface(new queryAdapter.myInterface() {
            @Override
            public void onItemClick(View view, int position) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        queryLocation.Result result = myAdapter.mList.get(position);
                        Log.d("listener", "你点了" + result.district);
                        String adcode = result.adcode;
                        queryFragment.this.data = IntnetTool.SendHttpWithHttpConnect(BaiDuWeather.BDWhea_Head
                                + adcode + BaiDuWeather.BDWhea_End);
                        Log.d("listener", data);
                        BaiDuWeather weather = IntnetTool.handleWeather(queryFragment.this.data);
                        Log.d("listener",weather.result.location.name);
                        newFragment = new WeatherFragment();
                        newFragment.setMyWeather(weather);
                        if (weather != null) {
                            Log.d("test", "hello!!!");
                        }
                        Message message1 = new Message();
                        message1.what = RELOAD;
                        handler.sendMessage(message1);

                    }
                }).start();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    getQuery();
                    try {
                        Thread.sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void getQuery(){
        String info = queryInfo.getText()+"";
        if(lastInfo.equals(info)){
            shouldChange = false;
        }else{
            shouldChange = true;
            lastInfo = info;
        }
        if(shouldChange){
            String data = IntnetTool.SendHttpWithHttpConnect(
                    queryLocation.QUE_HEAD + info + queryLocation.QUE_END
            );
            myQuery = IntnetTool.handleQuery(data);
            if(myQuery.status == 0) {
                queryLocation.Result[] results = myQuery.result;
                ArrayList<queryLocation.Result> mList = new ArrayList<>();
                for (queryLocation.Result item : results) {
                    mList.add(item);
                }
                myAdapter.mList = mList;

                Message message = new Message();
                message.what = REFRESH;
                handler.sendMessage(message);
            }
        }
    }




}