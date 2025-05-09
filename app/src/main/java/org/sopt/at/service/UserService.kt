package org.sopt.at.service

import org.sopt.at.dto.BaseResponseDto
import org.sopt.at.my.ResponseNicknameDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/v1/users/me")
    fun getMyNickname(
        @Header("userId") userId: Int
    ): Call<BaseResponseDto<ResponseNicknameDto>>
}