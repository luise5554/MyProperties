package com.example.myproperties.domain.rules

import com.example.myproperties.domain.model.rules.PropertyInfoValidationModel
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoInViewModel

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
        longitude: Double?,
        listPhotos: List<PhotoInViewModel>?
    ): PropertyInfoValidationModel

    fun mapValidationToMessage(propertyInfoValidationModel: PropertyInfoValidationModel): String
}