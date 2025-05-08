package org.sopt.at.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.ResponseCode

@Serializable
data class ResponseSignUpDto(
    @SerialName("userId")
    val userId: String,

    @SerialName("nickname")
    val nickname: String,
)