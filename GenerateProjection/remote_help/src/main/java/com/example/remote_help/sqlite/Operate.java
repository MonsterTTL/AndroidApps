package com.example.remote_help.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.remote_help.recycle.Family;

import java.util.ArrayList;
import java.util.List;

public class Operate {

    //使用懒汉单例模式，即在类加载时创建好对象
    private static final Operate operate = new Operate();

    private Operate(){}

    public static Operate getInstance() {
        return operate;
    }

    private MyDatabaseHelper dbHelper;

    private Object object;

    public List<Family> getFamilyListlist() {
        return familyListlist;
    }

    public void setFamilyListlist(List<Family> familyListlist) {
        this.familyListlist = familyListlist;
    }

    private List<Family> familyListlist = new ArrayList<>();

    public void initSQL(Object object){
        Log.d("创建", "initSQL: ");
        dbHelper = new MyDatabaseHelper((Context) object,"Family.db", null, 1);
        dbHelper.getWritableDatabase();
    }

    public void addSQL(String name, String phone_number){
        Log.d("operate", "addSQL: 开始添加数据了");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始添加数据
        values.put("name", name);
        values.put("number", phone_number);
        db.insert("Family",null, values);
    }

    public List<Family> querySQL() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Family", null, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.
                        getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.
                        getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String number = cursor.
                        getString(cursor.getColumnIndex("number"));
                Log.d("读取的数据", "querySQL: " + id);
                Log.d("读取的数据", "querySQL: " + name);
                Log.d("读取的数据", "querySQL: " + number);

                familyListlist.add(new Family(id,name, number));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return familyListlist;
    }

    public void deleteSQL(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Family","id = ?",new String[]{String.valueOf(id)});
    }

}
