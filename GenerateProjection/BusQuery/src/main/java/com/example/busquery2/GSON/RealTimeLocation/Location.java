package com.example.busquery2.GSON.RealTimeLocation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    /**
     * return_code : ok
     * error_code : 0
     * returl_list : {"lineinfo":{"bus_staname":"330路","fir_time":"06:30","end_time":"20:30","sta_sta":"产业园公交调度站","end_sta":"草堂七路公交调度站","bus_money":"2","other_lineid":"OTAwMDAwMTA3Mzc4"},"stations":[{"bus_staname":"产业园公交调度站","sid":"900000107377029"},{"bus_staname":"西太路信息大道口","sid":"900000107377031"},{"bus_staname":"郭杜西地铁站","sid":"900000107377035"},{"bus_staname":"小仁村","sid":"900000107377005"},{"bus_staname":"西太路","sid":"900000107377026"},{"bus_staname":"羊元村西","sid":"900000107377006"},{"bus_staname":"西太路未来之瞳","sid":"900000107377007"},{"bus_staname":"西安电子科技大学长安校区西门","sid":"900000107377008"},{"bus_staname":"甘河村","sid":"900000107377039"},{"bus_staname":"西太路终南大道口","sid":"900000107377038"},{"bus_staname":"西太路云桥路口","sid":"900000107377032"},{"bus_staname":"兆丰村","sid":"900000107377011"},{"bus_staname":"晓阳村","sid":"900000107377012"},{"bus_staname":"五星中学","sid":"900000107377013"},{"bus_staname":"五楼村","sid":"900000107377014"},{"bus_staname":"庆镇","sid":"900000107377015"},{"bus_staname":"郭村","sid":"900000107377022"},{"bus_staname":"大良村","sid":"900000107377016"},{"bus_staname":"宋村东堡","sid":"900000107377036"},{"bus_staname":"宋村南转盘","sid":"900000107377037"},{"bus_staname":"西太路秦岭东六路口","sid":"900000107377041"},{"bus_staname":"马丰滩","sid":"900000107377042"},{"bus_staname":"草堂八路秦岭大道口","sid":"900000107377043"},{"bus_staname":"草堂八路秦岭四路口","sid":"900000107377044"},{"bus_staname":"草堂七路秦岭三路口","sid":"900000107377045"},{"bus_staname":"草堂七路公交调度站","sid":"900000107377040"}],"buses":[{"lating":"34.05543906449249","longing":"108.74345560408204","distance":714,"dis_stat":18},{"lating":"34.1032392336555","longing":"108.81575329994803","distance":0,"dis_stat":11}]}
     */

    @SerializedName("return_code")
    private String returnCode;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("returl_list")
    private ReturlListLocationBean returlList;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ReturlListLocationBean getReturlList() {
        return returlList;
    }

    public void setReturlList(ReturlListLocationBean returlList) {
        this.returlList = returlList;
    }
}
