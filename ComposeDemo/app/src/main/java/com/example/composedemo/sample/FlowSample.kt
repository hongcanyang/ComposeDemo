package com.example.composedemo.sample

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

// 创建一个协程中的Flow环境
fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        // println("Calling a emit")
        emit(i) // emit next value
        delay(500) // pretend we are asynchronously waiting 100 ms
    }
}

fun main() = runBlocking<Unit> {
    testSimpleC()
}


fun testSimpleA() = runBlocking<Unit> {
    println("Calling simple  afunction...")
    val flow = simple()
    println("Calling a collect...")

    // 收集信息
    flow
        .collect { value ->
            println("a : $value")
        }

}

fun testSimpleB() = runBlocking<Unit> {
    val flow = simple()

    flow.onStart { println("onStart") }
        .onCompletion { println("onCompletion") }
        .collect { value -> println(value) }
}


fun testSimpleC() = runBlocking<Unit> {
    val flow = simple()

    flow
        .filter { i ->
            println("filter on : " + Thread.currentThread().name)
            i > 1
        }
        .map { value: Int ->
            println("map on : " + Thread.currentThread().name)
            value * 3
        }
        .flowOn(Dispatchers.IO)
        .collect { value -> println(value) }
}

fun testSimpleD() = runBlocking<Unit> {
    println("Calling simple  afunction...")
    val flow = simple()
    println("Calling a collect...")

    flow.collect { value ->
        println("a : $value")
    }

    println("Calling a collect again ...")
    // 收集信息
    flow.collect { value ->
        delay(1000)
        println("b : $value")
    }

}

fun testSimpleE() = runBlocking<Unit> {
    val flow = simple()

    flow.filter { i -> i > 1 }
        .map { value: Int -> value * 3 }
        .flowOn(Dispatchers.IO)
        .collect { value -> println(value) }
}

