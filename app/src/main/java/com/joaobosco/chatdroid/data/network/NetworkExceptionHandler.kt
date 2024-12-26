package com.joaobosco.chatdroid.data.network

import com.joaobosco.chatdroid.model.NetworkException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText

/**
 * Created by "João Bosco" on 26/12/2024.
 */

suspend fun <T> handlerNetworkException(block: suspend () -> T): T {
    return try {
        block()
    } catch (e: ClientRequestException) {
        val errorMessage = e.response.bodyAsText()
        throw NetworkException.ApiException(errorMessage, e.response.status.value)
    } catch (e: Exception) {
        throw NetworkException.UnKnownNetworkException(e)
    }
}