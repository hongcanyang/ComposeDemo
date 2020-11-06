package com.example.composedemo.sample

import android.util.Log
import androidx.compose.*
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

/**
 * Compose 生命周期
 */
@Composable
fun LifecycleDemo() {

    // 状态记忆支持
    val count = remember { mutableStateOf(0) }

    Column {
        Button( onClick = {
            count.value++
        }){
            Text("Click me")
        }

        if (count.value < 3) {
            // 当Compose第一次初始化的时候会执行
            onActive { Log.d("LifecycleDemo", "onactive with value: " + count.value) }

            // 当Compose 部分元素消失的时候会执行
            onDispose { Log.d("LifecycleDemo", "onDispose because value=" + count.value) }

            // 当Compose 执行的时候会调用一次
            onCommit{Log.d("LifecycleDemo", "onCommit because value=" + count.value) }

            
            Text(text = "You have clicked the button: " + count.value.toString())
        }
    }

}