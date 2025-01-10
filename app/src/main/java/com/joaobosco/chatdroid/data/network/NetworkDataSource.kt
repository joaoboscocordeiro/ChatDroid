package com.joaobosco.chatdroid.data.network

import com.joaobosco.chatdroid.data.network.model.AuthRequest
import com.joaobosco.chatdroid.data.network.model.CreateAccountRequest
import com.joaobosco.chatdroid.data.network.model.ImageResponse
import com.joaobosco.chatdroid.data.network.model.TokenRequest

/**
 * Created by "João Bosco" on 09/12/2024.
 */

interface NetworkDataSource {
    suspend fun signUp(request: CreateAccountRequest)
    suspend fun signIn(request: AuthRequest): TokenRequest
    suspend fun uploadProfilePicture(filePath: String): ImageResponse
}