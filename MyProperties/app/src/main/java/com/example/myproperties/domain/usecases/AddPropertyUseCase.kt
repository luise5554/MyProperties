package com.example.myproperties.domain.usecases

import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.infrastructure.data.repository.PropertyRepository
import javax.inject.Inject

class AddPropertyUseCase @Inject constructor(private val propertyRepository: PropertyRepository) {

    suspend operator fun invoke(propertyModel: PropertyModel){
        propertyRepository.addProperty(propertyModel)
    }

}