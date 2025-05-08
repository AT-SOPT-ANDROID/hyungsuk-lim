package org.sopt.at.signup

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    private val _id = mutableStateOf("")
    val id: MutableState<String> get() = _id

    private val _pw = mutableStateOf("")
    val pw: MutableState<String> get() = _pw

    private val _step = mutableIntStateOf(1)
    val step: MutableIntState get() = _step

    private val _isError = mutableStateOf(false)
    val isError: MutableState<Boolean> get() = _isError

    private val _visibility = mutableStateOf(false)
    val visibility: MutableState<Boolean> get() = _visibility

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

    fun nextStep() {
        if (_step.intValue == 1) _step.intValue = 2
    }

    fun onPrevious() {
        if (_step.intValue == 2) _step.intValue = 1
    }

    fun setIsError(isError: Boolean) {
        _isError.value = isError
    }

    fun switchVisibility() {
        _visibility.value = !_visibility.value
    }
}