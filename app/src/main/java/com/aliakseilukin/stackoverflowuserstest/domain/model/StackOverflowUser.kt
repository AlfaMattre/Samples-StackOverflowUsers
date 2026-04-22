package com.aliakseilukin.stackoverflowuserstest.domain.model

data class StackOverflowUser(
    val id: Int = 0,
    val name: String = "",
    val reputation: Long = 0,
    val avatarUrl: String = ""
)
