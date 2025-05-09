package org.sopt.at.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignInDto(
    @SerialName("userId")
    val userId: Int
)
