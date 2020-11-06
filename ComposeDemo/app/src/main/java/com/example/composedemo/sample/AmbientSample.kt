package com.example.composedemo.sample

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenStarted
import com.example.composedemo.domain.entity.User
import kotlinx.coroutines.runBlocking

/**
 * 如何把数据从父亲的节点传递到子节点中
 */

val ActiveUser = ambientOf<User> { error("No user found!") }

@Composable
fun UserScreen() {

    // ContextAmbient.current get Context

    // ConfigurationAmbient.current get organize
    var owner = LifecycleOwnerAmbient.current
    runBlocking {
        owner.lifecycle.whenCreated {
            Log.i("Ambient", "oncreate")
        }
        owner.lifecycle.whenStarted {
            Log.i("Ambient", "onStart")
        }
    }

    val user = User("liming", 15)
    Providers(ActiveUser provides user ) {
        RenderUserInfo()
    }


}


@Composable
fun RenderUserInfo() {
    Column {
        Text(text = "name : " + ActiveUser.current.name)

        Text(text = "age : " + ActiveUser.current.age)
    }
}
