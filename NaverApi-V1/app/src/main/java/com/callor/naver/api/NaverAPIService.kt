package com.callor.naver.api

import android.util.Log
import org.springframework.http.*
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.io.*
import java.net.*
import java.util.*

class NaverAPIService : Thread() {
    private val NAVER_CLIENT_ID = "0FzoIWbjrItxaqMrl4qY"
    private val NAVER_CLIENT_SECURITY = "aONjrwYTdN"
    private val NAVER_MOVIE_URL = "https://openapi.naver.com/v1/search/movie.json?query=%s"

    override fun run() {
        super.run()
        val searchText = "공공의적"
        this.getNaver(searchText)
    }

    fun getNaver(searchText: String?) {

        var text: String? = null
        text = try {
            URLEncoder.encode("공공의 적", "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("검색어 인코딩 실패", e)
        }

        val queryURL = String.format(NAVER_MOVIE_URL, text)
        val apiURL = URI(queryURL);

        val requestHeaders: HttpHeaders = HttpHeaders()
//        requestHeaders.set("X-Naver-Client-Id", NAVER_CLIENT_ID)
//        requestHeaders.set("X-Naver-Client-Secret", NAVER_CLIENT_SECURITY)
        requestHeaders.accept = Collections.singletonList(MediaType.APPLICATION_JSON )

        val requestEntity: HttpEntity<String> = HttpEntity<String>("parameter",requestHeaders)
        val responseEntity: ResponseEntity<String>? = null

        // Create a new RestTemplate instance
        val restTemplate = RestTemplate()

        // Add the String message converter
         restTemplate.messageConverters.add(StringHttpMessageConverter())

        // Make the HTTP GET request, marshaling the response to a String
        val result = restTemplate.exchange(apiURL, HttpMethod.GET,requestEntity,String::class.java)
        Log.d("NAVER",result.body.toString())
    }

}