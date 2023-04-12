package com.example.remote_help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.remote_help.recycle.Family;
import com.example.remote_help.recycle.FamilyAdapter;
import com.example.remote_help.sqlite.Operate;

import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private Operate operate = Operate.getInstance();

    //这是recycleview的链表,对recycleview操作在这上面进行
    List<Family> familyListlist = operate.querySQL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initData();
        initRecycle();
    }

    //初始化recycleview
    private void initRecycle(){
        RecyclerView recyclerView = findViewById(R.id.deleteRecycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FamilyAdapter adapter = new FamilyAdapter(familyListlist);
        adapter.setDeleteActivity(true,this);
        recyclerView.setAdapter(adapter);
    }

    //初始化recycleview的数据
    private void initData(){
        familyListlist.clear();
        familyListlist = operate.querySQL();
    }

}