package com.joaobosco.chatdroid.data.network.di

import com.joaobosco.chatdroid.data.network.NetworkDataSource
import com.joaobosco.chatdroid.data.network.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by "João Bosco" on 09/12/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    @Singleton
    fun bindNetworkDataSource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource
}