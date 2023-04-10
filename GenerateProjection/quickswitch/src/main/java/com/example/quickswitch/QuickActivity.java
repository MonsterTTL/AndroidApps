package com.example.quickswitch;
import android.Manifest;
import android.app.NotificationManager;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;


import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;


@Route(path = "/quick/quickActivity")
public class QuickActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result == true){

                    }else{
                        Toast.makeText(QuickActivity.this, "您需要以下权限", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    Button wifiSwitch;//wifi开关
    Button blueSwitch;//蓝牙开关
    SimpleViewModel simpleViewModel;//存储状态的ViewModel
    TabLayout Change_State;
    detailed_Method MyMethod = new detailed_Method();
    TextView wifi_text;
    TextView blue_text;
    SeekBar RingVoice;
    SeekBar MediaVoice;


    CircleImageView displaySetting;
    CircleImageView blueSetting;
    CircleImageView wifiSetting;
    CircleImageView clockSetting;
    CircleImageView locateSetting;
    CircleImageView voiceSetting;
    CircleImageView mobileSetting;
    final int REQUEST_WIFI_CHANGE = 0;
    final int REQUEST_BLUETOOTH_CHANGE = 1;
    final int REQUEST_BLUETOOTH_CONNECT = 2;
    final int REQUEST_AUDIO = 3;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_activity_main);
        simpleViewModel = new ViewModelProvider(this).get(SimpleViewModel.class);
        Myinit();
        MyMethod.bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        MyMethod.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        MyMethod.notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        simpleViewModel.isBlueOpen.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    blue_text.setText("已开启");
                    blueSwitch.setText("关闭蓝牙");
                } else {
                    blue_text.setText("已关闭");
                    blueSwitch.setText("开启蓝牙");
                }
            }
        });
        //限制SeekBar的范围
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            RingVoice.setMin(0);
            MediaVoice.setMin(0);
        }
        RingVoice.setMax(MyMethod.audioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
        MediaVoice.setMax(MyMethod.getMediaMaxVoice());
        //TabLayout 中的 tab 的 id 并不是使用 View 的自增 id ，而是通过 TabLayout.Tab 对象的 hashCode() 方法生成的一个唯一值。
        // 因此，即使你在 XML 中给每个 tab 指定了不同的 android:id，它们的 getId() 方法返回的值仍然会相同。
        //如果需要在 TabLayout 中识别不同的 Tab，可以使用 TabLayout.Tab 对象本身作为唯一的标识符。
        // 可以在创建每个 Tab 时，使用 Tab.setTag(Object) 方法为其设置一个标识符对象，在后续的处理中使用这个标识符来区分不同的 Tab。
        Change_State.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getText().toString()){
                   case "  响铃  " :
                       Change_State.setBackgroundColor(getResources().getColor(R.color.orange));
                       RingVoice.setOnTouchListener(new View.OnTouchListener() {public boolean onTouch(View v, MotionEvent event) {
                               return false;
                           }});
                       RingVoice.setProgress(MyMethod.LastRingVoice);
                       break;
                   case "  静音  ":
                       MyMethod.LastRingVoice = MyMethod.getRingVoice();
                       MyMethod.openSilentMode();
                       Change_State.setBackgroundColor(getResources().getColor(R.color.blue));
                       RingVoice.setProgress(0);
                       RingVoice.setOnTouchListener(new View.OnTouchListener() {
                           public boolean onTouch(View v, MotionEvent event) {return true;}});//返回true，禁止拖动
                       break;
                   case "  勿扰  ":
                       MyMethod.LastRingVoice = MyMethod.getRingVoice();
                       MyMethod.openDNDmod();
                       Change_State.setBackgroundColor(getResources().getColor(R.color.purple_500));
                       RingVoice.setProgress(0);
                       RingVoice.setOnTouchListener(new View.OnTouchListener() {
                           public boolean onTouch(View v, MotionEvent event) {return true;}});
                       break;
                   default:
                       break;

               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getText().toString()){
                    case "  响铃  " :
                        break;
                    case "  静音  ":
                        MyMethod.closeSilentMode();
                        break;
                    case "  勿扰  ":
                        MyMethod.closeDNDmod();
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        RingVoice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MyMethod.setRingVoice(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        MediaVoice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MyMethod.setMediaVoice(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


    }

    private void Myinit() {
        wifiSwitch = findViewById(R.id.button_wifi);
        blueSwitch = findViewById(R.id.button_blue);
        wifi_text = findViewById(R.id.statue_wifi);
        blue_text = findViewById(R.id.statue_bule);
        RingVoice = findViewById(R.id.ringVoice);
        Change_State = findViewById(R.id.Change_State);
        MediaVoice = findViewById(R.id.mediaVoice);
        displaySetting = findViewById(R.id.displaySetting);
        voiceSetting = findViewById(R.id.voiceSetting);
        locateSetting = findViewById(R.id.locateSetting);
        mobileSetting = findViewById(R.id.mobileSetting);
        blueSetting = findViewById(R.id.blueSetting);
        wifiSetting = findViewById(R.id.wifiSetting);
        clockSetting = findViewById(R.id.clockSetting);
        blueSetting.setOnClickListener(this);
        displaySetting.setOnClickListener(this);
        wifiSetting.setOnClickListener(this);
        clockSetting.setOnClickListener(this);
        locateSetting.setOnClickListener(this);
        voiceSetting.setOnClickListener(this);
        mobileSetting.setOnClickListener(this);
        wifiSwitch.setOnClickListener(this);
        blueSwitch.setOnClickListener(this);
    }

    private void jumpToSysSet(String action){
        Intent intent = new Intent(action);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_wifi) {//获取Wifi状态，如果是原来是开启状态就关闭，反之开启
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
        } else if (id == R.id.button_blue) {
            if (simpleViewModel.getBlueStatue()) {
                MyMethod.closeBlue();
                //simpleViewModel.CloseBlue();
            } else {
                MyMethod.openBlue();
                //simpleViewModel.OpenBlue();
            }
        } else if (id == R.id.blueSetting) {
            jumpToSysSet(Settings.ACTION_BLUETOOTH_SETTINGS);
        } else if (id == R.id.wifiSetting) {
            jumpToSysSet(Settings.ACTION_WIFI_SETTINGS);
        } else if (id == R.id.clockSetting) {
            jumpToSysSet(Settings.ACTION_DATE_SETTINGS);
        } else if (id == R.id.displaySetting) {
            jumpToSysSet(Settings.ACTION_DISPLAY_SETTINGS);
        } else if (id == R.id.locateSetting) {
            jumpToSysSet(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        } else if (id == R.id.voiceSetting) {
            jumpToSysSet(Settings.ACTION_SOUND_SETTINGS);
        } else if (id == R.id.mobileSetting) {
            jumpToSysSet(Settings.ACTION_DATA_ROAMING_SETTINGS);
        }
    }


    //内部类，具体用来实现系统状态的改变
    class detailed_Method {
        //wifi管理器
        public WifiManager wifiManager;//可能没啥用
        //蓝牙管理器
        public BluetoothManager bluetoothManager;//蓝牙管理器
        public AudioManager audioManager;//音频管理器
        public NotificationManager notificationManager;//通知管理器
        public int LastRingVoice;//上一次铃声的音量
        public int LastMediaVoice;//上一次媒体的音量
        //打开蓝牙
        public void openBlue() {
            if (ActivityCompat.checkSelfPermission(QuickActivity.this, Manifest.permission.BLUETOOTH)
                    != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(QuickActivity.this,Manifest.permission.BLUETOOTH_CONNECT)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(QuickActivity.this,new String[]{
                            Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_CONNECT
                    },REQUEST_BLUETOOTH_CHANGE);
                    return;
                }
            if(bluetoothManager.getAdapter().enable() == true){
                simpleViewModel.OpenBlue();
            }
        }

        public void closeBlue(){
            if (ActivityCompat.checkSelfPermission(QuickActivity.this, Manifest.permission.BLUETOOTH)
                    != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(QuickActivity.this,Manifest.permission.BLUETOOTH_CONNECT)
                            != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(QuickActivity.this,new String[]{
                        Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_CONNECT
                },REQUEST_BLUETOOTH_CHANGE);
                return;
            }
            if(bluetoothManager.getAdapter().disable() == true){
                simpleViewModel.CloseBlue();
            }
        }
        //开启静音模式
        public void openSilentMode(){
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.MODIFY_AUDIO_SETTINGS)
            != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(QuickActivity.this,new String[]{Manifest.permission.MODIFY_AUDIO_SETTINGS}
                        ,REQUEST_AUDIO);
                return;
            }
            //静音模式
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }

        public void closeSilentMode(){
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.MODIFY_AUDIO_SETTINGS)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(QuickActivity.this,new String[]{Manifest.permission.MODIFY_AUDIO_SETTINGS}
                        ,REQUEST_AUDIO);
                return;
            }
            //静音模式
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //开启勿扰模式
        public void openDNDmod(){
            if (notificationManager.isNotificationPolicyAccessGranted()) {//检查是否有开启勿扰模式的权限
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
                //设置通知过滤器为无，即所有通知都将被抑制
            } else {
                // 请求用户授权
                Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }
        public void closeDNDmod(){
            if (notificationManager.isNotificationPolicyAccessGranted()) {//检查是否有开启勿扰模式的权限
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
                //设置通知过滤器为所有，即所有通知都不将被抑制
            } else {
                // 请求用户授权
                Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }

        public void setRingVoice(int number){
            launcher.launch(Manifest.permission.MODIFY_AUDIO_SETTINGS);
            launcher.launch(Manifest.permission.ACCESS_NOTIFICATION_POLICY);
            closeSilentMode();
            audioManager.setStreamVolume(AudioManager.STREAM_RING,number,AudioManager.FLAG_PLAY_SOUND);
        }

        public int getRingVoice(){
            launcher.launch(Manifest.permission.MODIFY_AUDIO_SETTINGS);
            launcher.launch(Manifest.permission.ACCESS_NOTIFICATION_POLICY);

            return audioManager.getStreamVolume(AudioManager.STREAM_RING);
        }

        public int getRingMaxVoice(){
            return audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        }

        public void setMediaVoice(int num){
            launcher.launch(Manifest.permission.MODIFY_AUDIO_SETTINGS);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,num,AudioManager.FLAG_PLAY_SOUND);
        }

        public int getMediaVoice(){
            return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }

        public int getMediaMaxVoice(){
            return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_WIFI_CHANGE){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
                return;
            // 如果权限请求被授予，则执行打开/关闭WiFi的操作
        }else if(requestCode == REQUEST_BLUETOOTH_CHANGE){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
                return;
            else if(grantResults[1] != PackageManager.PERMISSION_GRANTED)
                return;
            // 如果权限请求被授予，则执行打开/关闭蓝牙的操作
            if(simpleViewModel.getBlueStatue())
                MyMethod.openBlue();
            else
                MyMethod.closeBlue();
        }else if(requestCode == REQUEST_AUDIO){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        RingVoice.setProgress(MyMethod.getRingVoice());
        MediaVoice.setProgress(MyMethod.getMediaVoice());
    }


}

