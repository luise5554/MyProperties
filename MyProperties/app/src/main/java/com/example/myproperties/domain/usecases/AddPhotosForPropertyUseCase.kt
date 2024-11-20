package com.example.myproperties.domain.usecases

import com.example.myproperties.domain.model.PhotoModel
import com.example.myproperties.infrastructure.data.repository.PhotoRepository
import javax.inject.Inject

class AddPhotosForPropertyUseCase @Inject constructor(private val photoRepository: PhotoRepository) {

    suspend operator fun invoke(photoList: List<PhotoModel>){
        photoList.forEach { photoModel ->
            photoRepository.addPhoto(photoModel)
        }
    }

}