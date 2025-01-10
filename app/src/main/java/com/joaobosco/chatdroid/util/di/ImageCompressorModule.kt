package com.joaobosco.chatdroid.util.di

import com.joaobosco.chatdroid.util.image.ImageCompressor
import com.joaobosco.chatdroid.util.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by "João Bosco" on 09/01/2025.
 */

@Module
@InstallIn(ViewModelComponent::class)
interface ImageCompressorModule {

    @Binds
    fun bindImageCompressor(compressor: ImageCompressorImpl): ImageCompressor
}