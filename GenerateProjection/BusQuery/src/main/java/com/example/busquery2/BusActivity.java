package com.example.busquery2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.AppUtils;
import com.example.busquery.R;
import com.example.busquery2.GSON.BusRoutesGson.ReturlListBusBean;
import com.example.busquery2.GSON.RealTimeLocation.Location;
import com.example.busquery2.GSON.RealTimeLocation.StationsLocationBean;
import com.example.busquery2.OkHttp.BusRoutes;
import com.example.busquery2.OkHttp.CityList;
import com.example.busquery2.OkHttp.DepartureTimetable;
import com.example.busquery2.OkHttp.RealTimeLocation;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/Bus/BusActivity")
public class BusActivity extends AppCompatActivity {

    private static final String TAG = "BUS";


    CityList cityList;

    BusRoutes busRoutes;

    DepartureTimetable departureTimetable;

    RealTimeLocation realTimeLocation;

    public String Nowcity = null;

    private String Nowcitynumber = null;

    public LocationClient mLocationClient;

    private TextView busmessage;

    private ImageButton getbusnumber;

    private EditText busnumber;

    private String nowbusnumber;

    private StringBuffer message = new StringBuffer();

    private ReturlListBusBean busid = new ReturlListBusBean();

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            switch (message.what){
                case 1:
                    //在这里接收所在城市
                    Bundle bundle = message.getData();
                    Nowcity = bundle.getString("nowcitykey");
                    initCityId();
                    break;
                case 2:
                    //在这里接收所在城市id
                    Bundle bundle1 = message.getData();
                    Nowcitynumber = bundle1.getString("nowcityidkey");
                    Log.d(TAG, "handleMessage2: " + Nowcitynumber);
                    break;
                case 3:
                    //在这里接收需要的路线
                    Bundle bundle2 = message.getData();
                    if(bundle2.getString("ErrorCode").equals("gg")){
                        Toast.makeText(BusActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
                    }else{
                        busid.setBusLinestrid(bundle2.getString("busLinestridkey"));
                        busid.setBusLinenum(bundle2.getString("busLinenumkey"));
                        busid.setBusStaname(bundle2.getString("busStanamekey"));
                        Log.d(TAG, "handleMessage3: " + busid.getBusLinenum());
                        initRealTimeLocation();
                    }
                    break;
                case 4:
                    //在这里处理
                    Bundle bundle3 = message.getData();
                    parseLocation(bundle3.getString("buslocationkey"));
                    Log.d(TAG, "handleMessage:4 " + bundle3.getString("buslocationkey"));
                    break;
                default:
                    break;
            }
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);


        for (int i = 0; i < AppUtils.getAppSignaturesSHA1().size(); i++) {
            Log.d(TAG, "onCreate: SHA1:" + AppUtils.getAppSignaturesSHA1().get(i));
        }

        initbaidu();

        busnumber =  findViewById(R.id.busnumber);
        busmessage =  findViewById(R.id.bus_textView);

        getbusnumber = findViewById(R.id.getbusnumber);
        getbusnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowbusnumber = busnumber.getText().toString();
                initBusRoutes();
            }
        });
    }

    private void parseLocation(String jsonData){
        Gson gson = new Gson();
        Location location = gson.fromJson(jsonData, Location.class);
        for(StationsLocationBean re :location.getReturlList().getStations()){
            message.append(re.getBusStaname() + "\n");
        }
        busmessage.setText(message);
    }

    private void initRealTimeLocation(){
        realTimeLocation = new RealTimeLocation(busid,Nowcitynumber,handler);
        realTimeLocation.sendRequestWithOkHttp();
    }

    private void initBusRoutes(){
        busRoutes = new BusRoutes(Nowcitynumber,nowbusnumber,handler);
        busRoutes.setContext(BusActivity.this);
        busRoutes.sendRequestWithOkHttp();
    }


    private void initCityId(){
        Log.d(TAG, "onCreate: 获取的目前所在城市" + Nowcity);
        cityList = new CityList(Nowcity,handler);
        cityList.sendRequestWithOkHttp();
    }

    private void initbaidu(){
        LocationClient.setAgreePrivacy(true);
        try {
            mLocationClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mLocationClient.registerLocationListener(new MyLocationListener());
        setContentView(R.layout.activity_bus);
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(BusActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            Log.d("123456", "123444444444");
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(!permissionList.isEmpty()){
            Log.d("123456", "开始给权限了");
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(BusActivity.this, permissions, 1);
        }else {
            Log.d("123456", "开始调用定位了" );
            requestLocation();
        }
    }

    private void requestLocation(){
        initLocation();
        Log.d("123456", "开始定位了");
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(100000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }



    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
                    Log.d("123456", "run: " + bdLocation.getLatitude());
                    currentPosition.append("经度："). append(bdLocation.getLongitude()).append("\n");
                    Log.d("654321", "run: " + bdLocation.getLatitude());
                    currentPosition.append("国家：").append(bdLocation.getCountryCode()).append("\n");
                    currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("市:").append(bdLocation.getCity()).append("\n");
                    Log.d(TAG, "run: 123" + bdLocation.getCity());

                    Message message = new Message();
                    message.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putString("nowcitykey",bdLocation.getCity());
                    message.setData(bundle);
                    handler.sendMessage(message);


                    currentPosition.append("区:").append(bdLocation.getDistrict()).append("\n");
                    currentPosition.append("乡镇:").append(bdLocation.getTown()).append("\n");
                    currentPosition.append("街道:").append(bdLocation.getStreet()).append("\n");
                    currentPosition.append("代码adCode:").append(bdLocation.getAdCode()).append("\n");
                    currentPosition.append("位置描述信息:").append(bdLocation.getLocationDescribe()).append("\n");
                    currentPosition.append("位置描述id:").append(bdLocation.getLocationID()).append("\n");
                    currentPosition.append("所有省份:").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("定位方式: ");
                    if(bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS");
                    }else if(bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("网络");
                    }
                    Log.d("123", "获得的gps信息: " + currentPosition);
                }
            });
        }
    }






}