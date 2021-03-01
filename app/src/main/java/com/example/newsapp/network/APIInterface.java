package com.example.newsapp.network;

import com.example.newsapp.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("top-headlines")
    Call<ResponseModel> getNews(@Query("country") String country,@Query("category") String category,@Query("apiKey") String apiKey);
}
