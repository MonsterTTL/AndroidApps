package com.example.generateprojection;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.generateprojection.adapter.NavAdapter;
import com.example.generateprojection.fragmentS.home_navFragment;
import com.example.generateprojection.fragmentS.news_navFragment;
import com.example.generateprojection.fragmentS.weather_navFragment;
import com.example.generateprojection.viewmodel.weatherViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ViewPager2 appViewPager2;
    BottomNavigationView appBottomNav;
    NavAdapter adapter;
    List<Fragment> fragmentList;



    public AMapLocationClient mLocationClient = null;

    public AMapLocationClientOption mLocationOption = null;

    private Boolean isLocation_allow = false;

    ActivityResultLauncher<String[]> locationPermissions = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions()
            , new ActivityResultCallback<Map<String, Boolean>>() {
                @Override
                public void onActivityResult(Map<String, Boolean> result) {
                    Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION,false);
                    Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
                    if (fineLocationGranted && coarseLocationGranted){
                        isLocation_allow = true;
                    }
                }
            });

    //位置信息
    private double jingdu;
    private double weidu;
    private String prince;
    private String city;

    weather_navFragment weather_navFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSystemBar(R.color.toolbar);
        appViewPager2 = findViewById(R.id.AppViewPager2);
        appBottomNav = findViewById(R.id.bottomNavigationView);
        weather_navFragment = new weather_navFragment();
        fragmentList = Arrays.asList(new home_navFragment(),weather_navFragment,new news_navFragment());
        adapter = new NavAdapter(this,fragmentList);
        appViewPager2.setAdapter(adapter);
        appViewPager2.setCurrentItem(0);
        appViewPager2.setUserInputEnabled(false);
        appBottomNav.setSelectedItemId(R.id.navItem_home);
        appBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navItem_home://常用
                        appViewPager2.setCurrentItem(0);
                        break;
                    case R.id.navItem_news:
                        appViewPager2.setCurrentItem(2);
                        break;
                    case R.id.navItem_weather:
                        appViewPager2.setCurrentItem(1);
                        break;
                }
                item.setChecked(true);
                return false;
            }
        });

        weatherViewModel model = new ViewModelProvider(MainActivity.this).get(weatherViewModel.class);
        model.flag.observe(MainActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                weather_navFragment.refreshUI();
            }
        });


        //这里开始是高德定位的设置
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            locationPermissions.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }else{
            isLocation_allow = true;
        }

        //隐私接口检查--必须在实例化之前调用
        AMapLocationClient.updatePrivacyShow(getApplicationContext(),true,true);
        AMapLocationClient.updatePrivacyAgree(getApplicationContext(),true);
        try {
            mLocationClient = new AMapLocationClient(getApplicationContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //监听器
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation != null){
                    if(aMapLocation.getErrorCode() == 0)
                    {
                        //获取纬度
                        jingdu = aMapLocation.getLatitude();
                        //获取经度
                        weidu = aMapLocation.getLongitude();
                        //获取省份
                        prince = aMapLocation.getProvince();
                        //获取城市信息
                        city = aMapLocation.getCity();
                        Log.d(TAG, "onLocationChanged: "+city);
                        //获取碎片实例以进行通信

                        //参数传递
                        model.setLocationXY(jingdu,weidu);

                        //adapter.notifyItemChanged(2);

                        Log.d(TAG, "x:"+ jingdu +" y:"+ weidu);
                        Log.d(TAG, "city:"+ city +" province:"+ prince);
                    }else{
                        Log.d(TAG, "onLocationChanged: "+"解析失败。错误码不为零");
                    }
                }else{//解析失败的情况
                    Log.d(TAG, "onLocationChanged: "+"解析错误");
                }
            }
        });//设置监听器
        mLocationOption = new AMapLocationClientOption();//设置请求参数
        //开始定位参数的配置

        //设置定位场景
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        //设置定位模式为高精度定位
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置连续定位(默认就是连续定位，每隔2000ms)
        mLocationOption.setInterval(2000);//设置周期为4000ms
        mLocationOption.setOnceLocation(true);//设置单次定位
        //设置是否返回地址信息
        mLocationOption.setNeedAddress(true);

        if(null != mLocationClient && isLocation_allow){
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.stopLocation();
            mLocationClient.startLocation();//开始定位
        }else{
            Log.d(TAG, "onCreate: "+"权限不足 或者 客户端为空");
        }





    }

    private static int getStatusBarHeight(Context context){
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height","dimen","android");
        if(resourceId > 0){
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    private void initSystemBar(int color){
        Window window = this.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(getApplicationContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(color));
        decorViewGroup.addView(statusBarView);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}