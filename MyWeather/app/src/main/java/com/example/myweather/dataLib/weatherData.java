package com.example.myweather.dataLib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class weatherData extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "create table Weather ("
            + "id integer primary key autoincrement, "
            + "province text, "
            + "city text, "
            + "name text, "
            + "adcode text)";

    private Context mContext;

    public weatherData(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
        Toast.makeText(mContext,"创建完成" ,Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }



}
