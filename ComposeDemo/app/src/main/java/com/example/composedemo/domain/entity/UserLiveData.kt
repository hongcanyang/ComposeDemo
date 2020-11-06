package com.example.composedemo.domain.entity

import androidx.lifecycle.MutableLiveData

public class UserLiveData : MutableLiveData<User>() {
    fun notifyAge() {
        value?.let {
            it.age++
        }
    }

}