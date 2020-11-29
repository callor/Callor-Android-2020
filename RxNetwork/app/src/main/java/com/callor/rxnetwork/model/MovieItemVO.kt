package com.callor.rxnetwork.model

class MovieItemVO {

    var vote_count: Int = 2141
    var id: Int = 335983
    var video: Boolean = false
    var vote_average: Float = 6.6F
    var title: String = "베놈"
    var popularity: Float = 228.355F
    var poster_path: String = "lc8TJW5z261JqSz3oSy5GES2ZXj.jpg"
    var original_language: String = "en"
    var original_title: String = "Venom"
    var genre_ids: IntArray = intArrayOf(878)
    var backdrop_path: String = "VuukZLgaCrho2Ar8Scl9HtV3yD.jpg"
    var adult: Boolean = false
    var overview: String = "진실을 위해서라면 몸을 사리지 않는 정의로운 열혈 기자 에디 브록...  "
    var release_date: String = "2018-10-03"


    constructor(vote_count: Int, id: Int, video: Boolean, vote_average: Float, title: String, popularity: Float, poster_path: String, original_language: String, original_title: String, genre_ids: IntArray, backdrop_path: String, adult: Boolean, overview: String, release_date: String) {
        this.vote_count = vote_count
        this.id = id
        this.video = video
        this.vote_average = vote_average
        this.title = title
        this.popularity = popularity
        this.poster_path = poster_path
        this.original_language = original_language
        this.original_title = original_title
        this.genre_ids = genre_ids
        this.backdrop_path = backdrop_path
        this.adult = adult
        this.overview = overview
        this.release_date = release_date
    }

    constructor(
            id: Int,
            vote_average: Float,
            title: String,
            release_date: String,
            poster_path: String,
            overview: String
    ) {
        this.id = id
        this.vote_average = vote_average
        this.title = title
        this.release_date = release_date
        this.poster_path = poster_path
        this.overview = overview
    }
    override fun toString(): String {
        return "MovieItemVO(vote_count=$vote_count, id=$id, video=$video, vote_average=$vote_average, title='$title', popularity=$popularity, poster_path='$poster_path', original_language='$original_language', original_title='$original_title', genre_ids=$genre_ids, backdrop_path='$backdrop_path', adult=$adult, overview='$overview', release_date='$release_date')"
    }


}