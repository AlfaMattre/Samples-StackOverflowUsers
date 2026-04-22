package com.aliakseilukin.stackoverflowuserstest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StackOverflowUserResponse(
    @SerialName("user_id") val id: Int? = null,
    @SerialName("display_name") val name: String? = null,
    @SerialName("reputation") val reputation: Long? = null,
    @SerialName("profileImage") val avatarUrl: String? = null
)
