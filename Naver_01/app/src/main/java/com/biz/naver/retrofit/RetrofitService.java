package com.biz.naver.retrofit;

import com.biz.naver.domain.NaverMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("search/{type}")
    Call<NaverMovie> getMovie(
        @Header("X-Naver-Client-Id") String clientId,
        @Header("X-Naver-Client-Secret") String clientSec,
        @Path("type") String type,
        @Query("query") String query);

}
