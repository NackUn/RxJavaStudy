package com.example.navermoviesample.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navermoviesample.base.BaseViewModel
import com.example.navermoviesample.data.MovieRepository
import com.example.navermoviesample.vo.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {
    private val _movieItems = MutableLiveData<List<MovieItem>>()
    val movieItems: LiveData<List<MovieItem>> get() = _movieItems
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    var searchWord = MutableLiveData<String>()

    init {
        _movieItems.value = mutableListOf()
    }

    fun showMovies() {
        searchWord.value?.let {
            addDisposable(
                movieRepository.requestMovies(it)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { _movieItems.value = it.movieItems },
                        { _errorMessage.value = it.message }
                    )
            )
        }
    }
}