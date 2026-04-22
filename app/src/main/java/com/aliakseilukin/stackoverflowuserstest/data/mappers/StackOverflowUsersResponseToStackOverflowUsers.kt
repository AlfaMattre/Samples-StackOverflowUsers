package com.aliakseilukin.stackoverflowuserstest.data.mappers

import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUserResponse
import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUsersResponse
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers

fun StackOverflowUsersResponse.toDomain(): StackOverflowUsers {
    return StackOverflowUsers(
        users = users?.map { it.toDomain() } ?: emptyList()
    )
}

fun StackOverflowUserResponse.toDomain(): StackOverflowUser {
    return StackOverflowUser(
        id = id ?: 0,
        name = name.orEmpty(),
        reputation = reputation ?: 0,
        avatarUrl = avatarUrl.orEmpty()
    )
}