package com.example.myweather.gson;

import com.google.gson.annotations.SerializedName;

public class BaiDuLocat {

    public static final String BD_URI_HEAD =
            "https://api.map.baidu.com/location/ip?ak=LmYvC3j2GGiVN2FjGcqFpoCFekgfPtRc&ip=";

    public static final String BD_URI_END =
            "&coor=bd09ll";

    public String address;
    public content content;

    @SerializedName("status")
    public int status;

    public class content{
        public String address;

        @SerializedName("address_detail")
        public address_detail addressDetail;

        @SerializedName("point")
        point point;
    }


    public class address_detail{
        public String city;
        public int city_code;
        public String province;

        @SerializedName("adcode")
        public String adcode;
    }

    public class point{
        public String x;
        public String y;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("address = " + this.address);
        return builder.toString();
    }
}
