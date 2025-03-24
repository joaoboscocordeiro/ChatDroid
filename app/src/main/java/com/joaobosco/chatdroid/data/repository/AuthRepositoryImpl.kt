package com.joaobosco.chatdroid.data.repository

import com.joaobosco.chatdroid.data.di.IoDispatcher
import com.joaobosco.chatdroid.data.manager.TokenManager
import com.joaobosco.chatdroid.data.network.NetworkDataSource
import com.joaobosco.chatdroid.data.network.model.AuthRequest
import com.joaobosco.chatdroid.data.network.model.CreateAccountRequest
import com.joaobosco.chatdroid.model.CreateAccount
import com.joaobosco.chatdroid.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by "João Bosco" on 11/12/2024.
 */

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val tokenManager: TokenManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                networkDataSource.signUp(
                    request = CreateAccountRequest(
                        username = createAccount.username,
                        password = createAccount.password,
                        firstName = createAccount.firstName,
                        lastName = createAccount.lastName,
                        profilePictureId = createAccount.profilePictureId
                    )
                )
            }
        }
    }

    override suspend fun signIn(username: String, password: String): Result<Unit> {
        return withContext(ioDispatcher) {
            runCatching {
                val tokenResponse = networkDataSource.signIn(
                    request = AuthRequest(
                        username = username,
                        password = password
                    )
                )
                tokenManager.saveAccessToken(tokenResponse.token)
            }
        }
    }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> {
        return withContext(ioDispatcher) {
            runCatching {
                val imageResponse = networkDataSource.uploadProfilePicture(filePath)
                Image(
                    id = imageResponse.id,
                    name = imageResponse.name,
                    type = imageResponse.type,
                    url = imageResponse.url
                )
            }
        }
    }
}