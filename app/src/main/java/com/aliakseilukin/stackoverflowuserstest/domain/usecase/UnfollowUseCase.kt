package com.aliakseilukin.stackoverflowuserstest.domain.usecase

import com.aliakseilukin.stackoverflowuserstest.domain.repository.FollowsRepository
import javax.inject.Inject

class UnfollowUseCase @Inject constructor(
    private val repository: FollowsRepository
) {

    suspend operator fun invoke(userId: Int) =
        repository.unfollow(userId)
}