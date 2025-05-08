package org.sopt.at.my

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel() : ViewModel() {
    private val _userId = mutableStateOf("")
    val userId: MutableState<String> get() = _userId

    fun setUserId(id: String) {
        _userId.value = id
    }
}