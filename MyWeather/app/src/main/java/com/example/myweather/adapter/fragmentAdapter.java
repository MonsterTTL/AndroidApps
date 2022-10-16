package com.example.myweather.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myweather.fragmentClass.WeatherFragment;

import java.util.List;

public class fragmentAdapter extends FragmentStatePagerAdapter {

    private List<WeatherFragment> mList;

    private WeatherFragment mCurrentFragment;

    public fragmentAdapter(FragmentManager manager, List<WeatherFragment> mList){
        super(manager);
        this.mList = mList;
    }

    public Fragment getItem(int position){
        return mList.get(position);
    }

    public int getCount(){
        return mList.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mCurrentFragment = (WeatherFragment)object;
        super.setPrimaryItem(container, position, object);
    }

    public WeatherFragment getCurrentFragment(){
        return mCurrentFragment;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        WeatherFragment fragment = (WeatherFragment)super.instantiateItem(container, position);
        Log.d("test","Executed");
        return fragment;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
