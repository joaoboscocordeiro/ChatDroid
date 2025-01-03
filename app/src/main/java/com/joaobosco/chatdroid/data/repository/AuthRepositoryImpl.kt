package com.joaobosco.chatdroid.data.repository

import com.joaobosco.chatdroid.data.network.NetworkDataSource
import com.joaobosco.chatdroid.data.network.model.AuthRequest
import com.joaobosco.chatdroid.data.network.model.CreateAccountRequest
import com.joaobosco.chatdroid.model.CreateAccount
import javax.inject.Inject

/**
 * Created by "João Bosco" on 11/12/2024.
 */

class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : AuthRepository {

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> {
        return runCatching {
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

    override suspend fun signIn(username: String, password: String) {
        networkDataSource.signIn(
            request = AuthRequest(
                username = username,
                password = password
            )
        )
    }
}