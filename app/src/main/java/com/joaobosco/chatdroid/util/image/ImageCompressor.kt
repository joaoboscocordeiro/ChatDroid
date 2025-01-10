package com.joaobosco.chatdroid.util.image

import android.net.Uri
import java.io.File

/**
 * Created by "João Bosco" on 09/01/2025.
 */
interface ImageCompressor {
    suspend fun compressAndResizeImage(
        imageUri: Uri,
        quality: Int = 80,
        maxWidth: Int = 1080,
        maxHeight: Int = 1080
    ): File
}