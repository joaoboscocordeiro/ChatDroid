package com.joaobosco.chatdroid.data.manager.selfuser

import android.content.Context
import com.joaobosco.chatdroid.SelfUser
import com.joaobosco.chatdroid.data.datastore.selfUserStore
import com.joaobosco.chatdroid.data.di.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by "João Bosco" on 24/03/2025.
 */

class SelfUserManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SelfUserManager {

    private val selfUserStore = context.selfUserStore

    override val selfUserFlow: Flow<SelfUser>
        get() = selfUserStore.data

    override suspend fun saveSelfUserData(
        firstName: String,
        lastName: String,
        profilePictureUrl: String,
        username: String
    ) {
        withContext(ioDispatcher) {
            selfUserStore.updateData {
                it.toBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setProfilePictureUrl(profilePictureUrl)
                    .setUsername(username)
                    .build()
            }
        }
    }

    override suspend fun clearSelfUser() {
        withContext(ioDispatcher) {
            selfUserStore.updateData {
                it.toBuilder()
                    .clearFirstName()
                    .clearLastName()
                    .clearProfilePictureUrl()
                    .clearUsername()
                    .build()
            }
        }
    }
}