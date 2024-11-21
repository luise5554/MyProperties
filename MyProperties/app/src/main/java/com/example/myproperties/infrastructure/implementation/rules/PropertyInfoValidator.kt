package com.example.myproperties.infrastructure.implementation.rules

import android.content.Context
import com.example.myproperties.R
import com.example.myproperties.domain.model.rules.PropertyInfoValidationModel
import com.example.myproperties.domain.rules.PropertyInfoValidatorInterface
import com.example.myproperties.presentation.ui.properties.add.subviews.photos.PhotoInViewModel

class PropertyInfoValidator(val context: Context): PropertyInfoValidatorInterface {

    override fun validateInfo(
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
    ): PropertyInfoValidationModel {

        if (propertyType.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_PROPERTY_TYPE
        }

        if (description.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_DESCRIPTION
        }

        if (title.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_TITLE
        }

        if (propertyTypeName.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_PROPERTY_TYPE_NAME
        }

        if (maxGuestNumber.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_MAX_GUESTS_NUMBER
        }

        if (bedsQuantity.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_BEDS_NUMBER
        }

        if (bathRoomsQuantity.isNullOrEmpty()){
            return PropertyInfoValidationModel.EMPTY_BATHROOMS_NUMBER
        }

        if (
            latitude == null
            || latitude == 0.0
            || longitude == null
            || longitude == 0.0
            ){
            return PropertyInfoValidationModel.EMPTY_LOCATION
        }

        if (listPhotos.isNullOrEmpty() || listPhotos.count() < 5){
            return PropertyInfoValidationModel.EMPTY_OR_NOT_ENOUGH_PHOTOS_LIST
        }

        return PropertyInfoValidationModel.SUCCESS_VALIDATION
    }

    override fun mapValidationToMessage(propertyInfoValidationModel: PropertyInfoValidationModel): String {
        return when(propertyInfoValidationModel){
            PropertyInfoValidationModel.EMPTY_PROPERTY_TYPE -> context.getString(R.string.emtpy_property_type_error)
            PropertyInfoValidationModel.EMPTY_PROPERTY_TYPE_NAME -> context.getString(R.string.emtpy_property_type_error)
            PropertyInfoValidationModel.EMPTY_DESCRIPTION -> context.getString(R.string.emtpy_property_description)
            PropertyInfoValidationModel.EMPTY_TITLE -> context.getString(R.string.emtpy_property_title)
            PropertyInfoValidationModel.EMPTY_MAX_GUESTS_NUMBER -> context.getString(R.string.emtpy_property_max_guests_number)
            PropertyInfoValidationModel.EMPTY_BEDS_NUMBER -> context.getString(R.string.emtpy_property_beds_number)
            PropertyInfoValidationModel.EMPTY_BATHROOMS_NUMBER -> context.getString(R.string.emtpy_property_bathrooms_number)
            PropertyInfoValidationModel.EMPTY_LOCATION -> context.getString(R.string.emtpy_property_location)
            PropertyInfoValidationModel.SUCCESS_VALIDATION -> context.getString(R.string.success)
            PropertyInfoValidationModel.EMPTY_OR_NOT_ENOUGH_PHOTOS_LIST -> context.getString(R.string.empty_or_no_enough_photo_list)
        }
    }


}