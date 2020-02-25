package com.callor.naver

import com.callor.naver.domain.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MovieService {

    //GET 방식으로 url을 맵핑할 수 있음.
    @GET("search/{type}")
    //Header를 통해서 각종 값을 전송할 수 있음
    //Path를 통해서 구체적인 API URI로 이동할 수 있음
    //Query를 통해서 웹에 쿼리를
    fun getSearch(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSec: String,
        @Path("type") type: String,
        @Query("query") query: String
    ): Call<Movie>


}