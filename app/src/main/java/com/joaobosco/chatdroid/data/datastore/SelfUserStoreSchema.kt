package com.joaobosco.chatdroid.data.datastore

import android.content.Context
import androidx.datastore.dataStore

/**
 * Created by "João Bosco" on 24/03/2025.
 */

val Context.selfUserStore by dataStore(
    fileName = "self_user.pb",
    serializer = SelfUserSerializer
)