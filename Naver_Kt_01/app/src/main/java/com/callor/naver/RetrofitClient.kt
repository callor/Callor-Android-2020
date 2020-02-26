package com.callor.naver

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
        private const val naverMovieUrl = "https://openapi.naver.com/v1/"
        fun getApiService(): MovieService {
            return getInstance().create(
                MovieService::class.java
            )
        }

        private fun getInstance(): Retrofit {
            val gson = GsonBuilder() // .setLenient()
                .create()

            // 예제
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()

            return Retrofit.Builder().baseUrl(naverMovieUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}
