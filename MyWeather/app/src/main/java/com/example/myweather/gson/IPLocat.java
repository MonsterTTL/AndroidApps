package com.example.myweather.gson;

import java.io.Serializable;

public class IPLocat implements Serializable {

    public String status;

    public String info;

    public String infocode;

    public String province;

    public String[] city;

    public String[] adcode;

    public String[] rectangle;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String toString(){

        StringBuilder builder = new StringBuilder();
        builder.append("status: "+status)
                .append("info: "+info)
                .append("infocode: "+infocode)
                .append(" province: "+province);

        return  builder.toString();

    }



}
