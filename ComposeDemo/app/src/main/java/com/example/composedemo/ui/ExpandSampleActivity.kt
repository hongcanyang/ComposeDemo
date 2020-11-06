package com.example.composedemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.ui.tooling.preview.Preview
import com.example.composedemo.R

class ExpandSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_sample).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {
               addChildView()
            }
        }
    }

    @Preview
    @Composable
    private fun addChildView() {
        Button(onClick = {}) {
            Text(text = "this is a expand button add for activity")
        }
    }
}