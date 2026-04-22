package com.aliakseilukin.stackoverflowuserstest.presentation.mappers

import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import com.aliakseilukin.stackoverflowuserstest.presentation.model.StackOverflowUserUI
import com.aliakseilukin.stackoverflowuserstest.presentation.model.StackOverflowUsersUI

fun StackOverflowUsers.toUI(): StackOverflowUsersUI {
    return StackOverflowUsersUI(
        users = users.map { it.toUI() }
    )
}

fun StackOverflowUser.toUI(): StackOverflowUserUI {
    return StackOverflowUserUI(
        id = id,
        name = name,
        reputation = reputation,
        avatarUrl = avatarUrl
    )
}