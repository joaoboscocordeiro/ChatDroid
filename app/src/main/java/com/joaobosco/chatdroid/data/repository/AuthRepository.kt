package com.joaobosco.chatdroid.data.repository

import com.joaobosco.chatdroid.model.CreateAccount

/**
 * Created by "João Bosco" on 11/12/2024.
 */

interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount)
    suspend fun signIn(username: String, password: String)
}