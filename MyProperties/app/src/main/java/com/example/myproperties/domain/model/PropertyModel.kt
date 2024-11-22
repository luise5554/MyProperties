package com.example.myproperties.domain.model

data class PropertyModel(
    val propertyId: Int = System.currentTimeMillis().hashCode(),
    val propertyType: Int,
    val propertyTypeName: String,
    val maxGuestsNumber: Int,
    val bedsQuantity: Int,
    val bathRoomsQuantity: Int,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
)