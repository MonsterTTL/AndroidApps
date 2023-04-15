package com.example.generateprojection.fragmentS;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.generateprojection.R;
import com.example.generateprojection.helper.AudioHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class home_navFragment extends Fragment implements AudioHelper.SpeechCallBack {

    CardView remoteAssit;
    CardView magnifyGlass;
    CardView bus ;
    View light;
    CardView quickSettings;
    CardView LightS;
    CardView AlarmClock;
    FloatingActionButton audioInput;
    ActivityResultLauncher<String> launcher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_navfragment,container,false);
        remoteAssit = view.findViewById(R.id.remote_assit);
        magnifyGlass = view.findViewById(R.id.magnify);
        LightS = view.findViewById(R.id.LightS);
        audioInput = view.findViewById(R.id.audioInput);
        quickSettings = view.findViewById(R.id.quickSetting);
        bus = view.findViewById(R.id.calendar);
        AlarmClock = view.findViewById(R.id.alarm_clock);
        launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission()
                , new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result == true){
                            new AudioHelper(home_navFragment.this).useSpeech(requireContext());
                        }else{
                            Toast.makeText(requireContext(), "无录音权限！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        magnifyGlass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/camera/cameraActivity").navigation();
            }
        });
        quickSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/quick/quickActivity").navigation();
            }
        });
        remoteAssit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/remote/remoteActivity").navigation();
            }
        });
        LightS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/Light/LightActivity").navigation();
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/Bus/BusActivity").navigation();
            }
        });

        AlarmClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/Alarm/AlarmClock").navigation();
            }
        });



        //语音识别模块
        audioInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(requireActivity().checkSelfPermission(Manifest.permission.RECORD_AUDIO) !=
                        PackageManager.PERMISSION_GRANTED){
                    launcher.launch(Manifest.permission.RECORD_AUDIO);
                }
                new AudioHelper(home_navFragment.this).useSpeech(requireContext());

            }
        });
    }


    //用于处理语音识别的回调
    @Override
    public void onListen(String result) {
        if(result == null){
            return ;
        }
        if(result.contains("放大镜")){
            ARouter.getInstance().build("/camera/cameraActivity").navigation();
        }else if (result.contains("远程协助")){
            ARouter.getInstance().build("/remote/remoteActivity").navigation();
        }else if(result.contains("快捷设置")){
            ARouter.getInstance().build("/quick/quickActivity").navigation();
        }else if(result.contains("手电筒")){
            ARouter.getInstance().build("/Light/LightActivity").navigation();
        }else if(result.contains("钟")){
            ARouter.getInstance().build("/Alarm/AlarmClock").navigation();
        }
    }
}