package com.joaobosco.chatdroid.data.manager

import kotlinx.coroutines.flow.Flow

/**
 * Created by "João Bosco" on 24/03/2025.
 */
interface TokenManager {
    val accessToken: Flow<String>
    suspend fun saveAccessToken(token: String)
    suspend fun clearAccessToken()
}