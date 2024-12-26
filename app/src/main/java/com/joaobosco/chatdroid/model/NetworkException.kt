package com.joaobosco.chatdroid.model

/**
 * Created by "João Bosco" on 26/12/2024.
 */

sealed class NetworkException(message: String, cause: Throwable? = null) :
    Exception(message, cause) {
    class ApiException(val responseMessage: String, val statusCode: Int) :
        NetworkException(responseMessage)

    class UnKnownNetworkException(cause: Throwable? = null) :
        NetworkException("Unknown network exception", cause)
}