package com.example.myproperties.domain.usecases

import com.example.myproperties.domain.model.PropertyModel
import com.example.myproperties.infrastructure.data.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPropertyUseCase @Inject constructor(
    private val propertyRepository: PropertyRepository
) {

    operator fun invoke(): Flow<List<PropertyModel>> {
        return propertyRepository.properties
    }

    suspend fun getPropertyBy(propertyId: Int): PropertyModel{
        return propertyRepository.getPropertyById(propertyId = propertyId)
    }

}