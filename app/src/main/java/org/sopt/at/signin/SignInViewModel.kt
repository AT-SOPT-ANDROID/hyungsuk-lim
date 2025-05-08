package org.sopt.at.signin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute

class SignInViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val user = savedStateHandle.toRoute<SignIn>()

    var signInResult by mutableStateOf<SignInResult?>(null)
        private set

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

    fun signIn() {
        signInResult = when {
            _id.value != user.userId -> SignInResult.InvalidId
            _pw.value != user.userPw -> SignInResult.InvalidPw
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