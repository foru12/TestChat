package com.example.testchat.usecase.profile

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ImageProcessorUseCase @Inject constructor() {

    fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }

    fun getFileName(contentResolver: ContentResolver, uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            if (it.moveToFirst()) {
                it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
            } else {
                "image.jpg"
            }
        } ?: "image.jpg"
    }

    fun processImage(contentResolver: ContentResolver, uri: Uri): Pair<String, String> {
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        val fileName = getFileName(contentResolver, uri)
        val base64 = bitmapToBase64(bitmap)
        return Pair(fileName, base64)
    }
}