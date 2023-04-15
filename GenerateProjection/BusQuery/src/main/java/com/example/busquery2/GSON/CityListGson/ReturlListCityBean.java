package com.example.busquery2.GSON.CityListGson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReturlListCityBean implements Serializable {
    /**
     * cityid : 341
     * city : 阿城市
     */

    @SerializedName("cityid")
    private String cityid;
    @SerializedName("city")
    private String city;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
