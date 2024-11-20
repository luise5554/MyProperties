package com.example.myproperties.presentation.ui.properties.add.subviews.photos

import android.net.Uri
import com.example.myproperties.domain.model.PhotoModel

data class PhotoInViewModel(
    val photoId: Int = System.currentTimeMillis().hashCode(),
    val uri: Uri?,
    val localPath: String,
    val index: Int
)

fun PhotoInViewModel.toPhotoModel(propertyId: Int): PhotoModel{
    return PhotoModel(
        propertyId = propertyId,
        localPath= uri?.path ?: "",
        photoDescription = "",
        order= index
    )
}