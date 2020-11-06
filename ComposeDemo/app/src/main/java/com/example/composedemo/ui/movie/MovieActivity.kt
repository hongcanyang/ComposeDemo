package com.example.composedemo.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.example.composedemo.ui.resource.ComposeDemoTheme

class MovieActivity : AppCompatActivity() {

    private val movieViewModel = MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel.initMoviesData()
        setContent {
            ComposeDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieUI(movieViewModel)
                }
            }
        }
    }
}