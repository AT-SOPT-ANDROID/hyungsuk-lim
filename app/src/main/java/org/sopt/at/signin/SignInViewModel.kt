package org.sopt.at.signin

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
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

class SignInViewModel(
    private var sharedPreferences: SharedPreferences
) : ViewModel() {

    private val authService by lazy { ServicePool.authService }

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

    fun resetSignInResult() {
        signInResult = null
    }

    fun switchVisibility() {
        _visibility.value = !_visibility.value
    }

    fun requestSignIn() {
        authService.postSignIn(
            requestSignInDto = RequestSignInDto(
                id = id.value,
                password = pw.value
            )
        ).enqueue(object : Callback<BaseResponseDto<ResponseSignInDto>> {
            override fun onResponse(
                call: Call<BaseResponseDto<ResponseSignInDto>>,
                response: Response<BaseResponseDto<ResponseSignInDto>>
            ) {
                if (response.isSuccessful) {
                    Log.i("is", "Success")
                    val userId = response.body()?.data?.userId
                    if (userId != null) {
                        signInResult = SignInResult.Success(userId)

                        sharedPreferences.edit() {
                            putInt("userId", userId)
                        }
                    }
                } else {
                    val errorBody: JsonElement =
                        Json.parseToJsonElement(response.errorBody()?.string() ?: "")
                    val errorMsg = errorBody.jsonObject["message"]?.jsonPrimitive?.content ?: ""
                    signInResult = if (errorMsg.startsWith("아이디")) {
                        SignInResult.InvalidId(errorMsg)
                    } else {
                        SignInResult.InvalidPw(errorMsg)
                    }
                    Log.e("error", errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<ResponseSignInDto>>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        }
        )
    }
}

sealed class SignInResult {
    data class InvalidId(val errMsg: String) : SignInResult()
    data class InvalidPw(val errMsg: String) : SignInResult()
    data class Success(val userId: Int) : SignInResult()
}