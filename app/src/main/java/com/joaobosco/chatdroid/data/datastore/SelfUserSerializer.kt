package com.joaobosco.chatdroid.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.joaobosco.chatdroid.SelfUser
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by "João Bosco" on 24/03/2025.
 */

object SelfUserSerializer : Serializer<SelfUser> {
    override val defaultValue: SelfUser
        get() = SelfUser.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SelfUser {

        try {
            return SelfUser.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SelfUser, output: OutputStream) {
        t.writeTo(output)
    }
}