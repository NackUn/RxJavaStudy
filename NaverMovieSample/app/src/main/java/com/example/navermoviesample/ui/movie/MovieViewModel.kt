package com.example.navermoviesample.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navermoviesample.base.BaseViewModel
import com.example.navermoviesample.network.NaverApi
import com.example.navermoviesample.vo.MovieItem
import com.example.navermoviesample.vo.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel() : BaseViewModel() {
    private val naverApi: NaverApi

    private val _movieItems = MutableLiveData<List<MovieItem>>()
    val movieItems: LiveData<List<MovieItem>> get() = _movieItems

    var searchWord = MutableLiveData<String>()

    private lateinit var mResultRequestMovies: Call<SearchResult>

    private val mCallBackRequestMovies = object : Callback<SearchResult>{
        override fun onFailure(call: Call<SearchResult>, t: Throwable) {
            Log.e("aa12", t.message);
        }

        override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
            Log.e("aa12", response.body().toString())
            val searchResult = response.body()
            if (searchResult != null) {
                _movieItems.value = searchResult.movieItems
            }
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        naverApi = retrofit.create(NaverApi::class.java)

        _movieItems.value = mutableListOf()
    }

    fun showMovies() {
        mResultRequestMovies = naverApi.requestMovies(searchWord.value!!)
        mResultRequestMovies.enqueue(mCallBackRequestMovies)
    }
}