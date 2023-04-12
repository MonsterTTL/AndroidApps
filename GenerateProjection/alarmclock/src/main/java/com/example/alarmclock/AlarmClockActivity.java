package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;

@Route(path = "/Alarm/AlarmClock")
public class AlarmClockActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    View set_clock;
    View open_systemClock;
    View clear_alarm;
    AlarmManager manager;
    Calendar now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        set_clock = findViewById(R.id.set_clock);
        open_systemClock = findViewById(R.id.system_clock);
        clear_alarm = findViewById(R.id.clear_alarm);
        now = Calendar.getInstance();

        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        set_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = TimePickerDialog.newInstance(AlarmClockActivity.this,
             true);
                tpd.show(getSupportFragmentManager(),"chooseAlarmClock");
            }
        });

        open_systemClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(intent);
            }
        });

        clear_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取闹钟管理器
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                //删除所有闹钟
                Intent intent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE,"悠然-闹钟");
                startActivity(intent);

            }
        });



    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+ hourOfDay+"h"+minute+"m"+second;
        Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
        //now.setTimeInMillis(System.currentTimeMillis());
        //now.set(Calendar.HOUR_OF_DAY,hourOfDay,Calendar.MINUTE,minute,Calendar.SECOND,second);
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR,hourOfDay);
        intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,"悠然-闹钟");
        intent.putExtra(AlarmClock.EXTRA_VIBRATE,true);
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        startActivity(intent);

    }
}