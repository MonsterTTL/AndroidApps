package com.example.remote_help.sharescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.remote_help.RemoteActivity;
import com.example.remote_help.Person;
import com.example.remote_help.R;

@Route(path = "/remote/remoteActivity")
public class GetPhoneNumberActivity extends AppCompatActivity {

    private final String TAG = "GetPhoneNumberActivity";

    private Person person = Person.getInstance();

    private ImageButton button;

    private EditText editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone_number);

        //在最开始的时候就对person进行初始数据的初始化
        person.setAppID(609408564L);
        person.setAppSign("59fb6b3d70836007ba898832b22da9917c76576b26436737a218dbd538eb33d7");

        isPersonData();

        editText = (EditText) findViewById(R.id.editText);
        button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //在这里可以加一个限制手机号的判断的东西
                person.setUserID(editText.getText().toString());
                person.setRoomID(editText.getText().toString());
                person.setStreamID(editText.getText().toString());

                Log.d(TAG, "onClick: 检查是否添加到person" + person.getUserID());

                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("userid", person.getUserID());
                editor.apply();

                Log.d(TAG, "onClick: 添加成功,关闭该活动,并且进入到下一项");
                startMainActivity();
            }
        });

    }

    //判断是否之前有数据
    private void isPersonData(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid", "");
        person.setUserID(userid);
        person.setRoomID(userid);
        person.setStreamID(userid);
        Log.d(TAG, "isPersonData: 开机检测是否收到,本地传来的值" + userid);
        Log.d(TAG, "isPersonData: 开机检测是否收到,本地传来的值" + person.getUserID());

        //这个现在不能读取
        if(person.getUserID() != ""){
            Log.d(TAG, "isPersonData: 检测已经记录过了本机号码,直接跳过并且该活动,进入到下一项" + userid);
            startMainActivity();
        }
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, RemoteActivity.class);
        startActivity(intent);
        finish();
    }

}