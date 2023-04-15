package com.example.busquery2.GSON.DepartureTimetableGson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Daparture implements Serializable {

    /**
     * return_code : ok
     * error_code : 0
     * returl_list : 08:33,08:46,09:05,09:30,10:00,10:29,10:59,11:32,12:02,12:29,12:59,13:29,14:00,14:36,14:59,16:01,16:21,16:40,16:57,17:08,17:19,17:30,17:44,17:49,18:00,18:12,18:22,18:39,18:48,19:00,19:15,19:30,19:51,20:11,20:30
     */

    @SerializedName("return_code")
    private String returnCode;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("returl_list")
    private String returlList;

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

    public String getReturlList() {
        return returlList;
    }

    public void setReturlList(String returlList) {
        this.returlList = returlList;
    }
}
