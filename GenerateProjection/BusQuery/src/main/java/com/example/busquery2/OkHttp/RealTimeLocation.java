package com.example.busquery2.OkHttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.busquery2.GSON.BusRoutesGson.Bus;
import com.example.busquery2.GSON.BusRoutesGson.ReturlListBusBean;
import com.example.busquery2.GSON.RealTimeLocation.Location;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PipedReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//实时位置
public class RealTimeLocation {

    private static final String TAG = "REALTIMELOCATION";

    private ReturlListBusBean busBean;

    private String cityid;

    private Handler handler;

    public RealTimeLocation(ReturlListBusBean busBean, String cityid, Handler handler) {
        this.busBean = busBean;
        this.cityid = cityid;
        this.handler = handler;
    }

    public void sendRequestWithOkHttp() {
        Log.d(TAG, "sendRequestWithOkHttp: 进入到方法sendRequestWithOkHttp");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: 进入");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://api.wxbus163.cn/z_busapi/BusApi.php?optype=rtbus&uname=18991377839" +
                                    "&cityid=" + cityid +
                                    "&bus_linestrid=" + busBean.getBusLinestrid() +
                                    "&bus_linenum=" + busBean.getBusLinenum() +
                                    "&bus_staname=" + busBean.getBusStaname() +
                                    "&keySecret=6d4c68144deb4c90b48d898a2741f34b")
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d(TAG, "onFailure: 请求失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d(TAG, "onResponse: " );
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
        Location location = gson.fromJson(jsonData, Location.class);
        //Log.d(TAG, "parseJSONWithGSON: " + jsonData);

        Message message = new Message();
        message.what = 4;
        Bundle bundle = new Bundle();
        bundle.putString("buslocationkey",jsonData);
        message.setData(bundle);
        handler.sendMessage(message);

    }

}
