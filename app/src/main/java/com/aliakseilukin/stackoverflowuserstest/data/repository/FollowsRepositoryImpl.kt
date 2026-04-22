package com.aliakseilukin.stackoverflowuserstest.data.repository

import com.aliakseilukin.stackoverflowuserstest.data.data_souce.FollowsDataSource
import com.aliakseilukin.stackoverflowuserstest.domain.repository.FollowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FollowsRepositoryImpl @Inject constructor(
    private val dataSource: FollowsDataSource
) : FollowsRepository {

    override val followIds: Flow<Set<Int>> = dataSource.followIds

    override suspend fun follow(userId: Int) =
        dataSource.follow(userId)

    override suspend fun unfollow(userId: Int) =
        dataSource.unfollow(userId)
}