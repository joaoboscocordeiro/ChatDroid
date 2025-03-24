package com.joaobosco.chatdroid.data.manager.di

import com.joaobosco.chatdroid.data.manager.TokenManager
import com.joaobosco.chatdroid.data.manager.TokenManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by "João Bosco" on 24/03/2025.
 */

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    @Singleton
    fun bindTokenManager(tokenManager: TokenManagerImpl): TokenManager
}