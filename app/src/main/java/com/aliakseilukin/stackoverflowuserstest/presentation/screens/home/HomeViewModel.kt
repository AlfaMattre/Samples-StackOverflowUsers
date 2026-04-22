package com.aliakseilukin.stackoverflowuserstest.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.FollowUseCase
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.GetFollowsUseCase
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.GetStackOverflowUsersUseCase
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.UnfollowUseCase
import com.aliakseilukin.stackoverflowuserstest.presentation.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStackOverflowUsersUseCase: GetStackOverflowUsersUseCase,
    private val followUseCase: FollowUseCase,
    private val unfollowUseCase: UnfollowUseCase,
    private val getFollowsUseCase: GetFollowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val state = _state.asStateFlow()

    init {
        getUsers()
        observeFollows()
    }

    fun toggleFollow(userId: Int) {
        viewModelScope.launch {
            val state = _state.value
            if (state is HomeUIState.Success) {
                if (state.followIds.contains(userId)) {
                    unfollowUseCase(userId)
                } else {
                    followUseCase(userId)
                }
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            _state.value = HomeUIState.Loading

            val stackOverflowUsers = async { getStackOverflowUsersUseCase() }
            val followIds = getFollowsUseCase().first()

            when (val result = stackOverflowUsers.await()) {
                is ResultState.Success -> {
                    val users = result.data.users.map { user ->
                        user.toUI().copy(isFollowing = followIds.contains(user.id))
                    }
                    _state.value = if (users.isEmpty()) {
                        HomeUIState.Error(errorMessage = "No users found")
                    } else {
                        HomeUIState.Success(users = users, followIds = followIds)
                    }
                }

                is ResultState.Error -> {
                    _state.value = HomeUIState.Error(errorMessage = result.error)
                }
            }
        }
    }

    private fun observeFollows() {
        viewModelScope.launch {
            getFollowsUseCase().collectLatest { followIds ->
                val state = _state.value
                if (state is HomeUIState.Success) {
                    val updatedUsers = state.users.map { user ->
                        user.copy(isFollowing = followIds.contains(user.id))
                    }
                    _state.value = state.copy(
                        users = updatedUsers,
                        followIds = followIds
                    )
                }
            }
        }
    }
}