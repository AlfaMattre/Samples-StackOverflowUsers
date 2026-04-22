package com.aliakseilukin.stackoverflowuserstest.data.mappers

import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUserResponse
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser

fun StackOverflowUserResponse.toStockOverflowUser(): StackOverflowUser {
    return StackOverflowUser(
        id = id ?: 0,
        name = name.orEmpty(),
        reputation = reputation ?: 0,
        avatarUrl = avatarUrl.orEmpty()
    )
}