package com.joaobosco.chatdroid.model

/**
 * Created by "João Bosco" on 11/12/2024.
 */
data class CreateAccount(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureId: String?
)
