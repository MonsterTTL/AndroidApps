package com.example.busquery2.GSON.RealTimeLocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BusesLocationBean implements Serializable {
    /**
     * lating : 34.05543906449249
     * longing : 108.74345560408204
     * distance : 714
     * dis_stat : 18
     */

    @SerializedName("lating")
    private String lating;
    @SerializedName("longing")
    private String longing;
    @SerializedName("distance")
    private Integer distance;
    @SerializedName("dis_stat")
    private Integer disStat;

    public String getLating() {
        return lating;
    }

    public void setLating(String lating) {
        this.lating = lating;
    }

    public String getLonging() {
        return longing;
    }

    public void setLonging(String longing) {
        this.longing = longing;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDisStat() {
        return disStat;
    }

    public void setDisStat(Integer disStat) {
        this.disStat = disStat;
    }
}
