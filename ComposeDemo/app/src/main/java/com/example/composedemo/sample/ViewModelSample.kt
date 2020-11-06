package com.example.composedemo.sample

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ContextAmbient
import com.example.composedemo.ui.UserViewModel
import com.example.composedemo.ui.movie.MovieActivity

@Composable
fun ViewModelSample(viewModel: UserViewModel) {

    val context = ContextAmbient.current
    val age = viewModel.age.observeAsState()
    val onChick : () -> Unit = {
        viewModel.changeAge()
    }
    rendUserInfo(age, onChick, context)
}

@Composable
fun rendUserInfo(age: State<Int?>, onChick: () -> Unit, context: Context) {
    Column() {
        Text(text = "age : " + age.value)

        onActive {
            Log.i("ViewModelSample", "onActive")
        }

        onCommit {
            Log.i("ViewModelSample", "onCommit")
        }

        onDispose{
            Log.i("ViewModelSample", "onDispose")
        }
        Button(onClick = {
            onChick()
        }) {
            Text(text = "click me")
        }

        Button(onClick = {
            context.startActivity(Intent(context, MovieActivity::class.java))
        }) {
            Text(text = "show movie scene")
        }
    }
}

