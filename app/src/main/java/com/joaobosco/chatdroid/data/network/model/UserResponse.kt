package com.joaobosco.chatdroid.data.network.model

import kotlinx.serialization.Serializable

/**
 * Created by "João Bosco" on 24/03/2025.
 */

@Serializable
data class UserResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val profilePictureUrl: String?,
    val username: String,
)
