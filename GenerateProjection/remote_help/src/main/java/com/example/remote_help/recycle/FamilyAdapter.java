package com.example.remote_help.recycle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remote_help.MiddleActivity;
import com.example.remote_help.R;
import com.example.remote_help.sharescreen.Helped_person;
import com.example.remote_help.Person;
import com.example.remote_help.sqlite.Operate;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.ViewHolder> {

    private List<Family> list;

    private boolean deleteActivity = false;

    private Operate operate = Operate.getInstance();

    private Context object;

    private Person person = Person.getInstance();

    /*
    这里是给adapter传入两个值,一个是判断启动的adapter是那个活动启动的
    true为delete活动启动的,false为主页活动启动的,第二个是传入了两个活动的Context值,后面启动会用到的
     */
    public void setDeleteActivity(boolean deleteActivity, Context object) {
        this.deleteActivity = deleteActivity;
        this.object = object;
    }

    public FamilyAdapter(List<Family> list){
        this.list = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        View familyView;
        TextView name;
        TextView phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            familyView = itemView;
            name = itemView.findViewById(R.id.name);
            phone_number = itemView.findViewById(R.id.phone_number);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.family_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.familyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前点击按钮的类
                int position = holder.getAdapterPosition();
                Family family = list.get(position);
                if(deleteActivity){
                    Log.d("delete", "onClick: ");
                    //要在这里发出警告,警告的内容,然后是否同意
                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("这是一个警告");
                    dialog.setMessage("你确定要删除" + family.getName());
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            operate.deleteSQL(family.getIdentifier());
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }else{
                    Log.d("打电话123456", "onClick: 直接进入打电话的界面,然后开房间,进行屏幕通话");

                    Intent intent = new Intent(object, MiddleActivity.class);
                    intent.putExtra("data",family.getPhone_number());
                    object.startActivity(intent);

                }
            }
        });
        return holder;
    }

    //传入电话号码,直接开始打电话
    public void callPhone(String phoneNum){

        Intent intent = new Intent(Intent.ACTION_CALL);

        Uri data = Uri.parse("tel:" + phoneNum);

        intent.setData(data);

        object.startActivity(intent);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Family family = list.get(position);
        holder.name.setText(family.getName());
        holder.phone_number.setText((family.getPhone_number()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
