package com.example.composedemo.domain.entity

data class MovieEntity(val movies: List<Movie>,
                    val size: Int) {



}
data class Movie(
    val id: Int,
    val name: String,
    var saleNumber: Int) {

}

