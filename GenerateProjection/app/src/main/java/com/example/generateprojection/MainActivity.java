package com.example.generateprojection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.generateprojection.adapter.NavAdapter;
import com.example.generateprojection.fragmentS.home_navFragment;
import com.example.generateprojection.fragmentS.news_navFragment;
import com.example.generateprojection.fragmentS.weather_navFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ViewPager2 appViewPager2;
    BottomNavigationView appBottomNav;
    NavAdapter adapter;
    List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSystemBar();
        appViewPager2 = findViewById(R.id.AppViewPager2);
        appBottomNav = findViewById(R.id.bottomNavigationView);
        fragmentList = Arrays.asList(new news_navFragment(),new home_navFragment(),new weather_navFragment());
        adapter = new NavAdapter(this,fragmentList);
        appViewPager2.setAdapter(adapter);
        appViewPager2.setCurrentItem(1);
        appViewPager2.setUserInputEnabled(false);
        appBottomNav.setSelectedItemId(R.id.navItem_home);
        appBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navItem_home://常用
                        appViewPager2.setCurrentItem(1);
                        break;
                    case R.id.navItem_news:
                        appViewPager2.setCurrentItem(0);
                        break;
                    case R.id.navItem_weather:
                        appViewPager2.setCurrentItem(2);
                        break;
                    case R.id.navItem_my:

                }

                item.setChecked(true);
                return false;
            }


        });
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

    private void initSystemBar(){
        Window window = this.getWindow();
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(getApplicationContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.toolbar));
        decorViewGroup.addView(statusBarView);
    }
}