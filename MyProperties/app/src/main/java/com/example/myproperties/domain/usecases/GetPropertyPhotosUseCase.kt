package com.example.myproperties.domain.usecases

import com.example.myproperties.domain.model.PhotoModel
import com.example.myproperties.infrastructure.data.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPropertyPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {

    fun getPhotosByPropertyId(propertyId: Int): Flow<List<PhotoModel>> {
        return photoRepository.getProperties(propertyId)
    }

}