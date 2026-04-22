package com.aliakseilukin.stackoverflowuserstest.presentation.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.aliakseilukin.stackoverflowuserstest.R

@Composable
fun UsersEmpty() {
    Text(
        text = stringResource(R.string.users_empty),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold
    )
}