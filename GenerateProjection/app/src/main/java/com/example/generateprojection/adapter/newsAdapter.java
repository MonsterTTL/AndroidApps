package com.example.generateprojection.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.generateprojection.NewsActivity;
import com.example.generateprojection.R;
import com.example.generateprojection.TopApplication;
import com.example.generateprojection.helper.ZHiHuDetail_DB;
import com.example.generateprojection.helper.ZhiHuDB;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder> {
    private static final String TAG = "newsAdapter";
    private List<ZhiHuDB.StoriesDTO> arrayList ;
    private List<ZhiHuDB.TopStoriesDTO> topArrayList;
    private ZHiHuDetail_DB[] detail_dbs ;
    private Activity fa;
    public newsAdapter(List<ZhiHuDB.StoriesDTO> data, List<ZhiHuDB.TopStoriesDTO> data2, Activity fa){
        arrayList = data;
        topArrayList = data2;
        this.fa = fa;
    }

    public void setDetail_dbs(ZHiHuDetail_DB[] dbs){
        this.detail_dbs = dbs;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_itemlayout,parent
                ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position <= 5){
            Glide.with(holder.news_img).load(arrayList.get(position).getImages().get(0))
                    .into(holder.news_img);
            holder.news_text.setText(arrayList.get(position).getTitle());
            String uri = arrayList.get(position).getUrl();
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fa, NewsActivity.class);
                    intent.putExtra("webview_uri",uri);
                    fa.startActivity(intent);
                }
            });
            Log.d(TAG, "onBindViewHolder: "+position);
        }else{
            Glide.with(holder.news_img).load(topArrayList.get(position-6).getImage()).into(holder.news_img);
            holder.news_text.setText(topArrayList.get(position-6).getTitle());
            String uri = topArrayList.get(position-6).getUrl();
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fa, NewsActivity.class);
                    intent.putExtra("webview_uri",uri);
                    fa.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size()+topArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView news_img;
        TextView news_text;
        TextView likes_number;
        TextView comments_number;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_img = itemView.findViewById(R.id.news_img);
            news_text = itemView.findViewById(R.id.news_text);
            likes_number = itemView.findViewById(R.id.likes_number);
            comments_number = itemView.findViewById(R.id.comments_number);
            cardView = itemView.findViewById(R.id.news_cardview);
        }
    }

    public interface news_Callback{
        public void callback();
    }

}
