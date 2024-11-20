package com.example.myproperties.domain.model

data class PhotoModel(
    val photoId: Int = 0,
    val propertyId: Int,
    val localPath: String,
    val photoDescription: String,
    val order: Int
)
