package org.sopt.at.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseNicknameDto(
    @SerialName("nickname")
    val nickname: String
)
