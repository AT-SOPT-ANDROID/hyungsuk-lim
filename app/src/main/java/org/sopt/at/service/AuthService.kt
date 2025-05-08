package org.sopt.at.service

import org.sopt.at.dto.BaseResponseDto
import org.sopt.at.signup.RequestSignUpDto
import org.sopt.at.signup.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    fun requestSignUp(
        @Body requestSignUpDto: RequestSignUpDto
    ): Call<BaseResponseDto<ResponseSignUpDto>>
}