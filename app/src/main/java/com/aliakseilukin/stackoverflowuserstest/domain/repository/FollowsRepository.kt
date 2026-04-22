package com.aliakseilukin.stackoverflowuserstest.domain.repository

import kotlinx.coroutines.flow.Flow

interface FollowsRepository {
    val followIds: Flow<Set<Int>>

    suspend fun follow(userId: Int)
    suspend fun unfollow(userId: Int)
}