package com.aliakseilukin.stackoverflowuserstest.presentation.screens.home

import com.aliakseilukin.stackoverflowuserstest.presentation.model.StackOverflowUserUI

sealed interface HomeUIState {
    data object Loading : HomeUIState

    data class Success(
        val users: List<StackOverflowUserUI>
    ) : HomeUIState

    data class Error(
        val errorMessage: String
    ) : HomeUIState
}