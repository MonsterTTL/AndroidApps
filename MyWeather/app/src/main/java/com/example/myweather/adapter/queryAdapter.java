package com.example.myweather.adapter;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myweather.MyApplication;
import com.example.myweather.R;
import com.example.myweather.gson.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Handler;
import java.util.zip.Inflater;

public class queryAdapter extends RecyclerView.Adapter<queryAdapter.ViewHolder> {

    public List<queryLocation.Result> mList;

    public interface myInterface{
        public void onItemClick(View view,int position);
    }

    private myInterface handle;

    public void setMyInterface(myInterface handle){
        this.handle = handle;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView queryLocation;
        TextView queryLocationDetail;
        View listenerView;

        public ViewHolder(View view){
            super(view);
            listenerView = view;
            queryLocation = view.findViewById(R.id.queryLocation);
            queryLocationDetail = view.findViewById(R.id.queryLocation_Detail);
        }

    }

    public queryAdapter(List<queryLocation.Result> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.getContext())
                .inflate(R.layout.single_queryitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        queryLocation.Result result = mList.get(position);
        holder.queryLocation.setText(result.name);
        holder.queryLocationDetail.setText(result.province +
                result.city+result.district);

        holder.listenerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handle != null){
                    handle.onItemClick(v, holder.getAdapterPosition());


                }

            }
        });
    }

    public int getItemCount(){
        return mList.size();
    }

}
