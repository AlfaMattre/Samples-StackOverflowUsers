package com.aliakseilukin.stackoverflowuserstest

import com.aliakseilukin.stackoverflowuserstest.data.mappers.toDomain
import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUserResponse
import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUsersResponse
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StackOverflowUsersResponseToDomainTest {

    @Test
    fun stackOverflowUserResponse_toDomain_maps_correctly() {
        val response = StackOverflowUserResponse(
            id = 1,
            name = "Alex",
            reputation = 1,
            avatarUrl = "url"
        )

        val result = response.toDomain()

        val expected = StackOverflowUser(
            id = 1,
            name = "Alex",
            reputation = 1,
            avatarUrl = "url"
        )

        assertEquals(result, expected)
    }

    @Test
    fun stackOverflowUsersResponse_toDomain_maps_correctly() {
        val response = StackOverflowUsersResponse(
            users = listOf(
                StackOverflowUserResponse(
                    id = 1,
                    name = "Alex",
                    reputation = 1,
                    avatarUrl = "url"
                ),
                StackOverflowUserResponse(
                    id = 2,
                    name = "Kate",
                    reputation = 2,
                    avatarUrl = "url"
                )
            )
        )

        val result = response.toDomain()

        val expected = StackOverflowUsers(
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

        assertEquals(result, expected)
    }

    @Test
    fun stackOverflowUserResponse_toDomain_null_maps_correctly() {
        val response = StackOverflowUserResponse(
            id = null,
            name = null,
            reputation = null,
            avatarUrl = null
        )

        val result = response.toDomain()

        val expected = StackOverflowUser(
            id = 0,
            name = "",
            reputation = 0,
            avatarUrl = ""
        )

        assertEquals(result, expected)
    }

    @Test
    fun stackOverflowUsersResponse_toDomain_null_maps_correctly() {
        val response = StackOverflowUsersResponse(users = null)

        val result = response.toDomain()

        val expected = StackOverflowUsers(users = emptyList())

        assertEquals(result, expected)
    }
}