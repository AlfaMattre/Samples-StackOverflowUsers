package com.aliakseilukin.stackoverflowuserstest

import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUser
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.FollowUseCase
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.GetFollowsUseCase
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.GetStackOverflowUsersUseCase
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.UnfollowUseCase
import com.aliakseilukin.stackoverflowuserstest.presentation.model.StackOverflowUserUI
import com.aliakseilukin.stackoverflowuserstest.presentation.screens.home.HomeUIState
import com.aliakseilukin.stackoverflowuserstest.presentation.screens.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getUsersUseCase: GetStackOverflowUsersUseCase = mock()
    private val followUseCase: FollowUseCase = mock()
    private val unfollowUseCase: UnfollowUseCase = mock()
    private val getFollowsUseCase: GetFollowsUseCase = mock()

    fun createViewModel(): HomeViewModel {
        return HomeViewModel(
            getUsersUseCase,
            followUseCase,
            unfollowUseCase,
            getFollowsUseCase
        )
    }

    @Test
    fun initial_state_loading() = runTest {
        whenever(getFollowsUseCase.invoke()).thenReturn(flowOf(emptySet()))
        whenever(getUsersUseCase.invoke()).thenReturn(
            ResultState.Success(StackOverflowUsers(emptyList()))
        )

        val viewModel = createViewModel()

        assertEquals(HomeUIState.Loading, viewModel.state.value)
    }

    @Test
    fun show_success_state() = runTest {
        whenever(getFollowsUseCase.invoke()).thenReturn(flowOf(emptySet()))
        whenever(getUsersUseCase.invoke()).thenReturn(
            ResultState.Success(
                StackOverflowUsers(
                    listOf(
                        StackOverflowUser(
                            id = 1,
                            name = "Alex",
                            reputation = 1,
                            avatarUrl = "url"
                        )
                    )
                )
            )
        )

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(
            HomeUIState.Success(
                users = listOf(
                    StackOverflowUserUI(
                        id = 1,
                        name = "Alex",
                        reputation = 1,
                        avatarUrl = "url"
                    )
                ), followIds = emptySet()
            ),
            viewModel.state.value
        )
    }

    @Test
    fun show_error_state() = runTest {
        whenever(getFollowsUseCase.invoke()).thenReturn(flowOf(emptySet()))
        whenever(getUsersUseCase.invoke()).thenReturn(
            ResultState.Error("No internet")
        )

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(HomeUIState.Error("No internet"), viewModel.state.value)
    }

    @Test
    fun show_error_state_when_users_empty() = runTest {
        whenever(getFollowsUseCase.invoke()).thenReturn(flowOf(emptySet()))
        whenever(getUsersUseCase.invoke()).thenReturn(
            ResultState.Success(StackOverflowUsers(emptyList()))
        )

        val viewModel = createViewModel()
        advanceUntilIdle()

        assertEquals(HomeUIState.Error("No users found"), viewModel.state.value)
    }

    @Test
    fun toggleFollow_calls_unfollow_when_id_already_followed() = runTest {
        val response = StackOverflowUsers(
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

        whenever(getFollowsUseCase.invoke()).thenReturn(
            flowOf(setOf(1))
        )
        whenever(getUsersUseCase.invoke()).thenReturn(
            ResultState.Success(response)
        )

        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.toggleFollow(1)
        advanceUntilIdle()

        verify(unfollowUseCase).invoke(1)
        verify(followUseCase, never()).invoke(any())
    }

    @Test
    fun toggleFollow_calls_follow_when_id_not_followed() = runTest {
        val response = StackOverflowUsers(
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

        whenever(getFollowsUseCase.invoke()).thenReturn(
            flowOf(emptySet())
        )
        whenever(getUsersUseCase.invoke()).thenReturn(
            ResultState.Success(response)
        )

        val viewModel = HomeViewModel(
            getUsersUseCase,
            followUseCase,
            unfollowUseCase,
            getFollowsUseCase
        )
        advanceUntilIdle()

        viewModel.toggleFollow(1)
        advanceUntilIdle()

        verify(followUseCase).invoke(1)
        verify(unfollowUseCase, never()).invoke(any())
    }
}