package org.sopt.at.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignInDto(
    @SerialName("loginId")
    val id: String,
    @SerialName("password")
    val password: String
)
