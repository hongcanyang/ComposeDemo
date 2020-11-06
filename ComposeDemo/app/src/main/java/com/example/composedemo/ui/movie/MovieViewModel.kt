package com.example.composedemo.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.composedemo.domain.entity.Movie
import com.example.composedemo.domain.entity.MovieEntity
import com.example.composedemo.domain.impl.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    private var _movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieList: LiveData<List<Movie>> get() = _movieList

    private val _showProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val showProgress: LiveData<Boolean> get() = _showProgress

    private var _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> get() = _showError

    fun initMoviesData() = viewModelScope.launch {
        movieRepository.getMovies()
            .onStart { _showProgress.postValue(true) }
            .catch { _showError.postValue(true) }
            .onCompletion { _showProgress.postValue(false) }
            .collect { it ->
                _movieList.postValue(it.movies)
                for (item in it.movies) {
                    Log.i("MovieViewModel","name : ${item.name} sale number : ${item.saleNumber}")
                }
            }
    }
}

//movieRepository.getMovies()
//.onStart { _showProgress.postValue(true) }
//.transform<MovieEntity, List<Movie>> {
//    value: MovieEntity ->
//    val list = value.movies
//    for (item in list) {
//        item.saleNumber *= 3
//    }
//    emit(list)
//}
//.catch { _showError.postValue(true) }
//.onCompletion { _showProgress.postValue(false) }
//.collect { it ->
//      _movieList.postValue(it)
//    for (item in it) {
//
//        Log.i("MovieViewModel","name : ${item.name} sale number : ${item.saleNumber}")
//    }
//}

