package com.callor.naver.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class Movie(
    @SerializedName("lastBuildDate")
    @Expose
    var lastBuildDate: String,

    @SerializedName("total")
    @Expose
    var total: Int,

    @SerializedName("start")
    @Expose
    var start: Int,

    @SerializedName("display")
    @Expose
    var display: Int,

    @SerializedName("items")
    var items: Array<Items>
) {

    data class Items(
            @SerializedName("title")
            val title: String,

            @SerializedName("link")
            val link: String,

            @SerializedName("image")
            val image: String,

            @SerializedName("subtitle")
            val subtitle: String,

            @SerializedName("pubdate")
            val pubDate: String,

            @SerializedName("director")
            val director: String,

            @SerializedName("actor")
            val actor: String,

            @SerializedName("userRating")
            val userRating: Double
    )
}
