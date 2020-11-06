package com.example.composedemo.service

import com.example.composedemo.domain.entity.Movie
import com.example.composedemo.domain.entity.MovieEntity

class MovieService {

    private lateinit var movieEntity: MovieEntity

    init {
        var list = ArrayList<Movie>()
        list.add(Movie(1, "复仇者联盟", 10000))
        list.add(Movie(1, "我爱我的祖国", 10000))
        movieEntity = MovieEntity(list, list.size)
    }

    fun getMovies() : MovieEntity {
        return movieEntity
    }
}