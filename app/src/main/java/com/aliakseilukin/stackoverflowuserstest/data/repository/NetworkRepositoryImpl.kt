package com.aliakseilukin.stackoverflowuserstest.data.repository

import com.aliakseilukin.stackoverflowuserstest.data.data_souce.StackOverflowDataSource
import com.aliakseilukin.stackoverflowuserstest.data.mappers.toDomain
import com.aliakseilukin.stackoverflowuserstest.data.service.safeApiCall
import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import com.aliakseilukin.stackoverflowuserstest.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val dataSource: StackOverflowDataSource
) : NetworkRepository {

    override suspend fun getUsers(): ResultState<StackOverflowUsers> =
        safeApiCall { dataSource.getUsers().toDomain() }
}