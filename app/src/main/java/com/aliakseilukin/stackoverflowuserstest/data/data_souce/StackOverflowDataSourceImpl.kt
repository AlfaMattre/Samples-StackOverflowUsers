package com.aliakseilukin.stackoverflowuserstest.data.data_souce

import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUsersResponse
import com.aliakseilukin.stackoverflowuserstest.data.service.StackOverflowApi
import javax.inject.Inject

class StackOverflowDataSourceImpl @Inject constructor(
    private val apiService: StackOverflowApi
) : StackOverflowDataSource {

    override suspend fun getUsers(): StackOverflowUsersResponse {
        return apiService.getUsers()
    }
}