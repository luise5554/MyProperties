package com.example.myproperties.domain.model

data class PropertyModel(
    val propertyType: Int,
    val propertyTypeName: String,
    val maxRoomers: Int,
    val bedsQuantity: Int,
    val bathRoomsQuantity: Int,
    val title: String,
    val description: String,
    val latitude: Long,
    val longitude: Long
)