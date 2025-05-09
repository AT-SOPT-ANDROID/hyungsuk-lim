package org.sopt.at.signup

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.sopt.at.ServicePool
import org.sopt.at.dto.BaseResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _id = mutableStateOf("")
    val id: MutableState<String> get() = _id

    private val _pw = mutableStateOf("")
    val pw: MutableState<String> get() = _pw

    private val _nickname = mutableStateOf("")
    val nickname: MutableState<String> get() = _nickname

    var signUpStep by mutableStateOf<SignUpStep>(SignUpStep.Id)
        private set

    var signUpResult by mutableStateOf<SignUpResult?>(null)
        private set


    private val _visibility = mutableStateOf(false)
    val visibility: MutableState<Boolean> get() = _visibility

    fun updateId(id: String) {
        _id.value = id
    }

    fun updatePw(pw: String) {
        _pw.value = pw
    }

    fun updateNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun nextStep() {
        signUpStep = if (signUpStep == SignUpStep.Id) {
            SignUpStep.Password
        } else {
            SignUpStep.Nickname
        }
    }

    fun onPrevious() {
        signUpStep = if (signUpStep == SignUpStep.Password) {
            SignUpStep.Id
        } else {
            SignUpStep.Password
        }
    }

    fun switchVisibility() {
        _visibility.value = !_visibility.value
    }

    fun requestSignUp() {
        authService.requestSignUp(
            requestSignUpDto = RequestSignUpDto(
                id = id.value,
                password = pw.value,
                nickname = nickname.value
            )
        ).enqueue(object : Callback<BaseResponseDto<ResponseSignUpDto>> {
            override fun onResponse(
                call: Call<BaseResponseDto<ResponseSignUpDto>>,
                response: Response<BaseResponseDto<ResponseSignUpDto>>
            ) {
                if (response.isSuccessful) {
                    Log.i("is", "Success")
                    signUpResult = response.body()?.data?.let {
                        SignUpResult.Success(
                            it.userId,
                            it.nickname,
                        )
                    }
                } else {
                    val errorBody: JsonElement =
                        Json.parseToJsonElement(response.errorBody()?.string() ?: "")
                    val errorMsg = errorBody.jsonObject["message"]?.jsonPrimitive?.content ?: ""

                    signUpResult = SignUpResult.Failure(errorMsg)
                    Log.e("error", response.message().toString())
                    Log.e("error", errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<ResponseSignUpDto>>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        }
        )
    }
}

sealed class SignUpStep {
    data object Id : SignUpStep()
    data object Password : SignUpStep()
    data object Nickname : SignUpStep()
}

sealed class SignUpResult {
    data class Success(val userId: Int, val nickname: String) : SignUpResult()
    data class Failure(val message: String?) : SignUpResult()
}