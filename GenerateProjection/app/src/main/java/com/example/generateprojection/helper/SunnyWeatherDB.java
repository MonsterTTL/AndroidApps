package com.example.generateprojection.helper;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SunnyWeatherDB implements Serializable {

    @SerializedName("status")
    private String status;
    @SerializedName("api_version")
    private String apiVersion;
    @SerializedName("api_status")
    private String apiStatus;
    @SerializedName("lang")
    private String lang;
    @SerializedName("unit")
    private String unit;
    @SerializedName("tzshift")
    private Integer tzshift;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("server_time")
    private Integer serverTime;
    @SerializedName("result")
    private ResultDTO result;
    @SerializedName("location")
    private List<Double> location;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTzshift() {
        return tzshift;
    }

    public void setTzshift(Integer tzshift) {
        this.tzshift = tzshift;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getServerTime() {
        return serverTime;
    }

    public void setServerTime(Integer serverTime) {
        this.serverTime = serverTime;
            }

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public static class ResultDTO implements Serializable {
        @SerializedName("alert")
        private AlertDTO alert;
        @SerializedName("realtime")
        private RealtimeDTO realtime;
        @SerializedName("minutely")
        private MinutelyDTO minutely;
        @SerializedName("hourly")
        private HourlyDTO hourly;
        @SerializedName("daily")
        private DailyDTO daily;
        @SerializedName("primary")
        private Integer primary;
        @SerializedName("forecast_keypoint")
        private String forecastKeypoint;

        public AlertDTO getAlert() {
            return alert;
        }

        public void setAlert(AlertDTO alert) {
            this.alert = alert;
        }

        public RealtimeDTO getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeDTO realtime) {
            this.realtime = realtime;
        }

        public MinutelyDTO getMinutely() {
            return minutely;
        }

        public void setMinutely(MinutelyDTO minutely) {
            this.minutely = minutely;
        }

        public HourlyDTO getHourly() {
            return hourly;
        }

        public void setHourly(HourlyDTO hourly) {
            this.hourly = hourly;
        }

        public DailyDTO getDaily() {
            return daily;
        }

        public void setDaily(DailyDTO daily) {
            this.daily = daily;
        }

        public Integer getPrimary() {
            return primary;
        }

        public void setPrimary(Integer primary) {
            this.primary = primary;
        }

        public String getForecastKeypoint() {
            return forecastKeypoint;
        }

        public void setForecastKeypoint(String forecastKeypoint) {
            this.forecastKeypoint = forecastKeypoint;
        }

        public static class AlertDTO implements Serializable {
            @SerializedName("status")
            private String status;
            @SerializedName("content")
            private List<ContentDTO> content;
            @SerializedName("adcodes")
            private List<AdcodesDTO> adcodes;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<ContentDTO> getContent() {
                return content;
            }

            public void setContent(List<ContentDTO> content) {
                this.content = content;
            }

            public List<AdcodesDTO> getAdcodes() {
                return adcodes;
            }

            public void setAdcodes(List<AdcodesDTO> adcodes) {
                this.adcodes = adcodes;
            }

            public static class ContentDTO implements Serializable {
                @SerializedName("province")
                private String province;
                @SerializedName("status")
                private String status;
                @SerializedName("code")
                private String code;
                @SerializedName("description")
                private String description;
                @SerializedName("regionId")
                private String regionId;
                @SerializedName("county")
                private String county;
                @SerializedName("pubtimestamp")
                private Integer pubtimestamp;
                @SerializedName("city")
                private String city;
                @SerializedName("alertId")
                private String alertId;
                @SerializedName("title")
                private String title;
                @SerializedName("adcode")
                private String adcode;
                @SerializedName("source")
                private String source;
                @SerializedName("location")
                private String location;
                @SerializedName("request_status")
                private String requestStatus;
                @SerializedName("latlon")
                private List<Double> latlon;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getRegionId() {
                    return regionId;
                }

                public void setRegionId(String regionId) {
                    this.regionId = regionId;
                }

                public String getCounty() {
                    return county;
                }

                public void setCounty(String county) {
                    this.county = county;
                }

                public Integer getPubtimestamp() {
                    return pubtimestamp;
                }

                public void setPubtimestamp(Integer pubtimestamp) {
                    this.pubtimestamp = pubtimestamp;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getAlertId() {
                    return alertId;
                }

                public void setAlertId(String alertId) {
                    this.alertId = alertId;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getAdcode() {
                    return adcode;
                }

                public void setAdcode(String adcode) {
                    this.adcode = adcode;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getLocation() {
                    return location;
                }

                public void setLocation(String location) {
                    this.location = location;
                }

                public String getRequestStatus() {
                    return requestStatus;
                }

                public void setRequestStatus(String requestStatus) {
                    this.requestStatus = requestStatus;
                }

                public List<Double> getLatlon() {
                    return latlon;
                }

                public void setLatlon(List<Double> latlon) {
                    this.latlon = latlon;
                }
            }

            public static class AdcodesDTO implements Serializable {
                @SerializedName("adcode")
                private Integer adcode;
                @SerializedName("name")
                private String name;

                public Integer getAdcode() {
                    return adcode;
                }

                public void setAdcode(Integer adcode) {
                    this.adcode = adcode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class RealtimeDTO implements Serializable {
            @SerializedName("status")
            private String status;
            @SerializedName("temperature")
            private Double temperature;
            @SerializedName("humidity")
            private Double humidity;
            @SerializedName("cloudrate")
            private Double cloudrate;
            @SerializedName("skycon")
            private String skycon;
            @SerializedName("visibility")
                        private Double visibility;
            @SerializedName("dswrf")
            private Double dswrf;
            @SerializedName("wind")
            private WindDTO wind;
            @SerializedName("pressure")
            private Double pressure;
            @SerializedName("apparent_temperature")
            private Double apparentTemperature;
            @SerializedName("precipitation")
            private PrecipitationDTO precipitation;
            @SerializedName("air_quality")
            private AirQualityDTO airQuality;
            @SerializedName("life_index")
            private LifeIndexDTO lifeIndex;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Double getTemperature() {
                return temperature;
            }

            public void setTemperature(Double temperature) {
                this.temperature = temperature;
            }

            public Double getHumidity() {
                return humidity;
            }

            public void setHumidity(Double humidity) {
                this.humidity = humidity;
            }

            public Double getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(Double cloudrate) {
                this.cloudrate = cloudrate;
            }

            public String getSkycon() {
                return skycon;
            }

            public void setSkycon(String skycon) {
                this.skycon = skycon;
            }

            public Double getVisibility() {
                return visibility;
            }

            public void setVisibility(Double visibility) {
                this.visibility = visibility;
            }

            public Double getDswrf() {
                return dswrf;
            }

            public void setDswrf(Double dswrf) {
                this.dswrf = dswrf;
            }

            public WindDTO getWind() {
                return wind;
            }

            public void setWind(WindDTO wind) {
                            this.wind = wind;
            }

            public Double getPressure() {
                return pressure;
            }

            public void setPressure(Double pressure) {
                this.pressure = pressure;
            }

            public Double getApparentTemperature() {
                return apparentTemperature;
            }

            public void setApparentTemperature(Double apparentTemperature) {
                this.apparentTemperature = apparentTemperature;
            }

            public PrecipitationDTO getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(PrecipitationDTO precipitation) {
                this.precipitation = precipitation;
            }

            public AirQualityDTO getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(AirQualityDTO airQuality) {
                this.airQuality = airQuality;
            }

            public LifeIndexDTO getLifeIndex() {
                return lifeIndex;
            }

            public void setLifeIndex(LifeIndexDTO lifeIndex) {
                this.lifeIndex = lifeIndex;
            }

            public static class WindDTO implements Serializable {
                @SerializedName("speed")
                private Double speed;
                @SerializedName("direction")
                private Double direction;

                public Double getSpeed() {
                    return speed;
                }

                public void setSpeed(Double speed) {
                    this.speed = speed;
                }

                public Double getDirection() {
                    return direction;
                }

                public void setDirection(Double direction) {
                    this.direction = direction;
                }
            }

            public static class PrecipitationDTO implements Serializable {
                @SerializedName("local")
                private LocalDTO local;
                @SerializedName("nearest")
                private NearestDTO nearest;

                public LocalDTO getLocal() {
                    return local;
                }

                public void setLocal(LocalDTO local) {
                    this.local = local;
                }

                public NearestDTO getNearest() {
                    return nearest;
                }

                public void setNearest(NearestDTO nearest) {
                    this.nearest = nearest;
                }

                public static class LocalDTO implements Serializable {
                    @SerializedName("status")
                    private String status;
                    @SerializedName("datasource")
                    private String datasource;
                    @SerializedName("intensity")
                    private Double intensity;

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getDatasource() {
                        return datasource;
                    }

                    public void setDatasource(String datasource) {
                        this.datasource = datasource;
                    }

                    public Double getIntensity() {
                        return intensity;
                    }

                    public void setIntensity(Double intensity) {
                        this.intensity = intensity;
                    }
                }

                public static class NearestDTO implements Serializable {
                    @SerializedName("status")
                    private String status;
                    @SerializedName("distance")
                    private Double distance;
                    @SerializedName("intensity")
                    private Double intensity;

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public Double getDistance() {
                        return distance;
                    }

                    public void setDistance(Double distance) {
                        this.distance = distance;
                    }

                    public Double getIntensity() {
                        return intensity;
                    }

                    public void setIntensity(Double intensity) {
                        this.intensity = intensity;
                    }
                }
            }

            public static class AirQualityDTO implements Serializable {
                @SerializedName("pm25")
                private Integer pm25;
                @SerializedName("pm10")
                private Integer pm10;
                @SerializedName("o3")
                private Integer o3;
                @SerializedName("so2")
                private Integer so2;
                @SerializedName("no2")
                private Integer no2;
                @SerializedName("co")
                private Double co;
                @SerializedName("aqi")
                private AqiDTO aqi;
                @SerializedName("description")
                private DescriptionDTO description;

                public Integer getPm25() {
                    return pm25;
                }

                public void setPm25(Integer pm25) {
                    this.pm25 = pm25;
                                    }

                public Integer getPm10() {
                    return pm10;
                }

                public void setPm10(Integer pm10) {
                    this.pm10 = pm10;
                }

                public Integer getO3() {
                    return o3;
                }

                public void setO3(Integer o3) {
                                    this.o3 = o3;
                }

                public Integer getSo2() {
                    return so2;
                }

                public void setSo2(Integer so2) {
                    this.so2 = so2;
                }

                public Integer getNo2() {
                    return no2;
                }

                public void setNo2(Integer no2) {
                    this.no2 = no2;
                }

                public Double getCo() {
                    return co;
                }

                public void setCo(Double co) {
                    this.co = co;
                }

                public AqiDTO getAqi() {
                    return aqi;
                                    }

                public void setAqi(AqiDTO aqi) {
                    this.aqi = aqi;
                }

                public DescriptionDTO getDescription() {
                    return description;
                }

                public void setDescription(DescriptionDTO description) {
                    this.description = description;
                }

                public static class AqiDTO implements Serializable {
                    @SerializedName("chn")
                    private Integer chn;
                    @SerializedName("usa")
                    private Integer usa;

                    public Integer getChn() {
                        return chn;
                    }

                    public void setChn(Integer chn) {
                        this.chn = chn;
                    }

                    public Integer getUsa() {
                        return usa;
                    }

                    public void setUsa(Integer usa) {
                        this.usa = usa;
                    }
                }

                public static class DescriptionDTO implements Serializable {
                    @SerializedName("chn")
                    private String chn;
                    @SerializedName("usa")
                    private String usa;

                    public String getChn() {
                                            return chn;
                    }

                    public void setChn(String chn) {
                        this.chn = chn;
                    }

                    public String getUsa() {
                        return usa;
                    }

                    public void setUsa(String usa) {
                        this.usa = usa;
                                            }
                }
            }

            public static class LifeIndexDTO implements Serializable {
                @SerializedName("ultraviolet")
                private UltravioletDTO ultraviolet;
                @SerializedName("comfort")
                private ComfortDTO comfort;

                public UltravioletDTO getUltraviolet() {
                    return ultraviolet;
                }

                public void setUltraviolet(UltravioletDTO ultraviolet) {
                    this.ultraviolet = ultraviolet;
                }

                public ComfortDTO getComfort() {
                    return comfort;
                }

                public void setComfort(ComfortDTO comfort) {
                    this.comfort = comfort;
                }

                public static class UltravioletDTO implements Serializable {
                    @SerializedName("index")
                    private Double index;
                    @SerializedName("desc")
                    private String desc;

                    public Double getIndex() {
                        return index;
                    }

                    public void setIndex(Double index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class ComfortDTO implements Serializable {
                    @SerializedName("index")
                    private Integer index;
                    @SerializedName("desc")
                    private String desc;

                    public Integer getIndex() {
                        return index;
                    }

                    public void setIndex(Integer index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }
            }
        }

        public static class MinutelyDTO implements Serializable {
            @SerializedName("status")
            private String status;
            @SerializedName("datasource")
            private String datasource;
            @SerializedName("description")
            private String description;
            @SerializedName("precipitation_2h")
            private List<Double> precipitation2h;
            @SerializedName("precipitation")
            private List<Double> precipitation;
            @SerializedName("probability")
            private List<Double> probability;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDatasource() {
                return datasource;
            }

            public void setDatasource(String datasource) {
                this.datasource = datasource;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<Double> getPrecipitation2h() {
                return precipitation2h;
            }

            public void setPrecipitation2h(List<Double> precipitation2h) {
                this.precipitation2h = precipitation2h;
            }

            public List<Double> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<Double> precipitation) {
                this.precipitation = precipitation;
            }

            public List<Double> getProbability() {
                return probability;
            }

            public void setProbability(List<Double> probability) {
                this.probability = probability;
            }
        }

        public static class HourlyDTO implements Serializable {
            @SerializedName("status")
            private String status;
            @SerializedName("description")
            private String description;
            @SerializedName("air_quality")
            private AirQualityDTOX airQuality;
            @SerializedName("precipitation")
            private List<PrecipitationDTOX> precipitation;
            @SerializedName("temperature")
            private List<TemperatureDTO> temperature;
            @SerializedName("apparent_temperature")
            private List<ApparentTemperatureDTO> apparentTemperature;
            @SerializedName("wind")
            private List<WindDTOX> wind;
            @SerializedName("humidity")
            private List<HumidityDTO> humidity;
            @SerializedName("cloudrate")
            private List<CloudrateDTO> cloudrate;
            @SerializedName("skycon")
            private List<SkyconDTO> skycon;
            @SerializedName("pressure")
            private List<PressureDTO> pressure;
            @SerializedName("visibility")
            private List<VisibilityDTO> visibility;
            @SerializedName("dswrf")
            private List<DswrfDTO> dswrf;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public AirQualityDTOX getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(AirQualityDTOX airQuality) {
                this.airQuality = airQuality;
            }

            public List<PrecipitationDTOX> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationDTOX> precipitation) {
                this.precipitation = precipitation;
            }

            public List<TemperatureDTO> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureDTO> temperature) {
                this.temperature = temperature;
            }

            public List<ApparentTemperatureDTO> getApparentTemperature() {
                return apparentTemperature;
            }

            public void setApparentTemperature(List<ApparentTemperatureDTO> apparentTemperature) {
                this.apparentTemperature = apparentTemperature;
            }

            public List<WindDTOX> getWind() {
                return wind;
            }

            public void setWind(List<WindDTOX> wind) {
                this.wind = wind;
            }

            public List<HumidityDTO> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityDTO> humidity) {
                this.humidity = humidity;
            }

            public List<CloudrateDTO> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateDTO> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<SkyconDTO> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconDTO> skycon) {
                this.skycon = skycon;
            }

            public List<PressureDTO> getPressure() {
                return pressure;
            }

            public void setPressure(List<PressureDTO> pressure) {
                this.pressure = pressure;
            }

            public List<VisibilityDTO> getVisibility() {
                return visibility;
            }

            public void setVisibility(List<VisibilityDTO> visibility) {
                this.visibility = visibility;
            }

            public List<DswrfDTO> getDswrf() {
                return dswrf;
                            }

            public void setDswrf(List<DswrfDTO> dswrf) {
                this.dswrf = dswrf;
            }

            public static class AirQualityDTOX implements Serializable {
                @SerializedName("aqi")
                private List<AqiDTOX> aqi;
                @SerializedName("pm25")
                private List<Pm25DTO> pm25;

                public List<AqiDTOX> getAqi() {
                    return aqi;
                }

                public void setAqi(List<AqiDTOX> aqi) {
                    this.aqi = aqi;
                }

                public List<Pm25DTO> getPm25() {
                    return pm25;
                }

                public void setPm25(List<Pm25DTO> pm25) {
                    this.pm25 = pm25;
                }

                public static class AqiDTOX implements Serializable {
                    @SerializedName("datetime")
                    private String datetime;
                    @SerializedName("value")
                    private ValueDTO value;

                    public String getDatetime() {
                        return datetime;
                    }

                    public void setDatetime(String datetime) {
                        this.datetime = datetime;
                    }

                    public ValueDTO getValue() {
                        return value;
                    }

                    public void setValue(ValueDTO value) {
                        this.value = value;
                    }

                    public static class ValueDTO implements Serializable {
                        @SerializedName("chn")
                        private Integer chn;
                        @SerializedName("usa")
                        private Integer usa;

                        public Integer getChn() {
                            return chn;
                        }

                        public void setChn(Integer chn) {
                            this.chn = chn;
                        }

                        public Integer getUsa() {
                            return usa;
                        }

                        public void setUsa(Integer usa) {
                            this.usa = usa;
                        }
                    }
                }

                public static class Pm25DTO implements Serializable {
                    @SerializedName("datetime")
                    private String datetime;
                    @SerializedName("value")
                    private Integer value;

                    public String getDatetime() {
                        return datetime;
                    }

                    public void setDatetime(String datetime) {
                        this.datetime = datetime;
                    }

                    public Integer getValue() {
                        return value;
                    }

                    public void setValue(Integer value) {
                        this.value = value;
                    }
                }
            }

            public static class PrecipitationDTOX implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;
                @SerializedName("probability")
                private Integer probability;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }

                public Integer getProbability() {
                    return probability;
                }

                public void setProbability(Integer probability) {
                    this.probability = probability;
                }
            }

            public static class TemperatureDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public static class ApparentTemperatureDTO implements Serializable {
                            @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public static class WindDTOX implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("speed")
                private Double speed;
                @SerializedName("direction")
                private Double direction;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getSpeed() {
                    return speed;
                }

                public void setSpeed(Double speed) {
                    this.speed = speed;
                }

                public Double getDirection() {
                    return direction;
                }

                public void setDirection(Double direction) {
                    this.direction = direction;
                }
            }

            public static class HumidityDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public static class CloudrateDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public static class SkyconDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private String value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class PressureDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public static class VisibilityDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public static class DswrfDTO implements Serializable {
                @SerializedName("datetime")
                private String datetime;
                @SerializedName("value")
                private Double value;

                public String getDatetime() {
                    return datetime;
                }

                public void setDatetime(String datetime) {
                    this.datetime = datetime;
                }

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
                            }
        }

        public static class DailyDTO implements Serializable {
            @SerializedName("status")
            private String status;
            @SerializedName("air_quality")
            private AirQualityDTOXX airQuality;
            @SerializedName("life_index")
            private LifeIndexDTOX lifeIndex;
            @SerializedName("astro")
            private List<AstroDTO> astro;
            @SerializedName("precipitation_08h_20h")
            private List<Precipitation08h20hDTO> precipitation08h20h;
            @SerializedName("precipitation_20h_32h")
                        private List<Precipitation20h32hDTO> precipitation20h32h;
            @SerializedName("precipitation")
            private List<PrecipitationDTOXX> precipitation;
            @SerializedName("temperature")
            private List<TemperatureDTOX> temperature;
            @SerializedName("temperature_08h_20h")
            private List<Temperature08h20hDTO> temperature08h20h;
            @SerializedName("temperature_20h_32h")
            private List<Temperature20h32hDTO> temperature20h32h;
            @SerializedName("wind")
            private List<WindDTOXX> wind;
            @SerializedName("wind_08h_20h")
            private List<Wind08h20hDTO> wind08h20h;
            @SerializedName("wind_20h_32h")
            private List<Wind20h32hDTO> wind20h32h;
            @SerializedName("humidity")
            private List<HumidityDTOX> humidity;
            @SerializedName("cloudrate")
            private List<CloudrateDTOX> cloudrate;
            @SerializedName("pressure")
            private List<PressureDTOX> pressure;
            @SerializedName("visibility")
            private List<VisibilityDTOX> visibility;
            @SerializedName("dswrf")
            private List<DswrfDTOX> dswrf;
            @SerializedName("skycon")
            private List<SkyconDTOX> skycon;
            @SerializedName("skycon_08h_20h")
            private List<Skycon08h20hDTO> skycon08h20h;
            @SerializedName("skycon_20h_32h")
            private List<Skycon20h32hDTO> skycon20h32h;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public AirQualityDTOXX getAirQuality() {
                return airQuality;
            }

            public void setAirQuality(AirQualityDTOXX airQuality) {
                this.airQuality = airQuality;
            }

            public LifeIndexDTOX getLifeIndex() {
                return lifeIndex;
            }

            public void setLifeIndex(LifeIndexDTOX lifeIndex) {
                this.lifeIndex = lifeIndex;
            }

            public List<AstroDTO> getAstro() {
                return astro;
            }

            public void setAstro(List<AstroDTO> astro) {
                this.astro = astro;
            }

            public List<Precipitation08h20hDTO> getPrecipitation08h20h() {
                return precipitation08h20h;
            }

            public void setPrecipitation08h20h(List<Precipitation08h20hDTO> precipitation08h20h) {
                this.precipitation08h20h = precipitation08h20h;
            }

            public List<Precipitation20h32hDTO> getPrecipitation20h32h() {
                return precipitation20h32h;
            }

            public void setPrecipitation20h32h(List<Precipitation20h32hDTO> precipitation20h32h) {
                this.precipitation20h32h = precipitation20h32h;
            }

            public List<PrecipitationDTOXX> getPrecipitation() {
                return precipitation;
            }

            public void setPrecipitation(List<PrecipitationDTOXX> precipitation) {
                this.precipitation = precipitation;
            }

            public List<TemperatureDTOX> getTemperature() {
                return temperature;
            }

            public void setTemperature(List<TemperatureDTOX> temperature) {
                this.temperature = temperature;
            }

            public List<Temperature08h20hDTO> getTemperature08h20h() {
                return temperature08h20h;
                            }

            public void setTemperature08h20h(List<Temperature08h20hDTO> temperature08h20h) {
                this.temperature08h20h = temperature08h20h;
            }

            public List<Temperature20h32hDTO> getTemperature20h32h() {
                return temperature20h32h;
            }

            public void setTemperature20h32h(List<Temperature20h32hDTO> temperature20h32h) {
                this.temperature20h32h = temperature20h32h;
            }

            public List<WindDTOXX> getWind() {
                return wind;
            }

            public void setWind(List<WindDTOXX> wind) {
                this.wind = wind;
            }

            public List<Wind08h20hDTO> getWind08h20h() {
                return wind08h20h;
            }

            public void setWind08h20h(List<Wind08h20hDTO> wind08h20h) {
                this.wind08h20h = wind08h20h;
            }

            public List<Wind20h32hDTO> getWind20h32h() {
                return wind20h32h;
            }

            public void setWind20h32h(List<Wind20h32hDTO> wind20h32h) {
                this.wind20h32h = wind20h32h;
            }

            public List<HumidityDTOX> getHumidity() {
                return humidity;
            }

            public void setHumidity(List<HumidityDTOX> humidity) {
                this.humidity = humidity;
            }

            public List<CloudrateDTOX> getCloudrate() {
                return cloudrate;
            }

            public void setCloudrate(List<CloudrateDTOX> cloudrate) {
                this.cloudrate = cloudrate;
            }

            public List<PressureDTOX> getPressure() {
                return pressure;
            }

            public void setPressure(List<PressureDTOX> pressure) {
                this.pressure = pressure;
            }

            public List<VisibilityDTOX> getVisibility() {
                return visibility;
            }

            public void setVisibility(List<VisibilityDTOX> visibility) {
                this.visibility = visibility;
            }

            public List<DswrfDTOX> getDswrf() {
                return dswrf;
            }

            public void setDswrf(List<DswrfDTOX> dswrf) {
                this.dswrf = dswrf;
            }

            public List<SkyconDTOX> getSkycon() {
                return skycon;
            }

            public void setSkycon(List<SkyconDTOX> skycon) {
                this.skycon = skycon;
            }

            public List<Skycon08h20hDTO> getSkycon08h20h() {
                return skycon08h20h;
            }

            public void setSkycon08h20h(List<Skycon08h20hDTO> skycon08h20h) {
                this.skycon08h20h = skycon08h20h;
            }

            public List<Skycon20h32hDTO> getSkycon20h32h() {
                return skycon20h32h;
            }

            public void setSkycon20h32h(List<Skycon20h32hDTO> skycon20h32h) {
                this.skycon20h32h = skycon20h32h;
            }

            public static class AirQualityDTOXX implements Serializable {
                @SerializedName("aqi")
                private List<AqiDTOXX> aqi;
                @SerializedName("pm25")
                private List<Pm25DTOX> pm25;

                public List<AqiDTOXX> getAqi() {
                    return aqi;
                }

                public void setAqi(List<AqiDTOXX> aqi) {
                    this.aqi = aqi;
                }

                public List<Pm25DTOX> getPm25() {
                    return pm25;
                }

                public void setPm25(List<Pm25DTOX> pm25) {
                    this.pm25 = pm25;
                                    }

                public static class AqiDTOXX implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("max")
                    private MaxDTO max;
                    @SerializedName("avg")
                    private AvgDTO avg;
                    @SerializedName("min")
                    private MinDTO min;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public MaxDTO getMax() {
                        return max;
                    }

                    public void setMax(MaxDTO max) {
                        this.max = max;
                    }

                    public AvgDTO getAvg() {
                        return avg;
                    }

                    public void setAvg(AvgDTO avg) {
                        this.avg = avg;
                    }

                    public MinDTO getMin() {
                        return min;
                    }

                    public void setMin(MinDTO min) {
                        this.min = min;
                    }

                    public static class MaxDTO implements Serializable {
                        @SerializedName("chn")
                        private Integer chn;
                        @SerializedName("usa")
                        private Integer usa;

                        public Integer getChn() {
                            return chn;
                        }

                        public void setChn(Integer chn) {
                            this.chn = chn;
                        }

                        public Integer getUsa() {
                            return usa;
                        }

                        public void setUsa(Integer usa) {
                            this.usa = usa;
                        }
                    }

                    public static class AvgDTO implements Serializable {
                        @SerializedName("chn")
                        private Integer chn;
                        @SerializedName("usa")
                        private Integer usa;

                        public Integer getChn() {
                            return chn;
                        }

                        public void setChn(Integer chn) {
                            this.chn = chn;
                        }

                        public Integer getUsa() {
                            return usa;
                        }

                        public void setUsa(Integer usa) {
                            this.usa = usa;
                        }
                    }

                    public static class MinDTO implements Serializable {
                        @SerializedName("chn")
                        private Integer chn;
                        @SerializedName("usa")
                        private Integer usa;

                        public Integer getChn() {
                            return chn;
                        }

                        public void setChn(Integer chn) {
                            this.chn = chn;
                        }

                        public Integer getUsa() {
                            return usa;
                        }

                        public void setUsa(Integer usa) {
                            this.usa = usa;
                        }
                    }
                }

                public static class Pm25DTOX implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("max")
                    private Integer max;
                    @SerializedName("avg")
                    private Integer avg;
                    @SerializedName("min")
                    private Integer min;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public Integer getMax() {
                        return max;
                    }

                    public void setMax(Integer max) {
                        this.max = max;
                    }

                    public Integer getAvg() {
                        return avg;
                    }

                    public void setAvg(Integer avg) {
                        this.avg = avg;
                    }

                    public Integer getMin() {
                        return min;
                    }

                    public void setMin(Integer min) {
                        this.min = min;
                    }
                }
            }

            public static class LifeIndexDTOX implements Serializable {
                @SerializedName("ultraviolet")
                private List<UltravioletDTOX> ultraviolet;
                @SerializedName("carWashing")
                private List<CarWashingDTO> carWashing;
                @SerializedName("dressing")
                private List<DressingDTO> dressing;
                @SerializedName("comfort")
                private List<ComfortDTOX> comfort;
                @SerializedName("coldRisk")
                private List<ColdRiskDTO> coldRisk;

                public List<UltravioletDTOX> getUltraviolet() {
                    return ultraviolet;
                }

                public void setUltraviolet(List<UltravioletDTOX> ultraviolet) {
                    this.ultraviolet = ultraviolet;
                }

                public List<CarWashingDTO> getCarWashing() {
                    return carWashing;
                }

                public void setCarWashing(List<CarWashingDTO> carWashing) {
                    this.carWashing = carWashing;
                }

                public List<DressingDTO> getDressing() {
                    return dressing;
                }

                public void setDressing(List<DressingDTO> dressing) {
                    this.dressing = dressing;
                }

                public List<ComfortDTOX> getComfort() {
                    return comfort;
                }

                public void setComfort(List<ComfortDTOX> comfort) {
                    this.comfort = comfort;
                }

                public List<ColdRiskDTO> getColdRisk() {
                    return coldRisk;
                }

                public void setColdRisk(List<ColdRiskDTO> coldRisk) {
                    this.coldRisk = coldRisk;
                }

                public static class UltravioletDTOX implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("index")
                    private String index;
                    @SerializedName("desc")
                    private String desc;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getIndex() {
                        return index;
                    }

                    public void setIndex(String index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class CarWashingDTO implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("index")
                    private String index;
                    @SerializedName("desc")
                    private String desc;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getIndex() {
                        return index;
                    }

                    public void setIndex(String index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class DressingDTO implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("index")
                    private String index;
                    @SerializedName("desc")
                    private String desc;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getIndex() {
                        return index;
                    }

                    public void setIndex(String index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class ComfortDTOX implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("index")
                    private String index;
                    @SerializedName("desc")
                    private String desc;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getIndex() {
                        return index;
                    }

                    public void setIndex(String index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class ColdRiskDTO implements Serializable {
                    @SerializedName("date")
                    private String date;
                    @SerializedName("index")
                    private String index;
                    @SerializedName("desc")
                    private String desc;

                    public String getDate() {
                        return date;
                    }

                    public void setDate(String date) {
                        this.date = date;
                    }

                    public String getIndex() {
                        return index;
                    }

                    public void setIndex(String index) {
                        this.index = index;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }
            }

            public static class AstroDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("sunrise")
                private SunriseDTO sunrise;
                @SerializedName("sunset")
                private SunsetDTO sunset;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public SunriseDTO getSunrise() {
                    return sunrise;
                }

                public void setSunrise(SunriseDTO sunrise) {
                    this.sunrise = sunrise;
                }

                public SunsetDTO getSunset() {
                    return sunset;
                }

                public void setSunset(SunsetDTO sunset) {
                    this.sunset = sunset;
                }

                public static class SunriseDTO implements Serializable {
                    @SerializedName("time")
                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }

                public static class SunsetDTO implements Serializable {
                    @SerializedName("time")
                    private String time;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }
                }
            }

            public static class Precipitation08h20hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;
                @SerializedName("probability")
                private Integer probability;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }

                public Integer getProbability() {
                    return probability;
                }

                public void setProbability(Integer probability) {
                    this.probability = probability;
                }
            }

            public static class Precipitation20h32hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;
                @SerializedName("probability")
                private Integer probability;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }

                public Integer getProbability() {
                    return probability;
                }

                public void setProbability(Integer probability) {
                    this.probability = probability;
                }
            }

            public static class PrecipitationDTOXX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;
                @SerializedName("probability")
                private Integer probability;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }

                public Integer getProbability() {
                    return probability;
                }

                public void setProbability(Integer probability) {
                    this.probability = probability;
                }
            }

            public static class TemperatureDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class Temperature08h20hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class Temperature20h32hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class WindDTOXX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private MaxDTOX max;
                @SerializedName("min")
                private MinDTOX min;
                @SerializedName("avg")
                private AvgDTOX avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public MaxDTOX getMax() {
                    return max;
                }

                public void setMax(MaxDTOX max) {
                    this.max = max;
                }

                public MinDTOX getMin() {
                    return min;
                }

                public void setMin(MinDTOX min) {
                    this.min = min;
                }

                public AvgDTOX getAvg() {
                    return avg;
                }

                public void setAvg(AvgDTOX avg) {
                    this.avg = avg;
                }

                public static class MaxDTOX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }

                public static class MinDTOX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }

                public static class AvgDTOX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }
            }

            public static class Wind08h20hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private MaxDTOXX max;
                @SerializedName("min")
                private MinDTOXX min;
                @SerializedName("avg")
                private AvgDTOXX avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public MaxDTOXX getMax() {
                    return max;
                }

                public void setMax(MaxDTOXX max) {
                    this.max = max;
                }

                public MinDTOXX getMin() {
                    return min;
                }

                public void setMin(MinDTOXX min) {
                    this.min = min;
                }

                public AvgDTOXX getAvg() {
                    return avg;
                }

                public void setAvg(AvgDTOXX avg) {
                    this.avg = avg;
                }

                public static class MaxDTOXX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }

                public static class MinDTOXX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }

                public static class AvgDTOXX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }
            }

            public static class Wind20h32hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private MaxDTOXXX max;
                @SerializedName("min")
                private MinDTOXXX min;
                @SerializedName("avg")
                private AvgDTOXXX avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public MaxDTOXXX getMax() {
                    return max;
                }

                public void setMax(MaxDTOXXX max) {
                    this.max = max;
                }

                public MinDTOXXX getMin() {
                    return min;
                }

                public void setMin(MinDTOXXX min) {
                    this.min = min;
                }

                public AvgDTOXXX getAvg() {
                    return avg;
                }

                public void setAvg(AvgDTOXXX avg) {
                    this.avg = avg;
                }

                public static class MaxDTOXXX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }

                public static class MinDTOXXX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }

                public static class AvgDTOXXX implements Serializable {
                    @SerializedName("speed")
                    private Double speed;
                    @SerializedName("direction")
                    private Double direction;

                    public Double getSpeed() {
                        return speed;
                    }

                    public void setSpeed(Double speed) {
                        this.speed = speed;
                    }

                    public Double getDirection() {
                        return direction;
                    }

                    public void setDirection(Double direction) {
                        this.direction = direction;
                    }
                }
            }

            public static class HumidityDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class CloudrateDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class PressureDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class VisibilityDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class DswrfDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("max")
                private Double max;
                @SerializedName("min")
                private Double min;
                @SerializedName("avg")
                private Double avg;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public Double getMax() {
                    return max;
                }

                public void setMax(Double max) {
                    this.max = max;
                }

                public Double getMin() {
                    return min;
                }

                public void setMin(Double min) {
                    this.min = min;
                }

                public Double getAvg() {
                    return avg;
                }

                public void setAvg(Double avg) {
                    this.avg = avg;
                }
            }

            public static class SkyconDTOX implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("value")
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class Skycon08h20hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("value")
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class Skycon20h32hDTO implements Serializable {
                @SerializedName("date")
                private String date;
                @SerializedName("value")
                private String value;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
