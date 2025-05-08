package org.sopt.at.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("loingId")
    val id: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String
)