package com.example.myweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myweather.adapter.*;
import com.bumptech.glide.Glide;
import com.example.myweather.Utils.IntnetTool;
import com.example.myweather.fragmentClass.WeatherFragment;
import com.example.myweather.gson.BaiDuLocat;
import com.example.myweather.gson.BaiDuWeather;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.myweather.dataLib.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressDialog progressDialog;
    FloatingActionButton dingwei;
    FloatingActionButton delete;
    BaiDuLocat nowLocat;
    Looper mainLooper = Looper.getMainLooper();
    FragmentManager manager;
    BaiDuWeather nowWeather;
    ViewPager viewPager;
    ArrayList<WeatherFragment> thisList = new ArrayList<>();
    ArrayList<BaiDuWeather> initWeathers = new ArrayList<>();
    fragmentAdapter fragmentAdapter;

    private weatherData dbHelper;

    private static final String DATABASE = "LibData.db";
    private static final String DATATAB = "Weather";

    private static final int UPDATE_WEATHER = 1;
    private static final int DELETE_ = 2;

    int test = 0;
    private Handler handler = new Handler(mainLooper){

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case UPDATE_WEATHER:
                        Log.d("tt",String.valueOf("tt="+thisList.size()));
                        viewPager.setAdapter(fragmentAdapter);
                        //fragmentAdapter.notifyDataSetChanged();
                        for(WeatherFragment fragment:thisList){
                            test++;
                            if(fragment.myWeather != null && test < 3)
                                fragment.refreshWeather();
                        }
                        //fragmentAdapter.notifyDataSetChanged();
                        //for(BaiDuWeather weather:initWeathers){
                        //    replaceFragmentInit(new WeatherFragment(),weather);
                        //}
                        break;

                case DELETE_ :
                    Snackbar.make(delete,"删除城市吗？",Snackbar.LENGTH_SHORT)
                            .setAction("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    WeatherFragment shouldDelete = fragmentAdapter.getCurrentFragment();
                                    BaiDuWeather weather = shouldDelete.myWeather;
                                    Log.d("exe","Exe"+fragmentAdapter.getCurrentFragment().myWeather
                                            .result.location.name);
                                    thisList.remove(shouldDelete);
                                    fragmentAdapter.notifyDataSetChanged();
                                    manager.beginTransaction().setReorderingAllowed(true)
                                            .remove(shouldDelete);
                                    deleteData(weather);
                                }
                            }).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        manager = getSupportFragmentManager();
        dingwei = findViewById(R.id.dingwei);
        delete = findViewById(R.id.delete);
        dbHelper = new weatherData(this,DATABASE,null,1);
        dbHelper.getWritableDatabase();
        //deleteAllData();
        initList();
        Glide.with(this).load(R.mipmap.dingwei).into(dingwei);
        Glide.with(this).load(R.drawable.delete_red).into(delete);
        dingwei.setOnClickListener(this);
        delete.setOnClickListener(this);

    }

    public void replaceFragment(WeatherFragment fragment,String data){
        showProgressDialog();
        BaiDuWeather weather = IntnetTool.handleWeather(data);
        if(!isDataExist(weather)){
            addItemToDataBase(weather);
            fragment.setMyWeather(weather);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    thisList.add(fragment);
                    fragmentAdapter.notifyDataSetChanged();
                    closeProgressDialog();
                }
            });
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"城市已存在！",Toast.LENGTH_SHORT)
                            .show();
                }
            });
            closeProgressDialog();
        }


    }

    //一个暂时的方法
    public void replaceFragmentInit(WeatherFragment fragment,BaiDuWeather weather){
        fragment.setMyWeather(weather);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                thisList.add(fragment);
                fragmentAdapter.notifyDataSetChanged();
            }
        });
    }


    //private void Test(){
    //    String data = IntnetTool.SendHttpWithHttpConnect(BaiDuWeather.BDWhea_Head + "330522"
    //    +BaiDuWeather.BDWhea_End);
    //    Log.d("test",data);
    //    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
    //            .edit();
    //    editor.putString("LAST_WEATHER",data);
    //    BaiDuWeather weather = IntnetTool.handleWeather(data);
    //}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dingwei:
                showProgressDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String ip = IntnetTool.getTrueIpAddress();
                        String data = IntnetTool.SendHttpWithHttpConnect(BaiDuLocat.BD_URI_HEAD
                        + ip + BaiDuLocat.BD_URI_END);
                        nowLocat = IntnetTool.handleBDdata(data);
                        String acode = nowLocat.content.addressDetail.adcode;
                        data = IntnetTool.SendHttpWithHttpConnect(BaiDuWeather.BDWhea_Head
                        + acode + BaiDuWeather.BDWhea_End);
                        nowWeather = IntnetTool.handleWeather(data);
                        if(!isDataExist(nowWeather)){
                            fragmentAdapter.getCurrentFragment().setMyWeather(nowWeather);
                            addItemToDataBase(nowWeather);
                        }
                        Message message = new Message();
                        message.what = UPDATE_WEATHER;
                        handler.sendMessage(message);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"定位完成",Toast.LENGTH_SHORT)
                                        .show();
                                closeProgressDialog();
                            }
                        });

                    }

                }).start();
                break;
            case R.id.delete:
                 Message message = new Message();
                 message.what = DELETE_;
                 handler.sendMessage(message);
                 manager.beginTransaction()
                         .setReorderingAllowed(true)
                         .remove(fragmentAdapter.getCurrentFragment());
                 break;
            default:
                break;

        }
    }

    private void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("loading.....");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }


    private void initList(){
        fragmentAdapter = new fragmentAdapter(manager,thisList);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.query(DATATAB,null,null,null,null,null
                        ,null);
                if(cursor.moveToNext()){
                    do{
                        String adcode = cursor.getString(cursor.getColumnIndexOrThrow("adcode"));
                        BaiDuWeather weather = IntnetTool.requestWeather(adcode);
                        //initWeathers.add(weather);
                        WeatherFragment fragment = new WeatherFragment();
                        fragment.setMyWeather(weather);
                        thisList.add(fragment);
                        //fragmentAdapter.notifyDataSetChanged();
                    } while (cursor.moveToNext());
                }else{
                    thisList.add(new WeatherFragment());
                    //fragmentAdapter.notifyDataSetChanged();
                }

                Message message = new Message();
                message.what = UPDATE_WEATHER;
                handler.sendMessage(message);

            }
        });
        //thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

    }


    //增加数据
    public void addItemToDataBase(BaiDuWeather weather){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("province",weather.result.location.province);
        values.put("city",weather.result.location.city);
        values.put("name",weather.result.location.name);
        values.put("adcode",weather.result.location.id);
        db.insert(DATATAB,null,values);
    }

    //查询数据
    public boolean isDataExist(BaiDuWeather weather){
        String adcode = weather.result.location.id;
        String name = weather.result.location.name;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DATATAB,null,null,null,null
        ,null,null);
        if(cursor.moveToNext()){
            do{
                if(adcode.equals(cursor.getString(cursor.getColumnIndexOrThrow("adcode")))
                        &&
                   name.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")))
                ){
                    return true;
                }

            }while (cursor.moveToNext());
        }
        return false;
    }



    //删除数据 -- 就一张表
    public void deleteData(BaiDuWeather weather){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(isDataExist(weather)){
            db.delete(DATATAB,"name = ?",new String[]{weather.result.location.name});
        }
    }


    //删除所有数据 -- 测试用
    public void deleteAllData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DATATAB,null,null);
    }



}

