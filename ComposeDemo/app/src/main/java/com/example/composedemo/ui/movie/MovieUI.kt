package com.example.composedemo.ui.movie

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovieUI(movieViewModel: MovieViewModel) {
    val movieList = movieViewModel.movieList.observeAsState()
    val isMovieProgress = movieViewModel.showProgress.observeAsState(initial = false)

    if (isMovieProgress.value) {
        CircularProgressIndicator(
            color = Color.Red,
            modifier = Modifier.width(45.dp).height(45.dp)
        )
    } else {
        movieList.value?.let {
            Column() {
                for (item in it) {
                    Text(text = "movie info : ${item.name} sale : ${item.saleNumber}", Modifier.padding(Dp(5f)))
                }
            }
        }
    }
}

