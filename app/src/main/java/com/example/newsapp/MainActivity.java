package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.newsapp.adapter.ArticleAdapter;
import com.example.newsapp.model.Article;
import com.example.newsapp.model.ResponseModel;
import com.example.newsapp.network.APIInterface;
import com.example.newsapp.network.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener {
    private static final String API_KEY = "b3273e3982af454cb2d176fde898cff3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView=findViewById(R.id.recyclerview);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call=apiService.getNews("us","business",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body().getStatus().equals("ok")){
                    List<Article> articleList=response.body().getArticles();
                    if (articleList.size()>0){
                        final ArticleAdapter articleAdapter = new ArticleAdapter(articleList);
                        articleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                        recyclerView.setAdapter(articleAdapter);

                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("out", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(int adapterPosition, View view) {
        switch (view.getId()){
            case R.id.linearlayout:
                Article article=(Article)view.getTag();
                if(!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent webActivity = new Intent(this,WebActivity.class);
                    webActivity.putExtra("url",article.getUrl());
                    startActivity(webActivity);
                }
                break;
        }
    }
}