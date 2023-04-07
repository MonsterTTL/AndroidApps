package com.example.generateprojection;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        TextView title = findViewById(R.id.start_title);
        TextView content = findViewById(R.id.start_content);

        //ObjectAnimator animator1 = ObjectAnimator.ofFloat(title,"translationX",200.f);
        //animator1.setDuration(1000);
        //animator1.start();
        //AnimatorSet set = new AnimatorSet();
        //set.setDuration(1000);
        //set.play(animator1);
        //set.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(startActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
