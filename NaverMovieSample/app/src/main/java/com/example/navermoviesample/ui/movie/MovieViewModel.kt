package com.example.navermoviesample.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navermoviesample.base.BaseViewModel
import com.example.navermoviesample.data.Repository
import com.example.navermoviesample.vo.MovieItem

class MovieViewModel(
    private val repository: Repository
) : BaseViewModel() {
    private val _movieItems = MutableLiveData<List<MovieItem>>()
    val movieItems: LiveData<List<MovieItem>> get() = _movieItems

    var searchWord = MutableLiveData<String>()

    init {
        _movieItems.value = mutableListOf()
    }

    fun showMovies() {
        repository.requestMovies(
            searchWord.value!!,
            onSearchSuccess = {
                _movieItems.value = it
            },
            onError = {
                it.message
            }
        )
    }
}