package com.aliakseilukin.stackoverflowuserstest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.aliakseilukin.stackoverflowuserstest.presentation.ui.theme.StackOverflowUsersTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowUsersTestTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}