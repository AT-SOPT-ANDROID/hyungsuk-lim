package org.sopt.at.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto<T>(
    @SerialName("success")
    val success: Boolean,

    @SerialName("code")
    val code: ResponseCode,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: T?
)