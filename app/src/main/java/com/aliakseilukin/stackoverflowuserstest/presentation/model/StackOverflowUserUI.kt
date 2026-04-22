package com.aliakseilukin.stackoverflowuserstest.presentation.model

data class StackOverflowUserUI(
    val id: Int = 0,
    val name: String = "",
    val reputation: Long = 0,
    val avatarUrl: String = "",
    val isFollowing: Boolean = false
)
