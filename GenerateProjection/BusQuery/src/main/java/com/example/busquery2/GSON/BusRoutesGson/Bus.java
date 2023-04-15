package com.example.busquery2.GSON.BusRoutesGson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Bus implements Serializable {

    /**
     * return_code : ok
     * error_code : 0
     * returl_list : [{"bus_endstan":"草堂七路公交调度站","bus_linestrid":"OTAwMDAwMTA3Mzc3","bus_linenum":"610100","bus_staname":"330","bus_stastan":"产业园公交调度站"},{"bus_endstan":"产业园公交调度站","bus_linestrid":"OTAwMDAwMTA3Mzc4","bus_linenum":"610100","bus_staname":"330","bus_stastan":"草堂七路公交调度站"}]
     */

    @SerializedName("return_code")
    private String returnCode;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("returl_list")
    private List<ReturlListBusBean> returlList;

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

    public List<ReturlListBusBean> getReturlList() {
        return returlList;
    }

    public void setReturlList(List<ReturlListBusBean> returlList) {
        this.returlList = returlList;
    }
}
