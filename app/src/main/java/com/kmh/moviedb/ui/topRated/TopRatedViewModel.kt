package com.kmh.moviedb.ui.topRated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kmh.moviedb.api.ApiClient
import com.kmh.moviedb.model.NowPlayingModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel : ViewModel() {
    private var movieLists:MutableLiveData<NowPlayingModel> = MutableLiveData()

    fun getTopRatedArticle():MutableLiveData<NowPlayingModel> = movieLists

    fun loadTopRatedData(){
        var apiClient= ApiClient()

        var apiCall=apiClient.getTopRated()

        apiCall.enqueue(object : Callback<NowPlayingModel> {
            override fun onFailure(call: Call<NowPlayingModel>, t: Throwable) {
                Log.d("Error>>>",t.toString())
            }

            override fun onResponse(
                call: Call<NowPlayingModel>,
                response: Response<NowPlayingModel>
            ) {
                movieLists.value=response.body()
            }

        })
    }
}