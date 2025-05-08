package org.sopt.at

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {
    private val _isLogin = mutableStateOf(false)
    val isLogin: State<Boolean> get() = _isLogin

    fun login() {
        _isLogin.value = true
//        Log.d("UserSession", "로그인 상태: $_isLogin")
    }

    fun logout() {
        _isLogin.value = false
    }
}