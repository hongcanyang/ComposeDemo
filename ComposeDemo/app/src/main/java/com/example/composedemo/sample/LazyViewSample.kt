package com.example.composedemo.sample

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.onDispose
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


@Composable
fun LazyViewSample() {

    LazyColumnFor(items = listOf(
        "A", "B", "C", "D", "e", "F", "G"
    ) + ((0..100).map { it.toString() }),
        modifier = Modifier,
        itemContent = { item ->
            Log.d("LazyColumnFor", "This get rendered $item")
            when (item) {
                "A" -> {
                    RenderAView()
                }
                "B" -> {
                    Button(onClick = {}) {
                        Text(text = item, style = TextStyle(fontSize = 80.sp))
                    }
                }
                "C" -> {
                    //Do Nothing
                }
                "D" -> {
                    Text(text = item)
                }
                else -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
            }
        })
}

@Composable
fun RenderAView() {

    Text(text = "A")

    onActive{
        Log.d("LazyColumnFor", "onActive")
    }

    onDispose{
        Log.d("LazyColumnFor", "onDispose")
    }

    onCommit{
        Log.d("LazyColumnFor", "onCommit")
    }
}
