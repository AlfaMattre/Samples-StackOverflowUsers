package com.aliakseilukin.stackoverflowuserstest.domain.repository

import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUsersResponse
import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState

interface NetworkRepository {
    suspend fun getUsers(): ResultState<StackOverflowUsersResponse>
}