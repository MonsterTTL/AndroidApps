package com.example.busquery2.GSON.BusRoutesGson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReturlListBusBean implements Serializable {
    /**
     * bus_endstan : 草堂七路公交调度站
     * bus_linestrid : OTAwMDAwMTA3Mzc3
     * bus_linenum : 610100
     * bus_staname : 330
     * bus_stastan : 产业园公交调度站
     */

    @SerializedName("bus_endstan")
    private String busEndstan;
    @SerializedName("bus_linestrid")
    private String busLinestrid;
    @SerializedName("bus_linenum")
    private String busLinenum;
    @SerializedName("bus_staname")
    private String busStaname;
    @SerializedName("bus_stastan")
    private String busStastan;

    public String getBusEndstan() {
        return busEndstan;
    }

    public void setBusEndstan(String busEndstan) {
        this.busEndstan = busEndstan;
    }

    public String getBusLinestrid() {
        return busLinestrid;
    }

    public void setBusLinestrid(String busLinestrid) {
        this.busLinestrid = busLinestrid;
    }

    public String getBusLinenum() {
        return busLinenum;
    }

    public void setBusLinenum(String busLinenum) {
        this.busLinenum = busLinenum;
    }

    public String getBusStaname() {
        return busStaname;
    }

    public void setBusStaname(String busStaname) {
        this.busStaname = busStaname;
    }

    public String getBusStastan() {
        return busStastan;
    }

    public void setBusStastan(String busStastan) {
        this.busStastan = busStastan;
    }
}
