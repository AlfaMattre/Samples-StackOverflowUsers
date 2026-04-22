package com.aliakseilukin.stackoverflowuserstest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StackOverflowUsersResponse(
    @SerialName("items") val users: List<StackOverflowUserResponse>? = null
)
