package com.aliakseilukin.stackoverflowuserstest.data.data_souce

import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUsersResponse

interface StackOverflowDataSource {
    suspend fun getUsers(): StackOverflowUsersResponse
}