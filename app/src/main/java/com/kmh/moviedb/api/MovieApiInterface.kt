package com.kmh.moviedb.api

import com.kmh.moviedb.model.NowPlayingModel
import com.kmh.moviedb.model.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("now_playing")
    fun getNowPlaying(
            @Query("api_key")api_key:String
    ):Call<NowPlayingModel>


    @GET("upcoming")
    fun getUpcoming(
        @Query("api_key")api_key:String
    ):Call<NowPlayingModel>

    @GET("popular")
    fun getPopular(
        @Query("api_key")api_key:String
    ):Call<NowPlayingModel>

    @GET("top_rated")
    fun getTopRated(
        @Query("api_key")api_key:String
    ):Call<NowPlayingModel>

    @GET("movie")
    fun getSearchMovie(
            @Query("api_key")api_key:String,
            @Query("query")query:String
    ):Call<SearchModel>


}