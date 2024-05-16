package com.tobi.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobi.imdb.model.Movie
import com.tobi.imdb.model.MovieListDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val repository =  MovieListDao()
//    private val _moviesShared = MutableStateFlow<List<Movie>?>()
//    val movies: StateFlow<List<Movie>> get() = _moviesShared.asStateFlow()

    /**
     * fetch movies from retrofit using flows
     */
    fun fetchMovies() {
        viewModelScope.launch {
            repository.getMovies()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    // handle exception
                    Timber.e(e)
                }
                .collect {
                    // list of users from the network
//                    _moviesShared = it
                    Timber.i(it.toString())
                }

        }
    }

}