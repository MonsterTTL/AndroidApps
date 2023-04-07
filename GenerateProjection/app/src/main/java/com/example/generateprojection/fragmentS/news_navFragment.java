package com.example.generateprojection.fragmentS;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.generateprojection.R;
import com.example.generateprojection.adapter.newsAdapter;
import com.example.generateprojection.helper.ZhiHuDB;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class news_navFragment extends Fragment {

    RecyclerView recyclerView;
    ZhiHuDB huDB;
    private static final String TAG = "news_navFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_navfragment,container,false);
        recyclerView = view.findViewById(R.id.newsRecycle);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireData();
    }

    public void requireData(){
        Request.Builder builder = new Request.Builder().url("https://news-at.zhihu.com/api/4/news/latest");
        builder.method("GET",null);
        OkHttpClient client = new OkHttpClient();
        Call myCall = client.newCall(builder.build());
        myCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                return;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Log.d(TAG, "onResponse: "+data);
                huDB = new Gson().fromJson(data,ZhiHuDB.class);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                List<ZhiHuDB.StoriesDTO> arrayList = huDB.getStories();
                List<ZhiHuDB.TopStoriesDTO> arrayList2 = huDB.getTopStories();
                newsAdapter myAdapter = new newsAdapter(arrayList,arrayList2,requireActivity());
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();

                     /**  这一部分是用来加载点赞数和评论数的网络请求  **/
                 /**    Request.Builder builder1 = new Request.Builder();
                 builder1.method("GET",null);
                 builder1.url("https://news-at.zhihu.com/api/4/story-extra/");
                 OkHttpClient client1 = new OkHttpClient();       **/


            }
        });

    }


}
