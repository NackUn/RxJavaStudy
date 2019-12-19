package com.example.navermoviesample.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navermoviesample.base.BaseViewModel
import com.example.navermoviesample.network.NaverApi
import com.example.navermoviesample.vo.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel() : BaseViewModel() {
    private val naverApi: NaverApi

    private val _movieItems = MutableLiveData<List<MovieItem>>()
    val movieItems: LiveData<List<MovieItem>> get() = _movieItems

    var searchWord = MutableLiveData<String>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        naverApi = retrofit.create(NaverApi::class.java)

        _movieItems.value = mutableListOf()
    }

    fun showMovies() {
        naverApi.run {
            this.requestMovies(searchWord.value!!)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { searchResult -> _movieItems.value = searchResult.movieItems },
                    { thowable -> thowable.message }
                )
        }
    }
}