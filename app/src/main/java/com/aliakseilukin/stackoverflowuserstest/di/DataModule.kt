package com.aliakseilukin.stackoverflowuserstest.di

import com.aliakseilukin.stackoverflowuserstest.data.data_souce.StackOverflowDataSource
import com.aliakseilukin.stackoverflowuserstest.data.data_souce.StackOverflowDataSourceImpl
import com.aliakseilukin.stackoverflowuserstest.data.repository.NetworkRepositoryImpl
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
        impl: StackOverflowDataSourceImpl
    ): StackOverflowDataSource


    @Binds
    @Singleton
    abstract fun bindNetworkRepository(
        impl: NetworkRepositoryImpl
    ): NetworkRepository
}