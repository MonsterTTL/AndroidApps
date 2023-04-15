package com.example.busquery2.GSON.RealTimeLocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StationsLocationBean implements Serializable {
    /**
     * bus_staname : 产业园公交调度站
     * sid : 900000107377029
     */

    @SerializedName("bus_staname")
    private String busStaname;
    @SerializedName("sid")
    private String sid;

    public String getBusStaname() {
        return busStaname;
    }

    public void setBusStaname(String busStaname) {
        this.busStaname = busStaname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
