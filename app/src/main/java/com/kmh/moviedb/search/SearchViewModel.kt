package com.kmh.moviedb.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kmh.moviedb.api.SearchApiClient
import com.kmh.moviedb.model.SearchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    private var movieLists:MutableLiveData<SearchModel> = MutableLiveData()

    fun getSearchWord(keyword: String):MutableLiveData<SearchModel> = movieLists

    fun getSearchArticle(): MutableLiveData<SearchModel> = movieLists

    fun loadSearchData(keyword: String){
        var apiClient= SearchApiClient()

        var apiCall=apiClient.getSearchMovie(keyword)

        apiCall.enqueue(object : Callback<SearchModel> {
            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                Log.d("Error>>>",t.toString())
            }

            override fun onResponse(call: Call<SearchModel>, response: Response<SearchModel>) {
                movieLists.value=response.body()
            }

        })
    }
}