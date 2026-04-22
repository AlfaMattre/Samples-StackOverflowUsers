package com.aliakseilukin.stackoverflowuserstest

import com.aliakseilukin.stackoverflowuserstest.domain.repository.FollowsRepository
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.FollowUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class FollowUseCaseTest {

    private lateinit var repository: FollowsRepository
    private lateinit var useCase: FollowUseCase

    @Before
    fun setup() {
        repository = mock(FollowsRepository::class.java)
        useCase = FollowUseCase(repository)
    }

    @Test
    fun followUseCase_calls_repository_with_id() = runTest {
        val userId = 123

        useCase(userId)

        verify(repository).follow(userId)
    }
}