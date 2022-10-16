package com.example.myweather.gson;

import com.google.gson.annotations.SerializedName;

public class queryLocation {

    public static final String QUE_HEAD = "https://api.map.baidu.com/place/v2/suggestion?query=";
    public static final String QUE_END = "&region=null&city_limit=false&output=json&ak=LmYvC3j2GGiVN2FjGcqFpoCFekgfPtRc";

    @SerializedName("status")
    public int status;

    @SerializedName("message")
    public String message;

    @SerializedName("result")
    public Result[] result;

    public class Result{
        public String name;
        public Location location;
        public String uid;
        public String province;
        public String city;
        public String district;
        public String cityid;
        public String tag;
        public String address;
        public String adcode;

    }

    public class Location{
        public double lat;
        public double lng;
    }
}
