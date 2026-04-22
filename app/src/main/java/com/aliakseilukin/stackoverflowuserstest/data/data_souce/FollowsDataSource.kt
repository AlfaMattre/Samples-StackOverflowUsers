package com.aliakseilukin.stackoverflowuserstest.data.data_souce

import kotlinx.coroutines.flow.Flow

interface FollowsDataSource {
    val followIds: Flow<Set<Int>>

    suspend fun follow(id: Int)
    suspend fun unfollow(id: Int)
}