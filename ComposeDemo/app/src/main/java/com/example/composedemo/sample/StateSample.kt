package com.example.composedemo.sample

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

/**
 * mutable state 的改变会触发composable执行重组的操作
 * remember 会持有最新改变后的状态
 */
@Composable
fun StateSample() {

//    val countState =   mutableStateOf(0)
    val countState =   remember {  mutableStateOf(0)}
    Column {
        Button(backgroundColor = MaterialTheme.colors.secondary, onClick = { countState.value++ }) {
            Text("count up")
        }
        onActive{Log.i("stateSample", "onActive")}
        onCommit {Log.i("stateSample", "onCommit")}
        onDispose{Log.i("stateSample", "onDispose")}
        Text("You have clicked the Button " + countState.value.toString() + " times")
    }
}