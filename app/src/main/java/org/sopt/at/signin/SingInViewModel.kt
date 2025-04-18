package org.sopt.at.signin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SingInViewModel : ViewModel() {
    private val _id = mutableStateOf("")
    val id: MutableState<String> get() = _id

    private val _pw = mutableStateOf("")
    val pw: MutableState<String> get() = _pw

    private val _visibility = mutableStateOf(false)
    val visibility: MutableState<Boolean> get() = _visibility

    fun updateId(id: String) {
        _id.value = id
    }

    fun updatePw(pw: String) {
        _pw.value = pw
    }

    fun switchVisibility() {
        _visibility.value = !_visibility.value
    }
}