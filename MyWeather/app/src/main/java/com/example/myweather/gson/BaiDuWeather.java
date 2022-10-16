package com.example.myweather.gson;

public class BaiDuWeather {


    public static final String BDWhea_Head
            = "https://api.map.baidu.com/weather/v1/?district_id=" ;

    public static final String BDWhea_End
            = "&data_type=all&ak=LmYvC3j2GGiVN2FjGcqFpoCFekgfPtRc";

    public int status;
    public result result;
    public String message;



    public class result{
        public location location;
        public now now;
        public forecasts[] forecasts;
    }

    public class location{
        public String country;
        public String province;
        public String city;
        public String name;
        public String id;
    }

    public class now{
        public String text;
        public int temp;
        public int feels_like;
        public int rh;
        public String wind_class;
        public String wind_dir;
        public String uptime;
    }

    public class forecasts{
        public String text_day;
        public String text_night;
        public int high;
        public int low;
        public String wc_day;
        public String wd_day;
        public String wc_night;
        public String wd_night;
        public String date;
        public String week;
    }
}
