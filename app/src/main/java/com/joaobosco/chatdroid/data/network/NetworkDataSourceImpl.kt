package com.joaobosco.chatdroid.data.network

import com.joaobosco.chatdroid.data.network.model.AuthRequest
import com.joaobosco.chatdroid.data.network.model.CreateAccountRequest
import com.joaobosco.chatdroid.data.network.model.TokenRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

/**
 * Created by "João Bosco" on 09/12/2024.
 */

class NetworkDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : NetworkDataSource {
    override suspend fun signUp(request: CreateAccountRequest) {
        httpClient.post("signup") {
            setBody(request)
        }.body<Unit>()
    }

    override suspend fun signIn(request: AuthRequest): TokenRequest {
        return httpClient.post("signin") {
            setBody(request)
        }.body()
    }
}