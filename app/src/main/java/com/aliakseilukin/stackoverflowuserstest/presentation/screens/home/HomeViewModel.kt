package com.aliakseilukin.stackoverflowuserstest.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.usecase.GetStackOverflowUsersUseCase
import com.aliakseilukin.stackoverflowuserstest.presentation.mappers.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStackOverflowUsersUseCase: GetStackOverflowUsersUseCase
): ViewModel() {

    private val _state = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _state.value = HomeUIState.Loading
            when (val result = getStackOverflowUsersUseCase()) {

                is ResultState.Success -> {
                    val users = result.data.users.map { it.toUI() }
                    _state.value = if (users.isEmpty()) {
                        HomeUIState.Error(errorMessage = "No users found")
                    } else {
                        HomeUIState.Success(users)
                    }
                }

                is ResultState.Error -> {
                    _state.value = HomeUIState.Error(errorMessage = result.error)
                }
            }
        }
    }
}