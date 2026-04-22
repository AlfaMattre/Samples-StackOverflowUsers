package com.aliakseilukin.stackoverflowuserstest.domain.usecase

import com.aliakseilukin.stackoverflowuserstest.data.model.StackOverflowUsersResponse
import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.repository.NetworkRepository
import javax.inject.Inject

class GetStackOverflowUsersUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    suspend operator fun invoke(): ResultState<StackOverflowUsersResponse> =
        networkRepository.getUsers()
}