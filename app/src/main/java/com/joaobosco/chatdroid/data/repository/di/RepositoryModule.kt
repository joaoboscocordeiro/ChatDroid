package com.joaobosco.chatdroid.data.repository.di

import com.joaobosco.chatdroid.data.repository.AuthRepository
import com.joaobosco.chatdroid.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by "João Bosco" on 11/12/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository
}