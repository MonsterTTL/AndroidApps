package com.example.busquery2.OkHttp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.busquery2.BusActivity;
import com.example.busquery2.GSON.BusRoutesGson.Bus;
import com.example.busquery2.GSON.BusRoutesGson.ReturlListBusBean;
import com.example.busquery2.GSON.CityListGson.City;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//公交路线
public class BusRoutes {

    private static final String TAG = "BUSROUTES";

    private String busnumber;

    private String citynumber;

    private Handler handler;

    private ReturlListBusBean busid;
    Context context;

    public BusRoutes(String citynumber,String busnumber, Handler handler) {
        this.busnumber = busnumber;
        this.citynumber = citynumber;
        this.handler = handler;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void sendRequestWithOkHttp() {
        Log.d(TAG, "sendRequestWithOkHttp: 进入到方法sendRequestWithOkHttp");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: 进入");
                    OkHttpClient client = new OkHttpClient();
                    Log.d(TAG, "run: " + busnumber);
                    Request request = new Request.Builder()
                            .url("http://api.wxbus163.cn/z_busapi/BusApi.php?optype=luxian&uname=18991377839" +
                                    "&cityid=" + "115" +
                                    "&keywords=" + busnumber +
                                    "&keySecret=eae5d76f1e671e1b68d226af16e04063")
                            .build();

                    Log.d(TAG, "run: "+citynumber + "  busnum:"+busnumber);
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d(TAG, "onFailure: 请求失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d(TAG, "onResponse: "+"success" );
                            String responsedata = response.body().string();
                            parseJSONWithGSON(responsedata);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        Log.d(TAG, "parseJSONWithGSON: "+jsonData);
        Bus bus = gson.fromJson(jsonData, Bus.class);
        Message message = new Message();
        message.what = 3;
        Bundle bundle = new Bundle();
        Log.d(TAG, "parseJSONWithGSON: "+bus.getReturnCode());
        if(!bus.getReturnCode().equals("ok")){
            bundle.putString("ErrorCode","gg");
        }else{
            bundle.putString("ErrorCode","ok");
            bundle.putString("busLinestridkey",bus.getReturlList().get(0).getBusLinestrid());
            bundle.putString("busLinenumkey",bus.getReturlList().get(0).getBusLinenum());
            bundle.putString("busStanamekey",bus.getReturlList().get(0).getBusStaname());
        }
        message.setData(bundle);
        handler.sendMessage(message);

    }
}
