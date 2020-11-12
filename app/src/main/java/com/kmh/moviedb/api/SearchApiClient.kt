package com.kmh.moviedb.api


import com.kmh.moviedb.model.SearchModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchApiClient {
    private val BASE_URL= "https://api.themoviedb.org/3/search/"

    private val apiInterface:MovieApiInterface
    init {
        val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiInterface=retrofit.create(MovieApiInterface::class.java)
    }

    fun getSearchMovie(query:String): Call<SearchModel> {
        return apiInterface.getSearchMovie("ed492850934cf52e0d85083bdf1c6396",query)

    }



}