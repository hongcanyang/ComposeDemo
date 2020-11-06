package com.example.composedemo.domain

import com.example.composedemo.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    suspend fun getMovies() : Flow<MovieEntity>
}