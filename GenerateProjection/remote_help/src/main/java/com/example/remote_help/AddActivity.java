package com.example.remote_help;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.remote_help.sqlite.Operate;

public class AddActivity extends AppCompatActivity {

    private EditText importName;

    private EditText importPhone;

    private ImageButton addPerson;

    private String name;

    private String phone;

    private Operate operate = Operate.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        importName = (EditText) findViewById(R.id.add_name);
        importPhone = (EditText) findViewById(R.id.add_phone);

        addPerson = (ImageButton) findViewById(R.id.add_person);
        Log.d("", "onCreateView: 这里是点击事件");
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里是添加的函数的声明
                name = importName.getText().toString();
                phone = importPhone.getText().toString();
                Log.d("sqladdfamily", "onClick: 名字" + name);
                Log.d("sqladdfamily", "onClick: 手机号" + phone);
                Log.d("sqladdfamily", "onClick: 这里已经把名字和手机号截取到了");
                operate.addSQL(name,phone);
                finish();
            }
        });
    }
}