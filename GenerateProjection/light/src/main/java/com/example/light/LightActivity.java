package com.example.light;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.alibaba.android.arouter.facade.annotation.Route;


@Route(path = "/Light/LightActivity")
public class LightActivity extends AppCompatActivity {
    private static final String TAG="LightActivity";
    SeekBar lightStrength;
    String cameId;
    ConstraintLayout constraintLayout;
    CameraDevice device;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        lightStrength = findViewById(R.id.lightStrength);
        constraintLayout = findViewById(R.id.lightBack);
        lightStrength.setProgress(0);
        lightStrength.setMin(0);
        lightStrength.setMax(5);
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] Ids = cameraManager.getCameraIdList();
            for (String now : Ids) {
                if (cameraManager.getCameraCharacteristics(now).get(CameraCharacteristics.LENS_FACING)
                        == CameraCharacteristics.LENS_FACING_BACK &&
                cameraManager.getCameraCharacteristics(now).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                    cameId = now;
                    Log.d(TAG,"Default:"+"Max:");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        Integer r1 = cameraManager.getCameraCharacteristics(now).get(CameraCharacteristics.FLASH_INFO_STRENGTH_DEFAULT_LEVEL);
                        Integer r2 = cameraManager.getCameraCharacteristics(now).get(CameraCharacteristics.FLASH_INFO_STRENGTH_MAXIMUM_LEVEL);
                        Log.d(TAG,"Default:"+r1+"Max:"+r2);
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        lightStrength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    cameraManager.setTorchMode(cameId,true);
                    constraintLayout.setBackgroundColor(getResources().getColor(R.color.light));
                    cameraManager.registerTorchCallback(mTorchCallBack,null);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        cameraManager.turnOnTorchWithStrengthLevel(cameId,progress);
                    }
                } catch (CameraAccessException e) {
                    throw new RuntimeException(e);
                }

                if(progress <= 0){
                    try {
                        cameraManager.setTorchMode(cameId,false);
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.noLight));
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private CameraManager.TorchCallback mTorchCallBack = new CameraManager.TorchCallback() {
        @Override
        public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
            super.onTorchModeChanged(cameraId, enabled);
        }

        @Override
        public void onTorchStrengthLevelChanged(@NonNull String cameraId, int newStrengthLevel) {
            super.onTorchStrengthLevelChanged(cameraId, newStrengthLevel);
        }
    };
}