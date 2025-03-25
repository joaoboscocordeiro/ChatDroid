package com.joaobosco.chatdroid.data.manager.selfuser

import com.joaobosco.chatdroid.SelfUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by "João Bosco" on 24/03/2025.
 */
interface SelfUserManager {
    val selfUserFlow: Flow<SelfUser>

    suspend fun saveSelfUserData(
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        username: String
    )

    suspend fun clearSelfUser()
}