package com.joaobosco.chatdroid.data.manager.token

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.joaobosco.chatdroid.data.datastore.TokensKeys
import com.joaobosco.chatdroid.data.datastore.tokenDataStore
import com.joaobosco.chatdroid.data.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by "João Bosco" on 24/03/2025.
 */
class TokenManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : TokenManager {

    private val tokenDataStore = context.tokenDataStore

    override val accessToken: Flow<String>
        get() = tokenDataStore.data.map { preferences ->
            preferences[TokensKeys.ACCESS_TOKEN] ?: ""
        }

    override suspend fun saveAccessToken(token: String) {
        withContext(ioDispatcher) {
            tokenDataStore.edit { preferences ->
                preferences[TokensKeys.ACCESS_TOKEN] = token
            }
        }
    }

    override suspend fun clearAccessToken() {
        withContext(ioDispatcher) {
            tokenDataStore.edit { preferences ->
                preferences.remove(TokensKeys.ACCESS_TOKEN)
            }
        }
    }
}