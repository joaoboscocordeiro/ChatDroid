package com.joaobosco.chatdroid.data.repository

import com.joaobosco.chatdroid.model.CreateAccount
import com.joaobosco.chatdroid.model.Image

/**
 * Created by "João Bosco" on 11/12/2024.
 */

interface AuthRepository {
    suspend fun signUp(createAccount: CreateAccount): Result<Unit>
    suspend fun signIn(username: String, password: String)
    suspend fun uploadProfilePicture(filePath: String): Result<Image>
}