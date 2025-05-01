package org.sopt.at.signin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    var idState by mutableStateOf("")
        private set
    var pwState by mutableStateOf("")
        private set

    var signInResult by mutableStateOf<SignInResult?>(null)
        private set

    private val _id = mutableStateOf("")
    val id: MutableState<String> get() = _id

    private val _pw = mutableStateOf("")
    val pw: MutableState<String> get() = _pw

    private val _visibility = mutableStateOf(false)
    val visibility: MutableState<Boolean> get() = _visibility

    fun setUserInfo(id: String, pw: String) {
        idState = id
        pwState = pw
    }

    fun updateId(id: String) {
        _id.value = id
    }

    fun updatePw(pw: String) {
        _pw.value = pw
    }

    fun signIn() {
        signInResult = when {
            _id.value != idState -> SignInResult.InvalidId
            _pw.value != pwState -> SignInResult.InvalidPw
            else -> SignInResult.Success(_id.value)
        }
    }

    fun resetSignInResult() {
        signInResult = null
    }

    fun switchVisibility() {
        _visibility.value = !_visibility.value
    }
}

sealed class SignInResult {
    object InvalidId : SignInResult()
    object InvalidPw : SignInResult()
    data class Success(val userId: String) : SignInResult()
}