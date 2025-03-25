package com.joaobosco.chatdroid.data.manager.di

import com.joaobosco.chatdroid.data.manager.selfuser.SelfUserManager
import com.joaobosco.chatdroid.data.manager.selfuser.SelfUserManagerImpl
import com.joaobosco.chatdroid.data.manager.token.SecureTokenManagerImpl
import com.joaobosco.chatdroid.data.manager.token.TokenManager
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
    fun bindTokenManager(tokenManager: SecureTokenManagerImpl): TokenManager

    @Binds
    @Singleton
    fun bindSelfUserManager(selfUserManager: SelfUserManagerImpl): SelfUserManager
}