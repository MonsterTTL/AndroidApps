package com.example.generateprojection.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class NavAdapter extends FragmentStateAdapter {


    private List<Fragment> fragmentList;
    public NavAdapter(FragmentActivity owner, List<Fragment> fragmentList){
        super(owner);
        this.fragmentList =  fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.isEmpty( )? 0 : fragmentList.size();
    }
}
