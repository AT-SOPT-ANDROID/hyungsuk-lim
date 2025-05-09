package org.sopt.at.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("loginId")
    val id: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String
)