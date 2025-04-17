package org.sopt.at.signup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val _id = mutableStateOf("")
    val id: MutableState<String> get() = _id

    private val _pw = mutableStateOf("")
    val pw: MutableState<String> get() = _pw

    private val _step = mutableStateOf(1)
    val step: MutableState<Int> get() = _step

    private val _isError = mutableStateOf(false)
    val isError: MutableState<Boolean> get() = _isError

    val isValidId = { id: String ->
        id.matches(Regex("^[a-z0-9]{6,12}$")) &&
                id.contains(Regex("[a-z]"))
    }

    val isValidPw = { pw: String ->
        pw.length in 8..15 &&
                pw.contains(Regex("[A-Za-z]")) &&
                pw.contains(Regex("[0-9]")) &&
                pw.contains(Regex("[^A-Za-z0-9]"))
    }

    fun updateId(id: String) {
        _id.value = id
    }

    fun updatePw(pw: String) {
        _pw.value = pw
    }

    fun onNext() {
        if (_step.value == 1) _step.value = 2
    }

    fun onPrevious() {
        if (_step.value == 2) _step.value = 1
    }

    fun setIsError(isError: Boolean) {
        _isError.value = isError
    }
}