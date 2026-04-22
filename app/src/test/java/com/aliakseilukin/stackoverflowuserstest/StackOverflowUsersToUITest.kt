package com.aliakseilukin.stackoverflowuserstest

import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import com.aliakseilukin.stackoverflowuserstest.presentation.mappers.toUI
import com.aliakseilukin.stackoverflowuserstest.presentation.model.StackOverflowUserUI
import com.aliakseilukin.stackoverflowuserstest.presentation.model.StackOverflowUsersUI
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StackOverflowUsersToUITest {

    @Test
    fun stackOverflowUser_toUI_maps_correctly() {
        val domainModel = StackOverflowUser(
            id = 1,
            name = "Alex",
            reputation = 1,
            avatarUrl = "url"
        )

        val result = domainModel.toUI()

        val expected = StackOverflowUserUI(
            id = 1,
            name = "Alex",
            reputation = 1,
            avatarUrl = "url"
        )

        assertEquals(result, expected)
    }

    @Test
    fun stackOverflowUsers_toUI_maps_correctly() {
        val domainModel = StackOverflowUsers(
            users = listOf(
                StackOverflowUser(
                    id = 1,
                    name = "Alex",
                    reputation = 1,
                    avatarUrl = "url"
                ),
                StackOverflowUser(
                    id = 2,
                    name = "Kate",
                    reputation = 2,
                    avatarUrl = "url"
                )
            )
        )

        val result = domainModel.toUI()

        val expected = StackOverflowUsersUI(
            users = listOf(
                StackOverflowUserUI(
                    id = 1,
                    name = "Alex",
                    reputation = 1,
                    avatarUrl = "url"
                ),
                StackOverflowUserUI(
                    id = 2,
                    name = "Kate",
                    reputation = 2,
                    avatarUrl = "url"
                )
            )
        )

        assertEquals(result, expected)
    }
}