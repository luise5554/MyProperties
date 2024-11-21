package com.example.myproperties.presentation.ui.properties.add.subviews.photos

import android.net.Uri
import com.example.myproperties.domain.model.PhotoModel

data class PhotoInViewModel(
    val photoId: Int = System.currentTimeMillis().hashCode(),
    val uri: Uri?,
    val localPath: String,
    val index: Int
)

fun PhotoInViewModel.toPhotoModel(propertyId: Int, newUriString: String): PhotoModel{
    return PhotoModel(
        propertyId = propertyId,
        localPath= newUriString,
        photoDescription = "",
        order= index
    )
}