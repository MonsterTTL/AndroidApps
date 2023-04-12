package com.example.remote_help;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.remote_help.sharescreen.Helper;

import java.util.concurrent.BlockingDeque;

public class MiddleActivity2 extends AppCompatActivity {

    private Button button;

    private String phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle2);

        Intent intent = getIntent();
        phone = intent.getStringExtra("number");

        button = (Button) findViewById(R.id.button22);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MiddleActivity2.this, Helper.class);
                intent.putExtra("roomid",phone);
                Log.d("测试进入的房间的号", "onClick: " + phone);
                startActivity(intent);
            }
        });
    }
}