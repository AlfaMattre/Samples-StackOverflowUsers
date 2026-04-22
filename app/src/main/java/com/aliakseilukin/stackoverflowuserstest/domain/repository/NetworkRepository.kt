package com.aliakseilukin.stackoverflowuserstest.domain.repository

import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers

interface NetworkRepository {
    suspend fun getUsers(): ResultState<StackOverflowUsers>
}