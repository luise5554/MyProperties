package com.example.myproperties.infrastructure.data.repository

import com.example.myproperties.domain.model.PhotoModel
import com.example.myproperties.infrastructure.data.dao.PhotoDao
import com.example.myproperties.infrastructure.data.entity.toData
import com.example.myproperties.infrastructure.data.entity.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(private val photoDao: PhotoDao) {

    fun getProperties(propertyId: Int): Flow<List<PhotoModel>> {
        val properties: Flow<List<PhotoModel>> = photoDao.getPhotos(propertyId = propertyId).map {
                propertiesData -> propertiesData.map { photoEntity -> photoEntity.toDomain() }
        }
        return properties
    }


    suspend fun addPhoto(photoModel: PhotoModel){
        photoDao.addPhoto(photoModel.toData())
    }

}