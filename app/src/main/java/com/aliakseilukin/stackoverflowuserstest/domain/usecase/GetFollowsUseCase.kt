package com.aliakseilukin.stackoverflowuserstest.domain.usecase

import com.aliakseilukin.stackoverflowuserstest.domain.repository.FollowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFollowsUseCase @Inject constructor(
    private val repository: FollowsRepository
) {

    operator fun invoke(): Flow<Set<Int>> =
        repository.followIds
}