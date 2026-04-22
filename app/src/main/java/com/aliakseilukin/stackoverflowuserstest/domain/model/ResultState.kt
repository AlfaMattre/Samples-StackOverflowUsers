package com.aliakseilukin.stackoverflowuserstest.domain.model

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val error: String) : ResultState<Nothing>
}