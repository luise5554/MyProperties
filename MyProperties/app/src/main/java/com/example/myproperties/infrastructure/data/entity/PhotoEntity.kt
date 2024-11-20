package com.example.myproperties.infrastructure.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myproperties.domain.model.PhotoModel

@Entity
data class PhotoEntity (
    @PrimaryKey
    val photoId: Int,
    val propertyId: Int,
    val localPath: String,
    val photoDescription: String,
    val order: Int
)

fun PhotoEntity.toDomain(): PhotoModel{
    return PhotoModel(
        photoId = photoId,
        propertyId = propertyId,
        localPath = localPath,
        photoDescription = photoDescription,
        order = order
    )
}

fun PhotoModel.toData(): PhotoEntity{
    return PhotoEntity(
        photoId = photoId,
        propertyId = propertyId,
        localPath = localPath,
        photoDescription = photoDescription,
        order = order
    )
}