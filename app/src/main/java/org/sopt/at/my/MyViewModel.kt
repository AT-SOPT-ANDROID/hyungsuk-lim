package org.sopt.at.my

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel() : ViewModel() {
    private val _userId = mutableStateOf(0)
    val userId: MutableState<Int> get() = _userId

    fun setUserId(id: Int) {
        _userId.value = id
    }
}