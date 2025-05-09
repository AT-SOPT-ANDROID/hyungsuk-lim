package org.sopt.at.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUpDto(
    @SerialName("userId")
    val userId: Int,

    @SerialName("nickname")
    val nickname: String,
)