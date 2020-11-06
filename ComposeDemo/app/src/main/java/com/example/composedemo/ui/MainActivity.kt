package com.example.composedemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.example.composedemo.domain.entity.GuideData
import com.example.composedemo.sample.*
import com.example.composedemo.ui.resource.ComposeDemoTheme

class MainActivity : AppCompatActivity() {

    val viewModel =  UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                  //  Greeting("Hello Android")

                    // LifecycleDemo()
                    LazyViewSample()
                  //  showColumnPreview()
                    // UserScreen()
                   // ViewModelSample(viewModel = viewModel)
                   //StateSample()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
     Text(text = "Hello $name!")

}

@Composable
fun textPreview() {
    val data = GuideData(2, "name")
    primaryButton(data)
}