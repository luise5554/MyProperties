package com.example.myproperties.domain.rules

import com.example.myproperties.domain.model.rules.PropertyInfoValidationModel

interface PropertyInfoValidatorInterface {
    fun validateInfo(
        propertyType: String?,
        propertyTypeName: String?,
        maxGuestNumber: String?,
        bedsQuantity: String?,
        bathRoomsQuantity: String?,
        title: String?,
        description: String?,
        latitude: Double?,
        longitude: Double?
    ): PropertyInfoValidationModel

    fun mapValidationToMessage(propertyInfoValidationModel: PropertyInfoValidationModel): String
}