package com.joaobosco.chatdroid.data.manager.token

import android.content.Context
import com.joaobosco.chatdroid.data.datastore.TokensKeys
import com.joaobosco.chatdroid.data.di.IoDispatcher
import com.joaobosco.chatdroid.data.manager.CryptoManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by "João Bosco" on 24/03/2025.
 */
class SecureTokenManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : TokenManager {

    override val accessToken: Flow<String>
        get() = flowOf(CryptoManager.decryptData(context, TokensKeys.ACCESS_TOKEN.name))

    override suspend fun saveAccessToken(token: String) {
        withContext(ioDispatcher) {
            CryptoManager.encryptData(context, TokensKeys.ACCESS_TOKEN.name, token)
        }
    }

    override suspend fun clearAccessToken() {
        withContext(ioDispatcher) {
            CryptoManager.encryptData(context, TokensKeys.ACCESS_TOKEN.name, "")
        }
    }
}