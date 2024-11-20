package com.example.myproperties.presentation.ui.properties.add.subviews.photos

import android.net.Uri

data class PhotoInViewModel(
    val photoId: Int = System.currentTimeMillis().hashCode(),
    val uri: Uri?,
    val localPath: String,
    val index: Int
)
