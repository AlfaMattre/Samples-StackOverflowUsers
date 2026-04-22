package com.aliakseilukin.stackoverflowuserstest

import com.aliakseilukin.stackoverflowuserstest.data.repository.NetworkRepositoryImpl
import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import com.aliakseilukin.stackoverflowuserstest.domain.repository.NetworkRepository
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.GetStackOverflowUsersUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetStackOverflowUsersUseCaseTest {

    private lateinit var repository: NetworkRepository
    private lateinit var useCase: GetStackOverflowUsersUseCase

    @Before
    fun setup() {
        repository = mock(NetworkRepositoryImpl::class.java)
        useCase = GetStackOverflowUsersUseCase(repository)
    }

    @Test
    fun getStackOverflowUsersUseCase_returns_success_when_repository_returns_success() = runTest {
        val expected = ResultState.Success(
            StackOverflowUsers(
                users = listOf(
                    StackOverflowUser(
                        id = 1,
                        name = "Alex",
                        reputation = 1,
                        avatarUrl = "url"
                    )
                )
            )
        )

        `when`(repository.getUsers()).thenReturn(expected)

        val result = useCase()

        assertEquals(expected, result)
        verify(repository).getUsers()
    }

    @Test
    fun getStackOverflowUsersUseCase_returns_error_when_repository_returns_error() = runTest {
        val expected = ResultState.Error(error = "Error")

        `when`(repository.getUsers()).thenReturn(expected)

        val result = useCase()

        assertEquals(expected, result)
        verify(repository).getUsers()
    }
}