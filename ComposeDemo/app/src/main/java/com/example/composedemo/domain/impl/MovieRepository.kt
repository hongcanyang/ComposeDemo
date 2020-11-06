package com.example.composedemo.domain.impl

import com.example.composedemo.domain.IMovieRepository
import com.example.composedemo.domain.entity.MovieEntity
import com.example.composedemo.service.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository : IMovieRepository {

    private val movieService: MovieService = MovieService()

    override suspend fun getMovies(): Flow<MovieEntity> = flow {
        kotlinx.coroutines.delay(3000L)
        emit(movieService.getMovies())
    }.flowOn(Dispatchers.IO)

}