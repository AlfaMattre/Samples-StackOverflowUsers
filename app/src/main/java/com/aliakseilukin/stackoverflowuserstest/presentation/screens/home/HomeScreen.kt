package com.aliakseilukin.stackoverflowuserstest.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliakseilukin.stackoverflowuserstest.presentation.composable.UserItem
import com.aliakseilukin.stackoverflowuserstest.presentation.composable.UsersEmpty

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    when (val uiState = state.value) {
        is HomeUIState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeUIState.Error -> {
            Column(
                modifier = modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UsersEmpty()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = uiState.errorMessage, textAlign = TextAlign.Center)
            }
        }

        is HomeUIState.Success -> {
            if (uiState.users.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    UsersEmpty()
                }
            } else {
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    items(
                        items = uiState.users,
                        key = { user -> user.id }
                    ) {
                        UserItem(it)
                    }
                }
            }
        }
    }
}