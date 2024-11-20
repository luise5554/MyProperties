package com.example.myproperties.infrastructure.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myproperties.domain.model.PropertyModel

@Entity
data class PropertyEntity(
    @PrimaryKey
    val propertyId: Int,
    val propertyType: Int,
    val propertyTypeName: String,
    val maxRoomers: Int,
    val bedsQuantity: Int,
    val bathRoomsQuantity: Int,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)

fun PropertyEntity.toDomain(): PropertyModel{
    return PropertyModel(
        propertyId = propertyId,
        propertyType = propertyType,
        propertyTypeName = propertyTypeName,
        maxGuestsNumber = maxRoomers,
        bedsQuantity = bedsQuantity,
        bathRoomsQuantity = bathRoomsQuantity,
        title = title,
        description = description,
        latitude = latitude,
        longitude = longitude
    )
}

fun PropertyModel.toData(): PropertyEntity{
    return PropertyEntity(
        propertyId = propertyId,
        propertyType = propertyType,
        propertyTypeName = propertyTypeName,
        maxRoomers = maxGuestsNumber,
        bedsQuantity = bedsQuantity,
        bathRoomsQuantity = bathRoomsQuantity,
        title = title,
        description = description,
        latitude = latitude,
        longitude = longitude
    )
}