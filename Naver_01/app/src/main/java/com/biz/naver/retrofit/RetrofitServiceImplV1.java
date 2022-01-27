package com.biz.naver.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceImplV1 {

    private static final String naver_movie_url = "https://openapi.naver.com/v1/";


    public static RetrofitService getApiService() {
        return getInstance().create(RetrofitService.class);

    }


    private static Retrofit getInstance() {

        Gson gson = new GsonBuilder()
                // .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        return new Retrofit.Builder().baseUrl(naver_movie_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }


}
