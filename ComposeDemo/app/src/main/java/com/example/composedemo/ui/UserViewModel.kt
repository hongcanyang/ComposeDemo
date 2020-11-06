package com.example.composedemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composedemo.domain.entity.User

class UserViewModel  constructor() : ViewModel() {

    private var _userLiveData : MutableLiveData<User> = MutableLiveData<User>()
    val userLiveData: LiveData<User> get() = _userLiveData

    private var _Male : MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val male : LiveData<Boolean> get() = _Male

    private var _age: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val age : LiveData<Int> get() = _age

    init {
        _userLiveData.postValue(User("hello", 1))
    }

    fun changeAge() {
        age.value?.let {
            _age.postValue(it + 1)
        }
    }


}