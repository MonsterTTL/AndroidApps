package com.example.busquery2.GSON.RealTimeLocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LineinfoLocationBean implements Serializable {
    /**
     * bus_staname : 330路
     * fir_time : 06:30
     * end_time : 20:30
     * sta_sta : 产业园公交调度站
     * end_sta : 草堂七路公交调度站
     * bus_money : 2
     * other_lineid : OTAwMDAwMTA3Mzc4
     */

    @SerializedName("bus_staname")
    private String busStaname;
    @SerializedName("fir_time")
    private String firTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("sta_sta")
    private String staSta;
    @SerializedName("end_sta")
    private String endSta;
    @SerializedName("bus_money")
    private String busMoney;
    @SerializedName("other_lineid")
    private String otherLineid;

    public String getBusStaname() {
        return busStaname;
    }

    public void setBusStaname(String busStaname) {
        this.busStaname = busStaname;
    }

    public String getFirTime() {
        return firTime;
    }

    public void setFirTime(String firTime) {
        this.firTime = firTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStaSta() {
        return staSta;
    }

    public void setStaSta(String staSta) {
        this.staSta = staSta;
    }

    public String getEndSta() {
        return endSta;
    }

    public void setEndSta(String endSta) {
        this.endSta = endSta;
    }

    public String getBusMoney() {
        return busMoney;
    }

    public void setBusMoney(String busMoney) {
        this.busMoney = busMoney;
    }

    public String getOtherLineid() {
        return otherLineid;
    }

    public void setOtherLineid(String otherLineid) {
        this.otherLineid = otherLineid;
    }
}
