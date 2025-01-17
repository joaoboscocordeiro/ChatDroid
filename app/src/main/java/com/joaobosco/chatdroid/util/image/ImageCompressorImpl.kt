package com.joaobosco.chatdroid.util.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by "João Bosco" on 04/01/2025.
 */

class ImageCompressorImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageCompressor {
    /**
     * Função para comprimir e redimensionar uma imagem a partir de um Uri.
     * Esta função é suspensa para suportar coroutines.
     *
     * @param imageUri Uri da imagem a ser comprimida e redirecionada.
     * @param quality Qualidade desejada para a compressão (1-100). Recomenda-se entre 70 e 85.
     * @param maxWidth Largura máxima para redimensionamento (opcional).
     * @param maxHeight Altura máxima para redimensionamento (opcional).
     * @return File arquivo comprimido e redirecionado.
     */
    override suspend fun compressAndResizeImage(
        imageUri: Uri,
        quality: Int,
        maxWidth: Int,
        maxHeight: Int
    ): File = withContext(Dispatchers.IO) {
        // Carrega o Bitmap a partir do Uri
        val originalBirmap = uriToBitmap(context, imageUri)
            ?: throw IllegalArgumentException("Imagem não encontrada")

        // Redimensiona a imagem se necessário
        val resizeBitmap =
            if (originalBirmap.width > maxWidth || originalBirmap.height > maxHeight) {
                resizeBitmap(originalBirmap, maxWidth, maxHeight)
            } else {
                originalBirmap
            }

        // Salva o bitmap comprimido em um arquivo temporário
        val compressedFile = File(context.cacheDir, "compressed_${System.currentTimeMillis()}.jpg")
        FileOutputStream(compressedFile).use { outputStream ->
            resizeBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        }
        compressedFile
    }

    /**
     * Converter um Uri em um Bitmap.
     * Esta função é suspensa para suportar coroutines.
     *
     * @param context Contexto para resolver a Uri.
     * @param uri Uri da imagem.
     * @return Bitmap ou null se a imagem não for encontrada.
     */

    private suspend fun uriToBitmap(context: Context, uri: Uri): Bitmap? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                null
            }
        }

    /**
     * Redimensionar um Bitmap mantendo a proporção
     *
     * @param bitmap Bitmap a ser redimensionado.
     * @param maxWidth Largura máxima.
     * @param maxHeight Altura máxima.
     * @return Bitmap redimensionado.
     */
    private fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val aspectRation = bitmap.width.toFloat() / bitmap.height.toFloat()
        val width = if (aspectRation > 1) maxWidth else (maxHeight * aspectRation).toInt()
        val height = if (aspectRation > 1) (maxWidth / aspectRation).toInt() else maxHeight
        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }
}