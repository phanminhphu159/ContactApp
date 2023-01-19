package com.example.contactapplication.ktext.image

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import com.example.contactapplication.App
import java.io.*


object ImageUtil {

    fun uriToImageFile(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor =
            App.Companion.appContext.contentResolver.query(uri, filePathColumn, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val filePath = cursor.getString(columnIndex)
                cursor.close()
                return File(filePath)
            }
            cursor.close()
        }
        return null
    }

    fun uriToBitmap(uri: Uri): Bitmap {
        return MediaStore.Images.Media.getBitmap(App.appContext.contentResolver, uri)
    }

    fun convertFileToByteArray(f: File?): ByteArray? {
        var byteArray: ByteArray? = null
        try {
            val inputStream: InputStream = FileInputStream(f)
            val bos = ByteArrayOutputStream()
            val b = ByteArray(1024 * 8)
            var bytesRead = 0
            while (inputStream.read(b).also { bytesRead = it } != -1) {
                bos.write(b, 0, bytesRead)
            }
            byteArray = bos.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return byteArray
    }

    fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? {
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? {
        return byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size) }
    }
}
