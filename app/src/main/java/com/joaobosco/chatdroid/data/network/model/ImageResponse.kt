package com.joaobosco.chatdroid.data.network.model

import kotlinx.serialization.Serializable

/**
 * Created by "João Bosco" on 09/01/2025.
 */

@Serializable
data class ImageResponse(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)
