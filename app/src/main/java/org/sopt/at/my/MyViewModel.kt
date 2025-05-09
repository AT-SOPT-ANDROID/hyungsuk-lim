package org.sopt.at.my

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.sopt.at.ServicePool
import org.sopt.at.dto.BaseResponseDto
import org.sopt.at.dto.response.ResponseNicknameDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    private val _nickname = mutableStateOf("")
    val nickname: State<String> get() = _nickname

    private val userService by lazy { ServicePool.userService }

    fun setUserNickname(nickname: String) {
        _nickname.value = nickname
    }

    fun getMyNickname(userId: Int) {
        userService.getMyNickname(
            userId = userId
        ).enqueue(object : Callback<BaseResponseDto<ResponseNicknameDto>> {
            override fun onResponse(
                call: Call<BaseResponseDto<ResponseNicknameDto>>,
                response: Response<BaseResponseDto<ResponseNicknameDto>>
            ) {
                if (response.isSuccessful) {
                    Log.i("is", "Success")
                    val nickname = response.body()?.data?.nickname
                    if (nickname != null) setUserNickname(nickname = nickname)
                } else {
                    val errorBody: JsonElement =
                        Json.parseToJsonElement(response.errorBody()?.string() ?: "")
                    val errorMsg = errorBody.jsonObject["message"]?.jsonPrimitive?.content ?: ""
                    Log.e("error", errorMsg)
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<ResponseNicknameDto>>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        })
    }
}