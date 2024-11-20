package com.example.myproperties.domain.model

data class PhotoModel(
    val photoId: Int = System.currentTimeMillis().hashCode(),
    val localPath: String,
    val photoDescription: String,
    val order: Int
)
