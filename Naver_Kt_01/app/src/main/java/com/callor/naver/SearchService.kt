package com.callor.naver

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchService{
//    private val CONNECT_TIMEOUT:Long = 15
//
//    //log를 볼 수 있도록 httpLogginInterceptor를 만듦
//    private val okHttpClient : OkHttpClient
//        get()
//        {
//            val httpLoggingInterceptor = HttpLoggingInterceptor()
//            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            return OkHttpClient().newBuilder()
//                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(httpLoggingInterceptor)
//                .build()
//        }
//
//    //retrofit에 각종 값을 setting
//    val movieRetrofit:MovieService
//        get() {
//            return Retrofit.Builder().baseUrl(API_URI.MOVIE_SEARCH.uri)//movieUri
//                .client(okHttpClient)//httpClient연결을 통해 log 확인
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava2를 사용하도록 Factory생성
//                .addConverterFactory(GsonConverterFactory.create())//Gson을 쓸 수 있도록 Factory생성
//                .build().create(MovieService::class.java)//MovieService Interface사용
//        }
}
