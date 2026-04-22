package com.aliakseilukin.stackoverflowuserstest.domain.usecase

import com.aliakseilukin.stackoverflowuserstest.domain.model.ResultState
import com.aliakseilukin.stackoverflowuserstest.domain.model.StackOverflowUsers
import com.aliakseilukin.stackoverflowuserstest.domain.repository.NetworkRepository
import javax.inject.Inject

class GetStackOverflowUsersUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    suspend operator fun invoke(): ResultState<StackOverflowUsers> =
        networkRepository.getUsers()
}