package com.example.myweather.Utils;

import android.util.Log;

import com.example.myweather.gson.BaiDuLocat;
import com.example.myweather.gson.BaiDuWeather;
import com.example.myweather.gson.IPLocat;
import com.google.gson.Gson;
import com.example.myweather.gson.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class IntnetTool {

    public static String getLocIP(){

        try{
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static String getLocalAddress(){
        try{
            for(Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
                nis.hasMoreElements();){
                    NetworkInterface netI = nis.nextElement();
                    for(Enumeration<InetAddress> adds = netI.getInetAddresses();
                        adds.hasMoreElements();){
                        InetAddress inetAddress = adds.nextElement();
                        if(inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress())
                        {
                            return inetAddress.getHostAddress();
                        }
                    }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void SendHttpWithOkHttp(String address, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static IPLocat PullWithGson(String response){
        Gson gson = new Gson();
        return  gson.fromJson(response,IPLocat.class);
    }

    public static String getTrueIpAddress(){
        InputStream is = null;
        String line = "";
        try {
            URL url = new URL("https://pv.sohu.com/cityjson?ie=utf-8");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            int requestCode = connection.getResponseCode();
            if(requestCode == HttpsURLConnection.HTTP_OK){
                is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                while((line = br.readLine()) != null){
                    Log.d("Main","line"+ line);
                    builder.append(line);
                }
                is.close();
                connection.disconnect();
                int start = builder.indexOf("{");
                int end = builder.indexOf("}");
                String data = builder.substring(start,end+1).toString();
                if(data != null){
                    try {
                        JSONObject object = new JSONObject(data);
                        return object.getString("cip");

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }finally {
            try{
                if(is != null)
                     is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        String line = "";
        try {
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // 从反馈的结果中提取出IP地址
                int start = strber.indexOf("{");
                int end = strber.indexOf("}");
                String json = strber.substring(start, end + 1);
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        line = jsonObject.optString("cip");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String SendHttpWithHttpConnect(String address){
        URL url ;
        String data;
        String line = "";
        InputStream is ;
        try{
            url = new URL(address);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            int requsetCode = connection.getResponseCode();
            if(requsetCode == HttpsURLConnection.HTTP_OK){
                is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                while((line = reader.readLine()) != null){
                    builder.append(line);
                }
                is.close();
                connection.disconnect();
                return builder.toString();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return line;
    }

    public static BaiDuWeather requestWeather(String adcode){
        String data = IntnetTool.SendHttpWithHttpConnect(BaiDuWeather.BDWhea_Head
        + adcode + BaiDuWeather.BDWhea_End);
        BaiDuWeather weather = IntnetTool.handleWeather(data);
        return weather;
    }

    public static BaiDuLocat handleBDdata(String data){
        Gson gson = new Gson();
        return gson.fromJson(data,BaiDuLocat.class);
    }


    public static BaiDuWeather handleWeather(String data){
        Gson gson = new Gson();
        return gson.fromJson(data,BaiDuWeather.class);
    }

    public static queryLocation handleQuery(String data){
        Gson gson = new Gson();
        return gson.fromJson(data,queryLocation.class);
    }
}
