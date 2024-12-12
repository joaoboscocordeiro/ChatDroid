package com.joaobosco.chatdroid.data.network.model

import kotlinx.serialization.Serializable

/**
 * Created by "João Bosco" on 09/12/2024.
 */

@Serializable
data class AuthRequest(
    val username: String,
    val password: String
)
