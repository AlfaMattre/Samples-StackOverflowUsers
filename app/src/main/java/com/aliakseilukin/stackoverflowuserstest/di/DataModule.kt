package com.aliakseilukin.stackoverflowuserstest.di

import com.aliakseilukin.stackoverflowuserstest.data.data_souce.FollowsDataSource
import com.aliakseilukin.stackoverflowuserstest.data.data_souce.FollowsDataSourceImpl
import com.aliakseilukin.stackoverflowuserstest.data.data_souce.StackOverflowDataSource
import com.aliakseilukin.stackoverflowuserstest.data.data_souce.StackOverflowDataSourceImpl
import com.aliakseilukin.stackoverflowuserstest.data.repository.FollowsRepositoryImpl
import com.aliakseilukin.stackoverflowuserstest.data.repository.NetworkRepositoryImpl
import com.aliakseilukin.stackoverflowuserstest.domain.repository.FollowsRepository
import com.aliakseilukin.stackoverflowuserstest.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindStackOverflowDataSource(
        dataSourceImpl: StackOverflowDataSourceImpl
    ): StackOverflowDataSource


    @Binds
    @Singleton
    abstract fun bindNetworkRepository(
        repositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

    @Binds
    @Singleton
    abstract fun bindFollowsDataSource(
        dataSourceImpl: FollowsDataSourceImpl
    ): FollowsDataSource

    @Binds
    @Singleton
    abstract fun bindFollowsRepository(
        repositoryImpl: FollowsRepositoryImpl
    ): FollowsRepository
}