package com.joaobosco.chatdroid.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

/**
 * Created by "João Bosco" on 24/03/2025.
 */

val Context.tokenDataStore by preferencesDataStore(name = "token_store")

object TokensKeys {
    val ACCESS_TOKEN = stringPreferencesKey("accessToken")
}